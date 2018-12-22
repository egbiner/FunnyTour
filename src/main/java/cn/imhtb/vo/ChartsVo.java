package cn.imhtb.vo;

import lombok.Data;

@Data
public class ChartsVo {
    String titleText;
    String titleSubText;
    String seriesName;
    String[] category;
    Integer[] data;
}
