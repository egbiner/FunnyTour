package cn.imhtb.vo;

import lombok.Data;

@Data
public class BaiduResult {
    public BaiduLocation location;
    public Integer precise;
    public Integer confidence;
    public Integer comprehension;
    public String level;
}
