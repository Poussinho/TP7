//package com.example.pereiraan.session6;
//
//import android.app.Application;
//import android.content.Context;
//
///**
// * Surcharge de APPLICATION (créé à l'éxécution de l'application)
// * Permettant l'accès au context statiquement depuis un Fragment
// */
//public class DemoApplication extends Application {
//    private static Context context;
//
//    public void onCreate() {
//        super.onCreate();
//        DemoApplication.context = getApplicationContext();
//    }
//
//    public static Context getContext() {
//        return DemoApplication.context;
//    }
//}
