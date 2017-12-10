package com.example.haikal.materialdesign2.SoundRecorder.Fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.haikal.materialdesign2.BuildConfig;
import com.example.haikal.materialdesign2.R;
import com.example.haikal.materialdesign2.SoundRecorder.Fragment.LicensesFragment;
import com.example.haikal.materialdesign2.SoundRecorder.MySharedPreferences;
import com.example.haikal.materialdesign2.SoundRecorder.activities.SettingsPrefActivity;

/**
 * Created by haikal on 04/12/2017.
 */

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference highQualityPref = (CheckBoxPreference)findPreference(getResources().getString(R.string.pref_high_quality_key));
        highQualityPref.setChecked(MySharedPreferences.getPrefHighQuality(getActivity()));
        highQualityPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                MySharedPreferences.setPrefHighQuality(getActivity(), (boolean)o);
                return true;
            }
        });

        Preference preference = findPreference(getString(R.string.pref_about_key));
        preference.setSummary(getString(R.string.pref_about_desc, BuildConfig.VERSION_NAME));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //call Fragment Dialog view
                LicensesFragment licensesFragment = new LicensesFragment();
                licensesFragment.show(((SettingsPrefActivity)getActivity()).getSupportFragmentManager().beginTransaction(), "dialog_licenses");
                return true;
            }
        });
    }
}
