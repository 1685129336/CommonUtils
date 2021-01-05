package com.example.common.log.impl;

import com.example.common.log.api.ILogAPi;
import com.example.common.log.api.MLogType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 本地文件输出
 */
public class MLogFile implements ILogAPi {
    private File file;
    private PrintWriter writer;

    public MLogFile(File file) {
        this.file = file;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void logOutput(String tag, String msg, MLogType type) {
        if(writer==null){
            try {
                writer = new PrintWriter(new FileOutputStream(file,true),true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(writer!=null){
            writer.println(type+"--"+tag+"-->"+msg);
        }

    }
}
