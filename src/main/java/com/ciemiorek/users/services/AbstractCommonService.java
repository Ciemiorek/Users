package com.ciemiorek.users.services;

import com.ciemiorek.users.common.MsgSource;

public abstract class AbstractCommonService {

    public MsgSource msgSource;

    public AbstractCommonService(MsgSource msgSource) {
        this.msgSource = msgSource;
    }
}
