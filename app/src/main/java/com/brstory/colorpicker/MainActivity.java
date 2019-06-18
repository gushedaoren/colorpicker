package com.brstory.colorpicker;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        ((ColorPickerPreference) findPreference("color2")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
                return true;
            }

        });
        ((ColorPickerPreference) findPreference("color2")).setAlphaSliderEnabled(true);
    }
}
