package io.renren.modules.sys.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SysServiceInfoVO {

    private Long orderId;
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
}