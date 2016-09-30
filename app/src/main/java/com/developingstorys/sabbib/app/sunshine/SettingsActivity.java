package com.developingstorys.sabbib.app.sunshine;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by PC-Charlie on 10/1/2016.
 */
public class SettingsActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.settings_container, new SettingsFragment(), "SETTING_FRAGMENT").commit();
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
