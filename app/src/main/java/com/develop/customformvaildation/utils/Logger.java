package com.develop.customformvaildation.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.develop.customformvaildation.BuildConfig;

public class Logger {
    private static String className = "Logger";
    private static String methodName = "null";
    private static int lineNumber = 0;

    private static void getMethodNames(StackTraceElement[] elements) {
        className = elements[1].getFileName();
        methodName = elements[1].getMethodName();
        lineNumber = elements[1].getLineNumber();
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    public static void getBundleToString(Bundle bundle) {
        String string = "Bundle{\n";
        for (String key : bundle.keySet()) {
            string += " " + key + " => " + bundle.get(key) + ";\n";
        }
        string += " }Bundle\n";
        Logger.e(string);
    }

    public static void showAlert(final Context context, final String message) {
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                e(message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void v(String message) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.v("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }

    public static void d(String message) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.d("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }

    public static void i(String message) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.i("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }

    public static void w(String message) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.w("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }

    public static void e(String message) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.e("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }

    public static void e(String message, Throwable throwable) {
        if (!isDebuggable()) {
            return;
        }
        getMethodNames(new Throwable().getStackTrace());
        Log.e("Tag " + className + "." + methodName + "():" + lineNumber, message);
    }
}