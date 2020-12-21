package io.renren.modules.sys.vo;


import lombok.Data;

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

    private Integer cost;

    private String description;
}