package io.renren.modules.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    ADMIN(1, "管理员"),

    FINANCE(8, "财务"),

    KEYBOARDER(2, "录单"),

    ASSIGN(3, "派单"),

    DRIVER(5, "司机"),

    SERVICE(6, "售后"),
    ;
    private final Integer code;

    private final String massage;

    UserTypeEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}