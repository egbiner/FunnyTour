package cn.imhtb.common;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String BAIDU_AK = "5ENbMgWEB4n1s1B0YenkXWmjZvtLZkIG";
    public static final String BAIDU_GET_POINT_URL_PREFIX = "http://api.map.baidu.com/geocoder/v2/?address=";
    public static final String BAIDU_GET_POINT_URL_SUFFIX = "&output=json&ak=";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

}
