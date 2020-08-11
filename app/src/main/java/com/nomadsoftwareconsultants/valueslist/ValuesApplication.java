package com.nomadsoftwareconsultants.valueslist;

import android.app.Application;
import android.util.Log;

import com.nomadsoftwareconsultants.valueslist.parse.Values;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

public class ValuesApplication extends Application {

    private static final String TAG = ValuesApplication.class.getSimpleName();

    private static ValuesApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        // add Parse subclasses
        ParseObject.registerSubclass(Values.class);

        // back to the future - Parse!
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .enableLocalDataStore()
                .build()
        );

        ParseInstallation pI = ParseInstallation.getCurrentInstallation();
        pI.saveInBackground();
        Log.i(TAG, "Parse InstallationID ==> " + pI.getInstallationId());
    }
}
