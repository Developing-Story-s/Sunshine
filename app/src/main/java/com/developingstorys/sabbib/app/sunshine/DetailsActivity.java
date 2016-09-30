package com.developingstorys.sabbib.app.sunshine;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {
    private final String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        TextView textView  = new TextView(this);
//        Intent i = .getIntent();
//        String message = i.getStringExtra("Weather");
//        textView.setTextSize(40);
//        Log.d(new DetailsActivity().TAG, "onCreateView: create()" + message);
//        textView.setText(i.getStringExtra(Intent.EXTRA_TEXT));
//        textView.setTextSize(40);
//        Log.d(new DetailsActivity().TAG, "onCreateView: " + i.getStringExtra(Intent.EXTRA_TEXT));
//        textView.setText(i.getStringExtra("Weather"));

        if(savedInstanceState == null)
            getFragmentManager().beginTransaction().replace(R.id.container, new PlaceHolderFragment()).commit();

    }//end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }//end onCreateOptionsMenu()


    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings)              startActivity(new Intent(this, SettingsActivity.class));
//        return true;
        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected()

    public static class PlaceHolderFragment extends Fragment{
        public PlaceHolderFragment(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_details, container, false);
            Intent i = getActivity().getIntent();
            String mess = i.getStringExtra(Intent.EXTRA_TEXT);
            TextView tv = (TextView) rootView.findViewById(R.id.weatherINFRO);
            tv.setTextSize(40);
            tv.setText(mess);
            tv.setTypeface(Typeface.SANS_SERIF,Typeface.ITALIC);
            return rootView;
        }
    }
}
