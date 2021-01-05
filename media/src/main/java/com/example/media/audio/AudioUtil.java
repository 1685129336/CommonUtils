package com.example.media.audio;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioUtil {
    private static AudioUtil util = new AudioUtil();
    public static AudioUtil getInstance(){
        return util;
    }

    private AudioUtil() {
    }

    private MediaRecorder mRecorder;
    private String audioPath = "";
    private Recording recording;
    private void prepare() throws IOException {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频源
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//输出格式
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);//编码格式
        mRecorder.setAudioSamplingRate(8000);//采样率
        audioPath = path();
        mRecorder.setOutputFile(audioPath);
        mRecorder.prepare();
    }


    public void start(){
        try {
            prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            reset();
            if(recording!=null){
                recording.error("发生异常");
            }
        }

    }
    public void stop(){
        reset();
        if(recording!=null){
            recording.success("录制完成",audioPath);
        }
    }
    private void reset(){
        if(mRecorder!=null){
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
        }
    }
    private String path() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String dataTime = format.format(date);
        String path = "/sdcard/neoRecorder/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path + "MIC_" + dataTime + ".mp3";
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public interface Recording{
        void success(String msg,String path);
        void error(String msg);
    }
}
