package io.renren.modules.sys.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysServiceVO {

    private Integer id;
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 维修员
     */
    private String serviceName;
    /**
     * 问题描述
     */
    private String problemDescription;
    /**
     * 维修状态
     */
    private Integer serviceStatus;
    /**
     * 服务类型
     */
    private Integer serviceType;
    /**
     * 维修费用
     */
    private BigDecimal serviceAmount;
    /**
     * 售后垫付
     */
    private BigDecimal serviceAdvanced;
    /**
     * 垫付说明
     */
    private String advancedRemark;
    /**
     * 备注
     */
    private String remark;
    /**
     * 客户名
     */
    private String customerName;
    /**
     * 手机号
     */
    private String customerMobile;
    /**
     * 地址
     */
    private String customerAddress;

    private String productName;
    /**
     * 预约时间
     */
    private Date appointmentTime;
    /**
     * 完成时间
     */
    private Date finishedTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新时间
     */
    private String areaName;
}