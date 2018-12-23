package cn.imhtb.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HotEssayVo {
    //essay_id
    private Integer id;
    private String title;
    private Integer number;
    private String city;

    private Integer essayOp;
    private Date createTime;
}
