package cn.imhtb.common;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String BAIDU_AK = "pdoFDkpZ8GSHk6M7Usv9QMRGXGic8z4l";
    public static final String BAIDU_GET_POINT_URL_PREFIX = "http://api.map.baidu.com/geocoder/v2/?address=";
    public static final String BAIDU_GET_POINT_URL_SUFFIX = "&output=json&ak=";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public interface EssayOp{
        int ESSAY_VIEW = 0;//浏览
        int ESSAY_VOTE = 1;//点赞
        int ESSAY_COMMENT = 2;//评论

    }

}
