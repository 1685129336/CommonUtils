package com.example.common.log.impl;

import com.example.common.log.api.ILogAPi;
import com.example.common.log.api.MLogType;

public class MLogConsole implements ILogAPi {
    /**
     * 控制台打印
     * @param tag
     * @param msg
     * @param type
     */
    @Override
    public void logOutput(String tag, String msg, MLogType type) {
        System.out.println(type+"--"+tag+"-->"+msg);
    }
}
