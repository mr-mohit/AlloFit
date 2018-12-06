package com.example.mv.allofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class MainPreferenceFragment extends PreferenceFragment {
    private static final String TAG = PreferenceSetting.class.getSimpleName();
    SharedPreferences mPreferences;
    String prefFile = "com.example.mv.allofit.goal_settings";
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_main);
        mPreferences=this.getActivity().getSharedPreferences(prefFile,Context.MODE_PRIVATE);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.Key_Goal)));
        // feedback preference click listener
        Preference myPref = this.findPreference(getString(R.string.key_send_feedback));
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                sendFeedback(getActivity());
                return true;
            }
        });
    }
    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        //sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
               // getActivity().getSharedPreferences(prefFile,Context.MODE_PRIVATE).getString(preference.getKey(), ""));
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
    }
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

             if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);
            SharedPreferences.Editor editor=mPreferences.edit();
            editor.putInt("goal",Integer.parseInt(stringValue)).apply();
            //CharSequence[] entries=listPreference.getEntries();
               // Toast.makeText(getActivity(), "Value: "+stringValue, Toast.LENGTH_SHORT).show();  <-- For debugging purposes only

                // Set the summary to reflect the new value.
                 preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);

             } else {
                 preference.setSummary(stringValue);
              }
            return true;
        }
    };

    /**
     * Email client intent to send support mail
     * Appends the necessary device information to email body
     * useful when providing support
     */
    public void sendFeedback(Context context) {
        String body = null;
        try {
            int apiLevel = Build.VERSION.SDK_INT;
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            body = "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                    Build.VERSION.RELEASE +"\nAPI Level: "+apiLevel+ "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                    "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,""+e);
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mohit_verma96@yahoo.in"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Query from android app");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
    }
}
