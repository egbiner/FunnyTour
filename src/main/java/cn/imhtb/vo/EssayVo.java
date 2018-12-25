package cn.imhtb.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EssayVo implements Serializable{

    private Integer id;

    private Integer userId;

    private String username;

    private String userAvatar;

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

    private Integer commentNum;

    private Boolean booleanVote;
}
