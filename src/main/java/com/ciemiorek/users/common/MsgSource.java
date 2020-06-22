package com.ciemiorek.users.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgSource {

    private final String OK001;

    public MsgSource(@Value("${common.ok.msg.ok001}") String ok001MsgValue) {
        OK001 = ok001MsgValue;

    }
}
