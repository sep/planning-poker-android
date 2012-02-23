package com.sep.planningpoker.activities;

import com.sep.planningpoker.application.Actions;
import com.sep.planningpoker.application.ApplicationPreferences;

import com.sep.planningpoker.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PlanningPokerActivity extends Activity{
    private ApplicationPreferences mAppPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent().setAction(Actions.SETTINGS));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public ApplicationPreferences getAppPreferences() {
        if (mAppPreferences == null) {
            mAppPreferences = new ApplicationPreferences(this);
        }
        return mAppPreferences;
    }
    
    public void setAppPreferences(ApplicationPreferences appPrefs) {
        mAppPreferences  = appPrefs;
    }
}
