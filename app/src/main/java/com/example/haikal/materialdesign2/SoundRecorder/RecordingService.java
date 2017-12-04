package com.example.haikal.materialdesign2.SoundRecorder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SoundRecorder.activities.SoundRecorderActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by haikal on 04/12/2017.
 */

public class RecordingService extends Service {
    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath =null;

    private MediaRecorder mRecoder =null;
    private DBHelper mDatabase;

    private long mStaringTimeMillis  =0;
    private long mElapseMillis=0;
    private int mElapseSeconds = 0;

    private OnTimerChangedListener onTimeChangedListener = null;
    private  static  final SimpleDateFormat mTimerFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());

    private Timer mTimer= null;
    private TimerTask mIncrementTimerTask = null;

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
        mDatabase = new DBHelper(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mRecoder != null)
            stopRecoding();
        super.onDestroy();
    }

    public void startRecording(){
        setFileNameAndPath();
        mRecoder = new MediaRecorder();
        mRecoder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecoder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecoder.setOutputFile(mFilePath);
        mRecoder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecoder.setAudioChannels(1);

        if(MySharedPreferences.getPrefHighQuality(this)){
            mRecoder.setAudioSamplingRate(44100);
            mRecoder.setAudioEncoder(192000);
        }

        try {
            mRecoder.prepare();
            mRecoder.start();
            mStaringTimeMillis = System.currentTimeMillis();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setFileNameAndPath(){
        int count = 0;
        File f;
        do {
            count++;
            mFileName = getString(R.string.default_file_name)
                    +"_"+(mDatabase.getDatabaseName());
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += "/SoundRecoder/"+mFileName;

            f = new File(mFilePath);
        }while (f.exists() && !f.isDirectory());
    }

    public void stopRecoding(){
        mRecoder.stop();
        mElapseMillis = (System.currentTimeMillis() - mStaringTimeMillis);
        mRecoder.release();
        Toast.makeText(getApplicationContext(), "Recoding Finish"+" "+mFilePath, Toast.LENGTH_LONG).show();

        //remove notification
        if(mIncrementTimerTask != null){
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }
        mRecoder = null;
        try {

        }catch (Exception e){
            Log.e(LOG_TAG, "exeption",e);
        }
    }

    private void startTimer(){
        mTimer = new Timer();
        mIncrementTimerTask = new TimerTask() {
            @Override
            public void run() {
                mElapseSeconds++;
                if(onTimeChangedListener != null)
                    onTimeChangedListener.onTimerChanged(mElapseSeconds);
                NotificationManager mgr = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                mgr.notify(1, createNotification());
            }
        };
        mTimer.scheduleAtFixedRate(mIncrementTimerTask, 1000, 1000);
    }
    //TODO:
    private Notification createNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_filter_list_green_50_24dp)
                .setContentTitle("Nottification")
                .setContentText(mTimerFormat.format(mElapseSeconds * 1000))
                .setOngoing(true);
        mBuilder.setContentIntent(PendingIntent.getActivities(getApplicationContext(),0,
        new Intent[]{new Intent(getApplicationContext(), SoundRecorderActivity.class)},0));

        return  mBuilder.build();
    }
}
