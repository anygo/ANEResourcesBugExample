// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air.utils;

import android.util.Log;

// Referenced classes of package com.adobe.air.utils:
//            Utils

public class AIRLogger
{

    static boolean g_enableReleaseLogging = false;
    private static String mflag = (new StringBuilder()).append(Utils.GetExternalStorageDirectory()).append("/.AIR/enable_logging").toString();

    public AIRLogger()
    {
    }

    public static void Enable(boolean flag)
    {
        g_enableReleaseLogging = flag;
        Log.v("Release Logging: ", (new StringBuilder()).append("enabled = ").append(g_enableReleaseLogging).toString());
    }

    public static int d(String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.d(s, s1);
        } else
        {
            return 0;
        }
    }

    public static int d(String s, String s1, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.d(s, s1, throwable);
        } else
        {
            return 0;
        }
    }

    public static int e(String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.e(s, s1);
        } else
        {
            return 0;
        }
    }

    public static int e(String s, String s1, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.e(s, s1, throwable);
        } else
        {
            return 0;
        }
    }

    public static int i(String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.i(s, s1);
        } else
        {
            return 0;
        }
    }

    public static int i(String s, String s1, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.i(s, s1, throwable);
        } else
        {
            return 0;
        }
    }

    public static boolean isEnabled()
    {
        return g_enableReleaseLogging;
    }

    public static boolean isLoggable(String s, int j)
    {
        if (g_enableReleaseLogging)
        {
            return Log.isLoggable(s, j);
        } else
        {
            return false;
        }
    }

    public static int println(int j, String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.println(j, s, s1);
        } else
        {
            return 0;
        }
    }

    public static int v(String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.v(s, s1);
        } else
        {
            return 0;
        }
    }

    public static int v(String s, String s1, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.v(s, s1, throwable);
        } else
        {
            return 0;
        }
    }

    public static int w(String s, String s1)
    {
        if (g_enableReleaseLogging)
        {
            return Log.w(s, s1);
        } else
        {
            return 0;
        }
    }

    public static int w(String s, String s1, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.w(s, s1, throwable);
        } else
        {
            return 0;
        }
    }

    public static int w(String s, Throwable throwable)
    {
        if (g_enableReleaseLogging)
        {
            return Log.w(s, throwable);
        } else
        {
            return 0;
        }
    }

}
