package io.renren.modules.app.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MessageVO {

    private Long orderId;

    private String customerName;

    private String customerMobile;

    private String customerAddress;

    private String productName;

    private String description;

    private String remark;

    private Date appointmentTime;

    private BigDecimal productAmount;

    private Integer agencyFund;

    private Date deliveryTime;
}