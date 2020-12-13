package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * 日志
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("tb_log")
public class LogEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日志ID
     */
    @TableId
    private Integer logId;
    /**
     * 订单的id
     */
    private Long orderId;
    /**
     * 派单员的id
     */
    private Integer assignId;

    /**
     * 司机的id
     */
    private Integer driverId;
    /**
     * 售后的id
     */
    private Integer serviceId;
    /**
     * 录单的id
     */
    private Integer keyboarderId;
    /**
     * 操作内容
     */
    private String operation;
    /**
     * 订单的当前状态
     */
    private Integer orderStatus;
    /**
     * 订单的描述
     */
    private String description;

    private Integer areaId;
}