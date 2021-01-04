package io.renren.modules.sys.vo;


import lombok.Data;

import java.util.Date;

@Data
public class StuVO {

    private Integer stuId;

    private Integer subId;

    private String stuName;

    private Integer gender;

    private Integer age;

    private String parent;

    private String mobile;

    private String address;

    private String subName;

    private Integer subTotal;

    private Integer subSurplus;

    private Integer subUse;

    private Integer cost;

    private String description;

    private Date createTime;

    private Date updateTime;
}