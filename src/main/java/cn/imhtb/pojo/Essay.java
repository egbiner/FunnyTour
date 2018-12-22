package cn.imhtb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Essay {
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String title;

    private String subTitle;

    private String image;

    private String subImage;

    private String content;

    private String subContent;

    private Date createTime;

    private Date updateTime;

    private String position;

    private Integer view;

    private Integer vote;

    private String city;

}