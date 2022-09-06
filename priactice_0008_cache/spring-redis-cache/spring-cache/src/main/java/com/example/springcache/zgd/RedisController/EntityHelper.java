package com.example.springcache.zgd.RedisController;

import org.reflections.Reflections;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityHelper {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(EntityHelper.class);

    /**
     * 将实体实例转化成map
     * @param entity 实体对象
     * @return [fieldName: fieldValue]
     */
    public static Map<String, Object> toMap(Object entity){
        if (entity == null) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(entity);
                        map.put(key, value);
                    }

                }
            } catch (Exception e) {
                System.out.println("transBeanToMap Error --> " + e.getLocalizedMessage());
                e.printStackTrace();
            }
            return map;
        }
    }

    /**
     * 缓存实体类的set、get方法
     *
     * @param clazz        类弄
     * @param setMethodMap [字段:set方法]
     * @param getMethodMap [字段:get方法]
     */
    public static void fillMethodMap(Class clazz, Map<String, Method> setMethodMap, Map<String, Method> getMethodMap) {
        Set<String> method_file_part = Arrays.stream(clazz.getDeclaredFields()).map(field -> field.getName().toLowerCase()).collect(Collectors.toSet());
        String method_name = "", field_name = "";
        for (Method method : clazz.getMethods()) {
            method_name = method.getName();
            if (method_name.startsWith("set") && method_file_part.contains((field_name = method_name.replace("set", "").toLowerCase()))) {
                setMethodMap.put(field_name, method);
            } else if (method_name.startsWith("get") && method_file_part.contains((field_name = method_name.replace("get", "").toLowerCase()))) {
                getMethodMap.put(field_name, method);
            }
        }
    }

    /**
     * 寻找指定包被指定注解修饰的类
     * @param packageName 包名
     * @param annotation 注解类
     * @return 类集合
     */
    public static Set<Class<?>> getPackageClassByAnnotation(String packageName, Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(annotation);
    }

    private EntityHelper(){}
}
