package com.ciemiorek.users.exception;

import com.ciemiorek.users.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException {
    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
