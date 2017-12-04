package com.example.haikal.materialdesign2.SoundRecorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haikal.materialdesign2.R;
import com.melnykov.fab.FloatingActionButton;

import java.io.File;

/**
 * Created by haikal on 30/11/2017.
 */

public class Fragment_Record extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position = 0;
    private int mRecordPromptCount = 0;
    private Chronometer mChronometer = null;
    private TextView mRecordingPrompt;

    //Recording controls
    private FloatingActionButton mRecordButton = null;
    private Button mPauseButton = null;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    long timeWhenPaused = 0;

    /*for what this metode ? */
    public static Fragment_Record newInstance(int position) {
        Fragment_Record f = new Fragment_Record();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public Fragment_Record() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*what fungtion this? */
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View recordView = inflater.inflate(R.layout.fragment_record, container, false);
        mChronometer = (Chronometer) recordView.findViewById(R.id.idchronometer);
       /*Update Recording Prompt Text */
        mRecordingPrompt = (TextView) recordView.findViewById(R.id.recording_status_text);
        mRecordButton = (FloatingActionButton) recordView.findViewById(R.id.btnRecord);
        mRecordButton.setColorNormal(getResources().getColor(R.color.colorPrimary));
        mRecordButton.setColorPressed(getResources().getColor(R.color.colorPrimaryDark));
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });
        mPauseButton = (Button) recordView.findViewById(R.id.btnPause);
        mPauseButton.setVisibility(View.GONE);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPauseRecord(mPauseRecording);
                mPauseRecording = !mPauseRecording;
            }
        });
        mPauseButton = (Button)recordView.findViewById(R.id.btnPause);
        mPauseButton.setVisibility(View.GONE);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPauseRecord(mPauseRecording);
                mPauseRecording = !mPauseRecording;
            }
        });
        return recordView;
    }

   /*TODO : implement Pause Recoding */
    private void onPauseRecord(boolean pause) {
        if (pause) {
           /*Pause Recoding */
            mPauseButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_grey_300_24dp, 0, 0, 0);
            mRecordingPrompt.setText((String) getString(R.string.resume_recording_button).toUpperCase());
            timeWhenPaused = mChronometer.getBase() - SystemClock.elapsedRealtime();
            mChronometer.stop();
        }else {
           /*resume Recording */
           mPauseButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pause_grey_300_24dp,0,0,0);
           mRecordingPrompt.setText((String)getString(R.string.pause_recording_button).toUpperCase());
           mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
           mChronometer.start();
        }
    }

    private void onRecord(boolean start) {
        Intent intent =new Intent(getActivity(), RecordingService.class);

        if(start){
           /*Start Recoding */
           mRecordButton.setImageResource(R.drawable.ic_pause_grey_300_24dp);
            Toast.makeText(getContext(),R.string.record_pause, Toast.LENGTH_SHORT).show();

            File folder =new File(Environment.getExternalStorageDirectory()+"/SoundRecoder");
            if(!folder.exists()){
                folder.mkdir();
            }

           /*Ctrl + Shift + . is to shot of code */
          /*start Chronometer */
          mChronometer.setBase(SystemClock.elapsedRealtime());
          mChronometer.start();
          mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
              @Override
              public void onChronometerTick(Chronometer chronometer) {
                  if(mRecordPromptCount == 0){
                      mRecordingPrompt.setText(getString(R.string.record_pause)+".");
                  }else if(mRecordPromptCount == 1){
                      mRecordingPrompt.setText(getText(R.string.record_pause)+"..");
                  }else if(mRecordPromptCount == 2){
                      mRecordingPrompt.setText(getText(R.string.record_pause)+"...");
                    mRecordPromptCount = -1;
                  }
                  mRecordPromptCount++;
              }
          });
         /*Start Recoding Service */
         getActivity().startService(intent);
           /*keep Screen on While Recording.. */
           getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

           mRecordingPrompt.setText(getString(R.string.record_pause)+".");
           mRecordPromptCount++;

        }else {
           /*Stop Recording */
           mRecordButton.setImageResource(R.drawable.ic_keyboard_voice_grey_300_24dp);
           mChronometer.stop();
           mChronometer.setBase(SystemClock.elapsedRealtime());
           timeWhenPaused = 0;
           mRecordingPrompt.setText(getString(R.string.pause_recording_button));
          /*Stop Setvice Record */
//           getActivity().stopService(intent);
           getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
