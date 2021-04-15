package com.example.heroguessr;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TKtvbq1k7GwUCan8xvx6EDrmJFxFLEu99AvRYHJN")
                .clientKey("oZnUczpQzhvXuTqPVHdTQXoYKTZIGLkxWioMDehj")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}