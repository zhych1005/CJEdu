package io.renren.modules.sys.entity;


import lombok.Data;

@Data
public class SubjectEntity {

    private Integer subId;

    private Integer stuId;

    private String subName;

    private Integer subTotal;

    private Integer subSurplus;

    private Integer subUse;
}