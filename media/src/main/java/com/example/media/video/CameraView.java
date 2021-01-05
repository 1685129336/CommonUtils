package com.example.media.video;

import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private MediaRecorder mRecorder;
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private boolean flag = false;

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void prepare() throws IOException {
        if (mHolder == null) {//holder对象初始化，回调
            mHolder = getHolder();
            mHolder.addCallback(this);
        }
        if (mCamera == null) {//相机初始化
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            mCamera.setDisplayOrientation(90);
            try {
                mCamera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
            mCamera.unlock();
        }
        //mediaRecorder对象初始化
        mRecorder = new MediaRecorder();
        mRecorder.setCamera(mCamera);
        mRecorder.setOrientationHint(90);
        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P));
        mRecorder.setPreviewDisplay(mHolder.getSurface());
        mRecorder.setOutputFile(getPath());
        mRecorder.prepare();
        flag = true;
    }

    public void start() {//开始
        try {
            if (!flag) {
                prepare();
            }
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            reset();
        }
    }

    public void stop() {//结束
        reset();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mHolder = holder;
        try {
            prepare();//准备
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        reset();
    }

    public String getPath() {//时间戳
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String dataTime = format.format(date);
        String path = "/sdcard/DCIM/Camera/视频/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path + "VID_" + dataTime + ".mp4";
    }

    private void reset() {//释放
        mHolder = null;
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
        }
        flag = false;
    }

}
