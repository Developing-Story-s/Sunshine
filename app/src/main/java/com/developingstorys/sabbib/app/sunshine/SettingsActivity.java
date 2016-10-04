package com.developingstorys.sabbib.app.sunshine;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by PC-Charlie on 10/1/2016.
 */
public class SettingsActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().replace(R.id.settings_container, new SettingsFragment(), "Settings_Fragment").commit();
    }



    /**
     * fix units!!
     */


    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.app_pref);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_units_key)));
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                                    .getString(preference.getKey(), ""));
        }//bindPreferenceSummaryToValue

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String value = newValue.toString();
            if(preference instanceof ListPreference){
                ListPreference lPreference = (ListPreference) preference;
                String s = (String) lPreference.getEntry();
                Log.i("Settings-Active", "onPreferenceChange: " + s);
                int prefIndex = lPreference.findIndexOfValue(value);
                if (prefIndex >= 0)
                    preference.setSummary(lPreference.getEntries()[prefIndex]);
                else
                    preference.setSummary(value);
            }//end if()
            return true;
        }//end ofPreferenceChange()
    }

}
