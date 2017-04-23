package com.developingstorys.sabbib.app;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {
    private final String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(savedInstanceState == null)
            getFragmentManager().beginTransaction().replace(R.id.container, new PlaceHolderFragment()).commit();

    }//end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailfragment, menu);
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
        if(item.getItemId() == R.id.action_settings)   startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected()

    public static class PlaceHolderFragment extends Fragment{

        public static final String TAG = PlaceHolderFragment.class.getSimpleName();
        public static final String FORECAST_SHARE_HASHTAG = "#Sunshine";
        private String forecast;

        public PlaceHolderFragment(){ setHasOptionsMenu(true);}

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
            inflater.inflate(R.menu.detailfragment, menu);

            MenuItem item = menu.findItem(R.id.menu_item_share);
            ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

            if(shareActionProvider != null)
                shareActionProvider.setShareIntent(shareIntent());
            else
                Log.d(TAG, "onCreateOptionsMenu: Share failed");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_details, container, false);
            Intent i = getActivity().getIntent();
            TextView tv = (TextView) rootView.findViewById(R.id.weatherINFRO);
            if(i != null && i.hasExtra(Intent.EXTRA_TEXT)){
                forecast = i.getStringExtra(Intent.EXTRA_TEXT);
                tv.setText(forecast);
            }
            tv.setTextSize(40);
            tv.setTypeface(Typeface.SANS_SERIF,Typeface.ITALIC);
            return rootView;
        }

        private Intent shareIntent(){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("type/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, forecast + FORECAST_SHARE_HASHTAG);
            return shareIntent;
        }

    }
}
