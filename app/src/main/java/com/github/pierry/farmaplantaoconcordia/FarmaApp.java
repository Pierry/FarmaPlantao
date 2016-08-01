package com.github.pierry.farmaplantaoconcordia;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class FarmaApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());
  }
}
