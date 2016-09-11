package com.buildingstories.sabbib.sunshine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int i = 0;
        Log.v("Check-: ", "="+ i++ );
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, new PlaceholderFragment())
                    .commit();

        PlaceholderFragment phf = new PlaceholderFragment();
        Log.v("Check-: ", "="+ i++ );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment(){}
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ArrayList<String> forecast = new ArrayList<>();
            int i = 0;
            Log.v("Check: ", "s "+ i++ );

            String[] forecastArray = {
                    "Today-Sunny-28/30",
                    "Tomorrow-foggy-22/24",
                    "Thurs-Rain-18/20",
                    "Fri-Foggy-22/23",
                    "Sat-Sunny-28/30",
                    "Sun-Sunny-29/31"
            };

            List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));

            ArrayAdapter<String> forecastAdapater = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview, weekForecast);

            ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
            listView.setAdapter(forecastAdapater);
            Log.v("Check: ", "s "+ i++ );
            return rootView;
        }
    }

}
