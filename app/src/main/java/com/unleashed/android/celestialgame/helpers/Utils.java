package com.unleashed.android.celestialgame.helpers;

import android.widget.Toast;

import com.unleashed.android.celestialgame.application.CelestialApplication;

/**
 * Created by sudhanshu on 15/01/17.
 */

public class Utils {

    public static void displayPopup(String s) {
        Toast.makeText(CelestialApplication.getContext(), s, Toast.LENGTH_LONG).show();
    }
}
