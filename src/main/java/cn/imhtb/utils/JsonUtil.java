package cn.imhtb.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 序列化与反序列化的全局配置
     */
    static {
        //序列化时包括所有字段
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        //取消默认转换日期为时间戳的设置
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);

        //格式化时间日期
//        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT_STR));

        //忽略空bean转json的报错，默认情况下 empty bean 会报错
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        //忽略在json中存在属性却在bean无对应属性转换时报错
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String  obj2string(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("parse obj2string warn",e);
            return null;
        }
    }

    /**
     * 格式化json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String  obj2stringPretty(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("parse obj2string warn",e);
            return null;
        }
    }

    /**
     * 转换单个对象，无法正确处理集合
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str :  objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }

    /**
     * 通过 TypeReference 指定要返回的集合对象
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }

    /**
     * 通过传入多个class对象来进行类型转换
     * @param str
     * @param collectionClass
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, Class<?> collectionClass,Class<?>... classes){
        if (StringUtils.isEmpty(str) || collectionClass == null || classes==null) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, classes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }



}
