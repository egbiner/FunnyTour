package cn.imhtb.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    private Integer id;

    private Integer pid;

    private String ip;

    private Integer userId;

    private Integer essayId;

    private String content;

    private Date createTime;

    private Date updateTime;

    /**
     * 楼主
     */
    private Boolean booleanFloorHost;

    /**
     * 文章作者
     */
    private Boolean booleanAuthor;

    /**
     * 当前用户是作者
     */
    private Boolean booleanCurrentUserAuthor;

    /**
     *
     *
     */

    private String username;

    private String userAvatar;

    private Integer userLevel;
}
