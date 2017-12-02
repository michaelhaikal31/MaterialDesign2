package com.example.haikal.materialdesign2.SoundRecorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.haikal.materialdesign2.R;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by haikal on 30/11/2017.
 */

public class RecordingService extends Service {
    private static final String LOG_TAG = "RecordingService";

    private String mFileName=null;
    private String mFilePath=null;

    private MediaRecorder mRecoder = null;
    //private DBHelper

    private long mStringTimeMillis =0;
    private long mElapsedMillis = 0;
    private int mElapsedSeconds = 0;

    private TimerTask mIncrementTimerTask = null;
    private TimePicker.OnTimeChangedListener onTimeChangedListener = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    public interface OnTimerChangedListener{
        void onTimerChanged(int seconds);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //mDatabse = new DBHelper(gerApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecoding();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mRecoder != null){
            stopRecoding();    
        }
        super.onDestroy();
    }

    private void stopRecoding() {
        mRecoder.stop();
        mElapsedMillis = (System.currentTimeMillis() - mStringTimeMillis);
        mRecoder.release();
        Toast.makeText(this,"Toas Recording Finis", Toast.LENGTH_SHORT).show();

       /* Remove Notification*/
       if(mIncrementTimerTask != null){
           mIncrementTimerTask.cancel();
           mIncrementTimerTask = null;
       }

    }

    private void startRecoding() {
        setFileNameAndPath();

        mRecoder = new MediaRecorder();
        mRecoder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecoder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecoder.setOutputFile(mFilePath);
        mRecoder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecoder.setAudioChannels(1);
        if(MySharedPreferences.getPrefHighQuality(this)){
            mRecoder.setAudioSamplingRate(44100);
            mRecoder.setAudioEncodingBitRate(192000);
        }
        try {
            mRecoder.prepare();
            mRecoder.start();
            mStringTimeMillis = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setFileNameAndPath() {
        int count =0;
        File f;
        do {
            count++;
           // mFileName = getString(R.string.app_name)+"_"+(mD);

            f =new File(mFilePath);
        }while (f.exists() && !f.isDirectory());

    }

}
