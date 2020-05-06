package com.qilong.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6cStUUdRDHeMGuGB75oiPpuF1EA0Muwd0kuHL3hc")
                // if defined
                .clientKey("eNk5ey0xGmSmxtDOZP5AIMhA4ad2gQreut5bmgVZ")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
