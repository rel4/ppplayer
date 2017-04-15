package com.xigua.p2p;

import android.util.Log;

public final class KJLoger {
    public static boolean DEBUG_LOG;
    public static boolean IS_DEBUG;
    public static boolean SHOW_ACTIVITY_STATE;

    static {
        KJLoger.IS_DEBUG = true;
        KJLoger.DEBUG_LOG = true;
        KJLoger.SHOW_ACTIVITY_STATE = true;
    }

    public KJLoger() {
        super();
    }

    public static final void debug(String arg1) {
        if(KJLoger.IS_DEBUG) {
            Log.i("debug", arg1);
        }
    }

    public static final void debug(String arg1, Throwable arg2) {
        if(KJLoger.IS_DEBUG) {
            Log.i("debug", arg1, arg2);
        }
    }

    public static final void debug(String arg1, Object[] arg2) {
        KJLoger.debug(String.format(arg1, arg2));
    }

    public static final void debugLog(String arg3, String arg4) {
        if(KJLoger.DEBUG_LOG) {
            Log.d("debug", String.valueOf(arg3) + arg4);
        }
    }

    public static final void exception(Exception arg1) {
        if(KJLoger.DEBUG_LOG) {
            arg1.printStackTrace();
        }
    }

    public static final void log(String arg0, String arg1) {
        KJLoger.debugLog(arg0, arg1);
    }

    public static final void openActivityState(boolean arg0) {
        KJLoger.SHOW_ACTIVITY_STATE = arg0;
    }

    public static final void openDebutLog(boolean arg0) {
        KJLoger.IS_DEBUG = arg0;
        KJLoger.DEBUG_LOG = arg0;
    }

    public static final void state(String arg3, String arg4) {
        if(KJLoger.SHOW_ACTIVITY_STATE) {
            Log.d("activity_state", String.valueOf(arg3) + arg4);
        }
    }
}

