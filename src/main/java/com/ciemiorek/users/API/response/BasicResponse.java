package com.ciemiorek.users.API.response;

import com.ciemiorek.users.API.type.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponse {

    private String responseMsg;
    private String errorCode;
    private String errorMsg;
    private ResponseStatus status;

    public BasicResponse() {
    }

    public BasicResponse(String responseMsg) {
        this.responseMsg = responseMsg;
        this.status = ResponseStatus.SUCCESS;
    }

    public BasicResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = ResponseStatus.ERROR;
    }
}
