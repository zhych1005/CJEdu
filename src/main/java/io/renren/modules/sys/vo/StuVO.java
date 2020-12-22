package io.renren.modules.sys.vo;


import lombok.Data;

import java.util.Date;

@Data
public class StuVO {

    private Integer stuId;

    private String stuName;

    private Integer gender;

    private Integer age;

    private String parent;

    private String mobile;

    private String address;

    private String subject;

    private Integer total;

    private Integer subSurplus;

    private Integer subUse;

    private Integer cost;

    private String description;

    private Date createTime;

    private Date updateTime;
}