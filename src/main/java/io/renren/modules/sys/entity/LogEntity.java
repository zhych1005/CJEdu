package io.renren.modules.sys.entity;


import lombok.Data;

@Data
public class LogEntity {

    private Integer logId;

    private Integer stuId;

    private Integer subId;

    private String operation;

    private String description;
}