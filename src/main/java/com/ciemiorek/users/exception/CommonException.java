package com.ciemiorek.users.exception;

import com.ciemiorek.users.common.ConstErrorMsg;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private ConstErrorMsg constErrorMsg;

    public CommonException(ConstErrorMsg constErrorMsg) {
        this.constErrorMsg = constErrorMsg;
    }

}
