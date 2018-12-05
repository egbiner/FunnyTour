package cn.imhtb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;

    private Integer pid;

    private String ip;

    private Integer userId;

    private Integer essayId;

    private String content;

    private Date createTime;

    private Date updateTime;

}