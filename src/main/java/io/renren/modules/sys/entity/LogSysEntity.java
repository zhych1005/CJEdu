package io.renren.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_log")
public class LogSysEntity {

    @TableId
    private Integer logId;

    private Integer stuId;

    private Integer subId;

    private String operation;

    private String description;

    private String level;

    private Integer status;
}