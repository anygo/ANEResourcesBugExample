// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.sixminute.ane1;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

// Referenced classes of package com.sixminute.ane1:
//            ANE1ExtensionContext

public class ANE1Extension
    implements FREExtension
{

    public ANE1Extension()
    {
    }

    public static void DLog(String s)
    {
        Log.i("ANE1", s);
    }

    public FREContext createContext(String s)
    {
        DLog((new StringBuilder()).append("createContext extId: ").append(s).toString());
        return new ANE1ExtensionContext();
    }

    public void dispose()
    {
        DLog("dispose");
    }

    public void initialize()
    {
        DLog("initialize");
    }
}
