package io.renren.modules.sys.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LogVO {

    private Integer logId;

    private String stuName;

    private String subName;

    private String operation;

    private String description;

    private Integer status;

    private String level;

    private Date createTime;
}