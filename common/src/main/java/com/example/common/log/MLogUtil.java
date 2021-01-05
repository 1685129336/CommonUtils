package com.example.common.log;

import com.example.common.log.api.ILogAPi;
import com.example.common.log.api.MLogType;
import com.example.common.log.impl.MLogcat;

import java.util.ArrayList;
import java.util.List;

public class MLogUtil {

    private List<ILogAPi> logs;
    private boolean debug;
    private MLogType minType;
    private MLogType commonType;
    private boolean exception;

    public MLogUtil(List<ILogAPi> logs, boolean debug, MLogType minType, MLogType commonType, boolean exception) {
        this.logs = logs;
        this.debug = debug;
        this.minType = minType;
        this.commonType = commonType;
        this.exception = exception;
        //是否要开启全局异常捕获打印
        if(exception){isException();}
    }

    public void print(String msg) {
        show("user",msg,commonType);
    }

    public void print(String msg, MLogType type) {
        show("user",msg,commonType);
    }

    public void print(String tag, String msg) {
        show(tag,msg,commonType);
    }

    public void print(String tag, String msg, MLogType type) {
        show(tag, msg, type);
    }

    private void isException() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            show("error", "全局异常" + e.getMessage(), MLogType.ERROR);
        });
    }

    private void show(String tag, String msg, MLogType type) {
        if (debug) {
            for (ILogAPi log : logs) {
                if (type.type >= minType.type) {
                    log.logOutput(tag, msg, type);
                }
            }
        }
    }
    public static class Builder{

        private List<ILogAPi> logs;
        private boolean debug = false;
        private MLogType minType = MLogType.VERBOSE;
        private boolean exception = false;

        public Builder() {
            logs = new ArrayList<>();
        }

        public Builder addLog(ILogAPi log){
            logs.add(log);
            return this;
        }
        public Builder isDebug(boolean debug){
            this.debug = debug;
            return this;
        }
        public Builder exception(boolean exception){
            this.exception = exception;
            return this;
        }
        public Builder minType(MLogType type){
            minType = type;
            return this;
        }

        public MLogUtil build(){
            if(logs.size()==0){
                logs.add(new MLogcat());
            }
            return new MLogUtil(logs,debug,minType,minType,exception);
        }
    }
}
