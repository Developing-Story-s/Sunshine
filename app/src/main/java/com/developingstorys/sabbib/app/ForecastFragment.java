package com.developingstorys.sabbib.app;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by PC-Charlie on 9/26/2016.
 */

public class ForecastFragment extends Fragment {

    private final String TAG = ForecastFragment.class.getSimpleName();
    String[] forecastArray;
    ListView listView;

    public ForecastFragment(){}
    ArrayAdapter<String> forecastAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            updateWeather();
            return true;
        }
        if(id == R.id.action_search){
            getSearchWeather();
            return true;
        }
        if(id ==  R.id.action_settings){
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWeather();
    }

    private void updateWeather(){
        FetchWeatherTask fwt = new FetchWeatherTask(getContext(), listView);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String Location = pref.getString(
                getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        /**
         * fix units!!
         */
        fwt.execute(Location);
    }
    private void getSearchWeather(){
        AlertDialog.Builder sDialog = new AlertDialog.Builder(getActivity());
        sDialog.setTitle("Enter City");
        final EditText sText = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        sText.setLayoutParams(lp);

        sDialog.setView(sText);
        sDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        String string = "Sydney";
                        string = sText.getText().toString();
                        new FetchWeatherTask(getContext(), listView).execute(string);
                    }
                }
        );
        sDialog.show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        forecastArray = new String[]{
                " ",
                " "
        };

        Arrays.asList(forecastArray);

        forecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        forecastArray);

        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(forecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String f = forecastAdapter.getItem(position);
                Intent detailIntent = new Intent(getActivity(), DetailsActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, f);
                startActivity(detailIntent);
            }
        });

        Button b = (Button) rootView.findViewById(R.id.button);


        return rootView;
    }

}


