package com.ciemiorek.users.common;

import lombok.Getter;

@Getter
public class ConstErrorMsg {

    private final String errorCode;
    private final String errorMsg;

    public ConstErrorMsg(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


}
