package com.ldz.util;

import com.ldz.system.ExitBoxSystem;
import com.ldz.system.InstantDisplayerSystem;
import com.ldz.system.OnActionSystem;
import com.ldz.system.PopupSystem;

/**
 * Created by Loic on 29/09/2017.
 */
public class InputProcessUtil {

    public static void disableInputSystems() {
        setProcessToInputSystem(false);
    }


    public static void enableInputSystems() {
        setProcessToInputSystem(true);
    }

    private static void setProcessToInputSystem(boolean value) {
        PopupSystem.getInstance(null).setProcessing(value);
        ExitBoxSystem.getInstance(null).setProcessing(value);
        OnActionSystem.getInstance(null).setProcessing(value);
        InstantDisplayerSystem.getInstance(null).setProcessing(value);
    }

}
