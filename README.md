# 简介

​		Java花式排序工具：支持实体的多字段同时自定义排序，支持每个字段设置自己的配置，包括：升序降序、null值处理、映射转换、转换异常处理、或者设定某个特殊值为最大或最小，而且对实体类的继承性没有要求。



# 作品描述

### 背景：

| 学号 | 姓名 | 语文 | 数学 | 英语 | 总分 |
| :--: | :--: | :--: | :--: | :--: | :--: |
|      |      |      |      |      |      |
|      |      |      |      |      |      |
|      |      |      |      |      |      |
|      |      |      |      |      |      |

**需求：**

点击每一个表头时：

+ 第1次点击，根据此列升序排序

+ 第2次点击，根据此列降序排序

+ 第3次点击，取消此列排序

+ 而且要求能多字段同时排序，比如：语文成绩一样则按数学成绩排。



### 平时怎么解决的：

1. 让实体类实现Comparable，缺点：compareTo方法只能重写一次，也就是说一个实体只能有一种排序方式

2. 临时按需求创建一个Comparator的实现，创建多少个就有多少种排序方式，缺点：每次都需要写临时创建的代码，可复用性差



### 合理的解决方式：

封装Comparator的动态创建，并扩展它的功能，代码如下（具体代码见最下方GitHub链接）：

```java
/**
 * 辅助比较器
 */
public class OrderHelper<T> implements Comparator<T> {
    
    // 最大值
    private static final Comparable<?> MAX_VALUE = o -> 1;
    // 最小值
    private static final Comparable<?> MIN_VALUE = o -> -1;
    // 配置信息
    private final List<OrderConfig<T>> orderConfigs;
    
    /**
     * 检查配置信息
     */
    OrderHelper(List<OrderConfig<T>> orderConfigs) {
        if (orderConfigs == null || orderConfigs.isEmpty()) {
            throw new NullPointerException("orderConfig 未设置");
        }
        this.orderConfigs = orderConfigs;
    }
    
    /**
     * 通过比较指定字段来比较两个实体
     */
    @Override
    public int compare(T entity1, T entity2) {
        if (Objects.equals(entity1, entity2)) {
            return 0;
        }
        int result = 0;
        // 如果字段值相等，比较下一层的字段
        for (int depth = 0; result == 0 && depth < orderConfigs.size(); depth++) {
            Comparable comparable1 = getFieldValue(entity1, depth);
            Comparable<?> comparable2 = getFieldValue(entity2, depth);
            if (Objects.equals(comparable1, comparable2)) {
                continue;
            }
            // 极值判断
            boolean orderMode = orderConfigs.get(depth).getOrderMode();
            if (comparable2 == MAX_VALUE) {
                return orderMode ? 1 : -1;
            }
            if (comparable2 == MIN_VALUE) {
                return orderMode ? -1 : 1;
            }
            result = comparable1.compareTo(comparable2);
            // 升序降序判断
            result = orderMode ? -result : result;
        }
        return result;
    }
    
    /**
     * 通过 mapper 获取实体的字段值（用于比较）
     * 并进行极值处理、异常处理
     */
    public Comparable<?> getFieldValue(T entity, int depth) {
        OrderConfig<T> orderConfig = orderConfigs.get(depth);
        // 实体为 null 判断
        if (entity == null) {
            if (orderConfig.getNullEntityMode() == 1) {
                return MIN_VALUE;
            }
            if (orderConfig.getNullEntityMode() == 2) {
                return MAX_VALUE;
            }
            throw new NullPointerException("在获取比较字段值时实体为 null");
        }
        Comparable<?> fieldValue;
        try {
            // 获取元素的字段值
            fieldValue = orderConfig.getMapper().apply(entity);
        } catch (Exception e) {
            // 异常判断
            if (orderConfig.getMapErrorMode() == 1) {
                return MIN_VALUE;
            }
            if (orderConfig.getMapErrorMode() == 2) {
                return MAX_VALUE;
            }
            throw new OrderException("在获取比较值时抛出异常: " + e);
        }
        // 极值判断：如果符合最大值或最小值，直接返回极值
        if (orderConfig.getMaxOnValues() != null && orderConfig.getMaxOnValues().contains(fieldValue)) {
            return MAX_VALUE;
        }
        if (orderConfig.getMinOnValues() != null && orderConfig.getMinOnValues().contains(fieldValue)) {
            return MIN_VALUE;
        }
        // 字段值为 null 判断
        if (fieldValue != null) {
            return fieldValue;
        }
        if (orderConfig.getNullFieldMode() == 1) {
            return MIN_VALUE;
        }
        if (orderConfig.getNullFieldMode() == 2) {
            return MAX_VALUE;
        }
        throw new NullPointerException("比较字段值为 null");
    }
    
}

/**
 * 配置信息
 */
public class OrderConfig<T> {
    public static final boolean ASC_MODE = false;
    public static final boolean DESC_MODE = true;
    public static final int ERROR_MODE = 0;
    public static final int MIN_MODE = 1;
    public static final int MAX_MODE = 2;

    // 排序模式 false升序，true降序
    private boolean orderMode;
    // 最大值
    private Set<Object> maxOnValues;
    // 最小值
    private Set<Object> minOnValues;
    // 通过实体获取字段值的映射器
    private Function<T, Comparable<?>> mapper;
    // 映射出错时判断 0抛出异常，1最小，2最大
    private int mapErrorMode;
    // 字段值为null时判断 0抛出异常，1最小，2最大
    private int nullFieldMode;
    // 实体为null时判断 0抛出异常，1最小，2最大
    private int nullEntityMode;
    // 省略get、set
}

/**
 * 花式排序工具类
 */
public class OrderUtils {

    /**
     * 升序排序
     *
     * @param list    待排序集合
     * @param mappers 通过实体获取字段值的映射器
     * @param <T>     实体类型
     */
    @SafeVarargs
    public static <T> void order(List<T> list, Function<T, Comparable<?>>... mappers) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable<?>> mapper : mappers) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(mapper);
            orderConfigs.add(orderConfig);
        }
        order(list, orderConfigs);
    }

    /**
     * 降序排序
     *
     * @param list    待排序集合
     * @param mappers 通过实体获取字段值的映射器
     * @param <T>     实体类型
     */
    @SafeVarargs
    public static <T> void orderDesc(List<T> list, Function<T, Comparable<?>>... mappers) {
        List<OrderConfig<T>> orderConfigs = new ArrayList<>();
        for (Function<T, Comparable<?>> mapper : mappers) {
            OrderConfig<T> orderConfig = new OrderConfig<T>();
            orderConfig.setMapper(mapper);
            orderConfig.setOrderMode(OrderConfig.DESC_MODE);
            orderConfigs.add(orderConfig);
        }
        order(list, orderConfigs);
    }

    public static <T> void order(List<T> list, OrderConfig<T> orderConfig) {
        order(list, new ArrayList<OrderConfig<T>>(1) {{
            add(orderConfig);
        }});
    }

    /**
     * 实际调用此方法
     */
    public static <T> void order(List<T> list, List<OrderConfig<T>> orderConfigs) {
        if (list == null || list.size() < 2) {
            return;
        }
        OrderHelper<T> orderhelper = new OrderHelper<>(orderConfigs);
        // 真正开始排序
        list.sort(orderhelper);
    }

    /**
     * 抛出异常
     */
    public static <T> void order(List<T> list) {
        throw new OrderException("未设置 function");
    }

    public static <T> void orderDesc(List<T> list) {
        throw new OrderException("未设置 function");
    }
}
```

封装了Comparator的动态创建后，上面的需求只需要一行代码完成：

```java
OrderUtils.orderDesc(studentList, Student::getChinese, Student::getMath);
```

**为什么以Comparator为扩展底层，而不是Comparable？**

对Comparable进行扩展，会对实体类的继承结构有要求，而且他们的耦合度较高（实体是Comparable的实现），难以拥有本次排序的上下文空间。



### 创新点

1. 对实体类的继承性和结构没有任何要求，完全通用

2. 对于多字段，而且各个字段排序方式各不相同复杂排序的，可以传入orderConfigs（排序方式的配置信息）

3. 复杂排序的配置满足日常需求，包括：升序降序、null值处理（包括实体为null和字段值为null）、映射转换、转换异常处理、或者设定某个特殊值为最大或最小，比如把null、转换异常项、以及字符串"--"设为最小

4. 利用了Java8的函数式接口，支持字段值进行映射转换，而且可复用映射转换方式，比如从字符串"65.86%"到数字0.6586可以使用以下这个转换方式

```java
Function<Student, Comparable<?>> myMapper = 
(p) -> new BigDecimal(p.getXxxRate().substring(0, p.getXxxRate().length() - 1));
```

当然也可以给实体类增加一个GET方法（这种方式虽然会修改实体类，但可以把最终要比较的值缓存在实体对象中）：

```java
public class Student {    
    private String xxxRate;    
    private BigDecimal xxxRateForOrder;
    public void setXxxRate(String xxxRate) {
        this.xxxRate = xxxRate;
    }
    public String getXxxRate() {        
        return xxxRate;    
    }    
    public BigDecimal getXxxRateForOrder() {        
        if (xxxRateForOrder == null) {            
            xxxRateForOrder = new BigDecimal(xxxRate.substring(0, xxxRate.length() - 1));        
        }        
        return xxxRateForOrder;    
    }
}
```

然后:

```java
OrderUtils.order(studentList, Student::getXxxRateForOrder);
```



### 带来的影响

+ 提升开发效率

+ 减少相关代码，提升复用性

+ 在不侵入实体类的情况下，使花式排序变简单



## 链接

https://github.com/Arvin5445/orderhelper