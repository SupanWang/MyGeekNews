package com.example.nice.geeknews.util;

import android.content.Context;
import android.util.Log;

import com.example.nice.geeknews.base.Constants;

/**
 * Created by ws on 2019/4/3.
 */

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}
