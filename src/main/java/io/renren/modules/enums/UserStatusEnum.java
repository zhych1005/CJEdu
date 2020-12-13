package io.renren.modules.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    FREEZE_USER(0, "冻结用户"),

    NORMAL(1, "正常用户"),
    ;
    private final Integer code;

    private final String massage;

    UserStatusEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}