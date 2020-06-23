package com.ciemiorek.users.exception;

import com.ciemiorek.users.common.ConstErrorMsg;

public class CommonConflictException extends CommonException {
    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
