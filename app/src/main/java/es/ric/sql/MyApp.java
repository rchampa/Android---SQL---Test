package es.ric.sql;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    
    private static MyApp INSTANCE;
    
    
    @Override
    public void onCreate() {
            super.onCreate();
            INSTANCE = this;
    }
    
    public static Context getContext() {
            return INSTANCE.getApplicationContext();
    }
}
