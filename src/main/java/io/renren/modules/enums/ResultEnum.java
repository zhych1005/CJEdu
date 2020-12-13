package io.renren.modules.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    AUTH_ERROR(2,"权限不足,请联系管理员!"),

    ASSIGN_PARAM_ERROR(3,"该派单员未登录,不能接收通知!"),

    DRIVER_PARAM_ERROR(5,"该司机未登录,不能接收通知!"),

    FREEZE_USER(6,"账户已冻结!"),
    ;
    private final Integer code;

    private final String massage;

    ResultEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}