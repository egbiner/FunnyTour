package cn.imhtb.pojo;

import lombok.Data;

@Data
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

}