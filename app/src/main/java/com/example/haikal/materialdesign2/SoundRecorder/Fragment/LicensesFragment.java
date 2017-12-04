package com.example.haikal.materialdesign2.SoundRecorder.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haikal.materialdesign2.R;

public class LicensesFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater dilogInflater = getActivity().getLayoutInflater();
        View openSourceLicensesView = dilogInflater.inflate(R.layout.fragment_licenses, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(getString(R.string.dialog_title_licenses))
                .setView(openSourceLicensesView)
                .setNeutralButton(android.R.string.ok, null);
        return dialogBuilder.create();
    }
}
