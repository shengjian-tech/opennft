/**
 *
 */
package net.shengjian.frame.util;

import net.shengjian.frame.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理类的工具类. 例如反射
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see ClassUtils
 */

public class
ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);
    // 缓存 entity的字段信息
    private static Map<String, EntityInfo> staticEntitymap = new ConcurrentHashMap<>();

    /**
     * 默认值为NULL
     */
    static {
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new BooleanConverter(null), Boolean.class);
        ConvertUtils.register(new ByteConverter(null), Byte.class);
        ConvertUtils.register(new CharacterConverter(null), Character.class);
        ConvertUtils.register(new StringConverter(null), String.class);
    }

    private ClassUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 根据ClassName获取 EntityInfo
     * @param clazz
     * @return
     * @throws Exception
     */
    public static EntityInfo getEntityInfoByClass(Class clazz) throws Exception {
        if (clazz == null) {
            return null;
        }
        String className = clazz.getName();

        boolean isContains = staticEntitymap.containsKey(className);
        if (isContains) {
            // 需要把EntityInfo 浅拷贝一个对象,不然多线程会争抢这一个内存对象.
            EntityInfo entityInfo = staticEntitymap.get(className);

            if (entityInfo != null) {
                EntityInfo newInfo = new EntityInfo();
                // 浅克隆
                BeanUtils.copyProperties(newInfo, entityInfo);
                return newInfo;
            }
        }

        EntityInfo info = new EntityInfo();

        if ((clazz.isAnnotationPresent(Table.class))) {
            info.setTableAnnotation(true);
        }

        if (clazz.isAnnotationPresent(TableSuffix.class)) {
            info.setTableSuffixAnnotation(true);
            TableSuffix tableSuffix = (TableSuffix) clazz.getAnnotation(TableSuffix.class);
            info.setTableSuffixFieldName(tableSuffix.name());
        }
        if (clazz.isAnnotationPresent(NotLog.class)) {
            info.setNotLog(true);
        }

        if (clazz.isAnnotationPresent(LuceneSearch.class)) {
            info.setLuceneSearchAnnotation(true);
        }

        String tableName = ClassUtils.getTableNameByClass(clazz);
        info.setTableName(tableName);
        info.setClassName(className);

        List<FieldInfo> fields = new ArrayList<>();
        info.setFields(fields);
        recursionFiled(clazz, info, fields);

        staticEntitymap.put(className, info);

        return info;
    }

    /**
     * 根据对象获取Entity信息,主要是为了获取分表的信息
     *
     * @param o
     * @return
     * @throws Exception
     */
    public static EntityInfo getEntityInfoByEntity(Object o) throws Exception {
        if (o == null)
            return null;
        Class clazz = o.getClass();
        EntityInfo info = getEntityInfoByClass(clazz);
        if (info == null) {
            return null;
        }
        String tableExt = getTableExt(o);
        info.setTableSuffix(tableExt);
        return info;
    }

    /**
     * clazz 属性 fd 的 getReadMethod() 是否包含 注解 annotationName
     *
     * @param clazz
     * @param fdName
     * @param annotationClass
     * @return
     * @throws Exception
     */
    public static boolean isAnnotation(Class clazz, String fdName, Class annotationClass) throws Exception {

        if (clazz == null || fdName == null || annotationClass == null) {
            return false;
        }
        PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
        Method getMethod = pd.getReadMethod();// 获得get方法
        return getMethod.isAnnotationPresent(annotationClass);

    }

    /**
     * 获取 Class 的@Table注解 name 属性,没有属性则返回 null
     *
     * @param object
     * @return
     * @throws Exception
     */
    protected static String getTableName(Object object) throws Exception {

        if (object == null) {
            return null;
        }

        String tableName = null;

        if (object instanceof Class) {
            EntityInfo entityInfo = getEntityInfoByClass((Class) object);

            if (entityInfo == null) {
                return null;
            }
            tableName = entityInfo.getTableName();
        } else {
            EntityInfo entityInfoByEntity = ClassUtils.getEntityInfoByEntity(object);

            if (entityInfoByEntity == null) {
                return null;
            }

            tableName = entityInfoByEntity.getTableName();
            String tableExt = entityInfoByEntity.getTableSuffix();
            if (StringUtils.isNotBlank(tableExt)) {
                tableName = tableName + tableExt;
            }
        }

        return tableName;

    }

    /**
     * 获取 Class 的@Table注解 name 属性,没有属性则返回 null
     *
     * @param clazz
     * @return
     */
    protected static String getTableNameByClass(Class clazz) {

        if (clazz == null) {
            return null;
        }

        if ((clazz.isAnnotationPresent(Table.class) == false)) {
            return null;
        }

        Table table = (Table) clazz.getAnnotation(Table.class);

        String tableName = table.name();

        return tableName;

    }

    /**
     * 获取数据库分表的后缀
     *
     * @param o
     * @return
     * @throws Exception
     */
    public static String getTableExt(Object o) throws Exception {
        Class clazz = o.getClass();

        EntityInfo entityInfo = getEntityInfoByClass(clazz);

        if (entityInfo == null) {
            return "";
        }

        if (!entityInfo.getTableSuffixAnnotation()) {// 不分表
            return "";
        }
        String p = entityInfo.getTableSuffixFieldName();
        String tableExt = (String) getPropertieValue(p, o);
        return tableExt;

    }

    /**
     * 递归查询父类的所有属性,set 去掉重复的属性
     * @param clazz
     * @param info
     * @param fields
     * @return
     * @throws Exception
     */
    private static List<FieldInfo> recursionFiled(Class clazz, EntityInfo info, List<FieldInfo> fields)
            throws Exception {

        if (fields == null) {
            return null;
        }

        Field[] fds = clazz.getDeclaredFields();

        if (fds == null || fds.length < 1) {
            return fields;
        }

        for (int i = 0; i < fds.length; i++) {
            FieldInfo finfo = new FieldInfo();
            Field fd = fds[i];
            String fieldName = fd.getName();

            boolean exist = false;
            for (FieldInfo fi : fields) {
                if (fieldName.equals(fi.getFieldName())) {// 如果字段已经存在
                    exist = true;
                    break;
                }
            }

            if (exist) {
                continue;
            }

            finfo.setFieldName(fieldName);// 字段名称
            finfo.setFieldType(fd.getType());// 字段类型
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method getMethod = pd.getReadMethod();// 获得get方法

            if (getMethod.isAnnotationPresent(Id.class)) {// 如果是主键
                finfo.setPk(true);
                info.setPkName(fieldName);
                info.setPkReturnType(getMethod.getReturnType());
            }

            if (getMethod.isAnnotationPresent(LuceneField.class)) {// 如果有LuceneField注解
                finfo.setLuceneField(true);
                LuceneField luceneField = getMethod.getAnnotation(LuceneField.class);

                finfo.setLuceneStored(luceneField.luceneStored());
                finfo.setLuceneIndex(luceneField.luceneIndex());

                if (fd.getType() == String.class) {// 如果是String
                    finfo.setStringTokenized(luceneField.stringTokenized());
                    // finfo.setLuceneFacet(luceneField.luceneFacet());
                } else if (fd.getType() == Integer.class || fd.getType() == int.class) {
                    finfo.setNumericSort(luceneField.numericSort());
                } else if (fd.getType() == Double.class || fd.getType() == double.class) {
                    finfo.setNumericSort(luceneField.numericSort());
                } else if (fd.getType() == Float.class || fd.getType() == float.class) {
                    finfo.setNumericSort(luceneField.numericSort());
                } else if (fd.getType() == Date.class || fd.getType() == BigInteger.class) {
                    finfo.setNumericSort(luceneField.numericSort());
                }

            }
            if (getMethod.isAnnotationPresent(Transient.class)) {// 如果不是数据库字段
                finfo.setDb(false);
            }
            if (getMethod.isAnnotationPresent(WhereSQL.class)) {// 如果有WhereSQL注解
                WhereSQL ws = getMethod.getAnnotation(WhereSQL.class);
                finfo.setWhereSQL(ws.sql());
            }
            if (getMethod.isAnnotationPresent(PKSequence.class)) {// 如果有PKSequence注解
                PKSequence pkSequence = getMethod.getAnnotation(PKSequence.class);
                finfo.setPkSequence(pkSequence.name());
                info.setPksequence(pkSequence.name());
            }

            fields.add(finfo);
        }
        Class superClass = clazz.getSuperclass();
        if (superClass != Object.class) {
            recursionFiled(superClass, info, fields);
        }
        return fields;
    }

    /**
     * 获得主键的值
     *
     * @param o
     * @return
     * @throws Exception
     */
    public static Object getPKValue(Object o) throws Exception {
        Class clazz = o.getClass();

        EntityInfo entityInfo = getEntityInfoByClass(clazz);

        if (entityInfo == null) {
            return null;
        }

        String id = entityInfo.getPkName();
        return getPropertieValue(id, o);

    }

    /**
     * 获取一个实体类的属性值
     *
     * @param p
     * @param o
     * @return
     * @throws Exception
     */
    public static Object getPropertieValue(String p, Object o) throws Exception {

        PropertyDescriptor pd = new PropertyDescriptor(p, o.getClass());
        Method getMethod = pd.getReadMethod();// 获得get方法
        if (getMethod != null) {
            return getMethod.invoke(o);
        }

        return null;

        /*
         * for (Class<?> clazz = o.getClass(); clazz != Object.class; clazz =
         * clazz.getSuperclass()) {
         *
         * PropertyDescriptor pd = new PropertyDescriptor(p, clazz); Method getMethod =
         * pd.getReadMethod();// 获得get方法 if (getMethod != null) { return
         * getMethod.invoke(o); }
         *
         * }
         *
         * return null;
         */

    }

    /**
     * 设置实体类的属性值
     *
     * @param p
     * @param o
     * @return
     * @throws Exception
     */
    public static Object setPropertieValue(String p, Object o, Object value) throws Exception {

        /*
         * PropertyDescriptor pd = new PropertyDescriptor(p, o.getClass()); //获得set方法
         * Method setMethod = pd.getWriteMethod(); setMethod.invoke(o, value);
         */

        BeanUtils.setProperty(o, p, value);

        return o;

        /*
         * for (Class<?> clazz = o.getClass(); clazz != Object.class; clazz =
         * clazz.getSuperclass()) { PropertyDescriptor pd = new PropertyDescriptor(p,
         * clazz); Method setMethod = pd.getWriteMethod();// 获得set方法 if (setMethod !=
         * null) { setMethod.invoke(o, value); return o; }
         *
         * }
         *
         * return o;
         *
         */

    }

    /**
     * 获取字段的返回类型
     * @param p
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Class getReturnType(String p, Class clazz) throws Exception {

        if (StringUtils.isBlank(p) || clazz == null) {
            return null;
        }

        EntityInfo entityInfo = getEntityInfoByClass(clazz);

        if (entityInfo == null) {
            return null;
        }

        List<FieldInfo> fields = entityInfo.getFields();

        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        for (FieldInfo finfo : fields) {
            if (p.equals(finfo.getFieldName())) {// 属性名和字段名相同
                return finfo.getFieldType();
            }
        }

        return null;
    }

    /**
     * 是否是java的基本类型
     *
     * @param clazz
     * @return
     */
    public static boolean isBaseType(Class clazz) {
        if (clazz == null) {
            return false;
        }
        String className = clazz.getName().toLowerCase();
        return className.startsWith("java.") || className.startsWith("javax.");
    }

    /**
     * 获取所有字段的名称
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    private static Set<String> getAllFieldNames(Class clazz) throws Exception {
        EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
        if (entityInfoByClass == null) {
            return null;
        }

        List<FieldInfo> fields = entityInfoByClass.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        Set<String> set = new HashSet<>();

        for (FieldInfo finfo : fields) {
            set.add(finfo.getFieldName());
        }

        return set;
    }

    /**
     * 获取所有的数据库字段
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List<String> getAllDBFields(Class clazz) throws Exception {
        EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
        if (entityInfoByClass == null) {
            return null;
        }

        List<FieldInfo> fields = entityInfoByClass.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        List<String> list = new ArrayList<>();

        for (FieldInfo finfo : fields) {
            if (finfo.getDb()) {
                list.add(finfo.getFieldName());
            }

        }

        return list;

    }

    /**
     * 获取所有分词的字段,一般用于分词查询内容
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List<FieldInfo> getLuceneTokenizedFields(Class clazz) throws Exception {
        EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
        if (entityInfoByClass == null) {
            return null;
        }

        List<FieldInfo> fields = entityInfoByClass.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        List<FieldInfo> list = new ArrayList<>();

        for (FieldInfo finfo : fields) {

            if (finfo.getPk()) {// 如果是主键,强制不分词
                continue;
            }

            if (finfo.getStringTokenized()) {
                list.add(finfo);
            }

        }

        return list;

    }

    /**
     * 获取所有LuceneField 注解的字段,参与Lucene索引的字段
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List<FieldInfo> getLuceneFields(Class clazz) throws Exception {
        EntityInfo entityInfoByClass = getEntityInfoByClass(clazz);
        if (entityInfoByClass == null) {
            return null;
        }

        List<FieldInfo> fields = entityInfoByClass.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        List<FieldInfo> list = new ArrayList<>();

        for (FieldInfo finfo : fields) {
            if (finfo.getLuceneField()) {
                list.add(finfo);
            }
        }
        return list;

    }

    /**
     * 复制
     *
     * @param bean
     * @param properties
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void populate(final Object bean, final Map<String, ? extends Object> properties) throws Exception {
        // Do nothing unless both arguments have been specified
        if ((bean == null) || (properties == null)) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("ClassUtils.populate(" + bean + ", " + properties + ")");
        }
        Set<String> allFieldNames = getAllFieldNames(bean.getClass());

        // Loop through the property name/value pairs to be set
        for (final Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
            // Identify the property name and value(s) to be assigned
            final String name = entry.getKey();
            if (name == null) {
                continue;
            }
            String _name = name;
            if (!allFieldNames.contains(name)) {

                if (!_name.contains(GlobalStatic.SQLCutSeparator)) {
                    continue;
                }

            }

            if (_name.contains(GlobalStatic.SQLCutSeparator)) {

                String[] strs = _name.split(GlobalStatic.SQLCutSeparator);
                // 获取最后的属性名称
                _name = strs[strs.length - 1];
                // 循环获取实体对象
                Object o = bean;
                for (int i = 0; i < strs.length - 1; i++) {
                    o = getPropertieValue(strs[i], o);
                }
                setPropertieValue(_name, o, entry.getValue());

            } else {
                // Perform the assignment for this property
                setPropertieValue(name, bean, entry.getValue());
            }

        }

    }

    /**
     * 获取类的泛型实际类型
     * @return
     */
    public static Class getActualTypeGenericSuperclass(Class clazz) {
        //获取子类所属接口的参数化类型,cn.xxx.xxx.BasicAction<cn.xxx.xxx.Standard>
        Type type = clazz.getGenericSuperclass();
        //因为type是顶级接口没有定义任何方法，所以需要强转为子接口ParameterizedType
        ParameterizedType parameterizedType = (ParameterizedType) type;

        //通过子接口定义的getActualTypeArguments方法获取到实际参数类型,<cn.xxx.xxx.Standard>
        //返回参数为数组，因为Java中接口可以多实现
        Type[] types = parameterizedType.getActualTypeArguments();
        //获取数组中的实际参数类型
        return (Class) types[0];

    }


}
