package cn.imhtb.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User{
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private String name;

    private Integer level;

    private String introduce;

    private String sex;

    private String avatar;

}