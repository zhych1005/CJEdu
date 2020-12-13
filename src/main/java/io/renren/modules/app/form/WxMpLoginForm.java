package io.renren.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "微信公众号登录表单")
public class WxMpLoginForm {
        @ApiModelProperty(value = "临时登陆凭证")
        @NotBlank(message="等待刷新后重试...")
        private String code;
}