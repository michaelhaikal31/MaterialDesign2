package com.example.haikal.materialdesign2.AndroidSpeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haikal.materialdesign2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech;
    private Button BtnSpeech;
    private EditText InputText;
private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textToSpeech = new TextToSpeech(this, this);
        InputText = (EditText) findViewById(R.id.EditTextSpeech);
        BtnSpeech = (Button) findViewById(R.id.btn_scpeech);
        BtnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeakUp();
            }
        });

        adView = (AdView)findViewById(R.id.admod_Speech);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","This languages is not Supported");
                Toasterror("This languages is not Supported");
            }else {
                BtnSpeech.setEnabled(true);
                SpeakUp();
            }
        }else {
            Log.e("TSS","Initilization Failed");
            Toasterror("Initilization Failed");
        }
    }

    private void Toasterror(String pns) {
        Toast.makeText(getApplicationContext(),pns, Toast.LENGTH_SHORT).show();
    }

    private void SpeakUp() {
        String TextString = InputText.getText().toString();
        textToSpeech.speak(TextString, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
