package com.sep.planningpoker.application;

import com.sep.planningpoker.application.preferences.PreferenceKeys;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ApplicationPreferences {

    private static final String SHARED_PREFERENCE_KEY = "Amazon_Settings";

    private SharedPreferences settings;
    private Editor editor;

    public ApplicationPreferences(Context context) {
        settings = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public boolean getIsFirstTimeLaunched() {
        return getBoolean(PreferenceKeys.IS_FIRST_LAUNCH, true);
    }

    public void setIsFirstTimeLaunched(boolean value) {
        storeBoolean(PreferenceKeys.IS_FIRST_LAUNCH, value);
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }

    private void storeBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

}
