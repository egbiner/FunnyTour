package cn.imhtb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EssayOp {
    private Integer id;

    private String ip;

    private Integer userId;

    private Integer essayOp;

    private Integer essayId;

    private Date createTime;

    public EssayOp(Integer essayId, Integer essayOp) {
        this.essayOp = essayOp;
        this.essayId = essayId;
    }

    public EssayOp() {
    }
}