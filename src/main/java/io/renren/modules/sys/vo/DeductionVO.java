package io.renren.modules.sys.vo;

import lombok.Data;

@Data
public class DeductionVO {

    private Integer stuId;

    private Integer subId;

    private Integer subSurplus;

    private String openId;

    private String description;

    private String stuName;

    private String subName;

    private String level;
}