package com.example.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.media.audio.AudioUtil
import com.example.media.zip.ZipUtil
import kotlinx.android.synthetic.main.activity_media.*

class MediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        start_audio.setOnClickListener {
            Toast.makeText(this, "开始录制", Toast.LENGTH_SHORT).show()
//            AudioUtil.getInstance().start()
//            camera.start()
//            ZipUtil.ZipFolder("/sdcard/Download/test","/sdcard/Download")

            ZipUtil.unZipFolder("/sdcard/neoRecorder/MIC_20201221_090741.mp31608512866079.zip","/sdcard/neoRecorder/MIC_20201221_090741.mp3")
        }
        stop_audio.setOnClickListener {
//            Toast.makeText(this, "停止录制", Toast.LENGTH_SHORT).show()
//            AudioUtil.getInstance().stop()
//            camera.stop()
        }

        AudioUtil.getInstance().setRecording(object : AudioUtil.Recording{
            override fun error(msg: String?) {
                Toast.makeText(this@MediaActivity, "录制失败", Toast.LENGTH_SHORT).show()
            }

            override fun success(msg: String?, path: String?) {
                Toast.makeText(this@MediaActivity, "录制完成", Toast.LENGTH_SHORT).show()
                ZipUtil.ZipFolder(path,path)
            }

        })



    }
}