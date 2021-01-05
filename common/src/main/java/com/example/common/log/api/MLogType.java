package com.example.common.log.api;

public enum MLogType {
    VERBOSE(0), INFO(1), DEBUG(2), WARNING(3), ERROR(4);
    public int type;

    MLogType(int type) {
        this.type = type;
    }

}
