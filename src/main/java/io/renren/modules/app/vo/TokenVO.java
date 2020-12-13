package io.renren.modules.app.vo;

import lombok.Data;

@Data
public class TokenVO {

    private Integer userId;

    private String openid;

    private Integer type;

    private Integer status;

    private Integer areaId;
}