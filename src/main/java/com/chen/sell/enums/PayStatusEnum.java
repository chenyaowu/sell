package com.chen.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0,"等待状态"),
    SUCCESS(1,"成功支付")

    ;


    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
