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
     *
     *
     */

    private String username;

    private String userAvatar;

    private Boolean booleanAuthor;
}
