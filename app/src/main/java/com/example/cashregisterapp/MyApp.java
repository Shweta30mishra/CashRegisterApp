package com.example.cashregisterapp;

import android.app.Application;

public class MyApp extends Application {
    private StoreManager manager = new StoreManager();
    public StoreManager getmanager(){return manager;}
}
