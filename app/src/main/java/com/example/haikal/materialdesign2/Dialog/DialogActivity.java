package com.example.haikal.materialdesign2.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.haikal.materialdesign2.ActivityBase.MainActivity;
import com.example.haikal.materialdesign2.R;

public class DialogActivity extends AppCompatActivity {
 private Button dialogbase, dialogwithStyle, dialogmultichoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(DialogActivity.class.getSimpleName());
        dialogbase = (Button) findViewById(R.id.Dialog1);
        dialogbase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBase();
            }
        });
        dialogwithStyle = (Button) findViewById(R.id.Dialog2);
        dialogwithStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogWithStyle();
            }
        });
        dialogmultichoice = (Button) findViewById(R.id.Dialog3);
        dialogmultichoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog3();
            }
        });



    }

    private void Dialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setTitle(R.string.dialogtitle);

        //List of item

        String PositiveText = getString(R.string.PositiveText);
        builder.setPositiveButton(PositiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Dialog OK",Toast.LENGTH_SHORT).show();
            }
        });

        String NegativeText = getString(R.string.NegativeText);
        builder.setNegativeButton(NegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Dialog Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void DialogWithStyle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this, R.style.MyDialogTheme);
        builder.setTitle(getString(R.string.dialogtitle));
        builder.setMessage(getString(R.string.dialogmessage));

        String PositiveText = getString(R.string.PositiveText);
        builder.setPositiveButton(PositiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Dialog OK",Toast.LENGTH_SHORT).show();
            }
        });

        String NegativeText = getString(R.string.NegativeText);
        builder.setNegativeButton(NegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Dialog Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void DialogBase() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setTitle(getString(R.string.dialogtitle));
        builder.setMessage(getString(R.string.dialogmessage));

        String PositiveText = getString(R.string.PositiveText);
        builder.setPositiveButton(PositiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Dialog OK",Toast.LENGTH_SHORT).show();
            }
        });

        String NegativeText = getString(R.string.NegativeText);
        builder.setNegativeButton(NegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Dialog Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
