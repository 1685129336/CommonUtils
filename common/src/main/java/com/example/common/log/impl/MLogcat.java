package com.example.common.log.impl;

import android.util.Log;

import com.example.common.log.api.ILogAPi;
import com.example.common.log.api.MLogType;

public class MLogcat implements ILogAPi {
    /**
     * locat打印输出
     * @param tag
     * @param msg
     * @param type
     */
    @Override
    public void logOutput(String tag, String msg, MLogType type) {
        switch (type) {
            case INFO:
                Log.i(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case WARNING:
                Log.w(tag, msg);
                break;
        }
    }
}
