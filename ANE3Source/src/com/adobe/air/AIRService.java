// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.SystemClock;
import com.adobe.air.utils.Utils;

// Referenced classes of package com.adobe.air:
//            Entrypoints

public class AIRService extends Service
{
    public class DummyBinder extends Binder
    {

        final AIRService this$0;

        AIRService getService()
        {
            return AIRService.this;
        }

        public DummyBinder()
        {
            this$0 = AIRService.this;
            super();
        }
    }


    public static final String EXTRA_DOWNLOAD_TIME = "com.adobe.air.DownloadConfigCompleteTime";
    public static final String INTENT_CONFIG_DOWNLOADED = "com.adobe.air.DownloadConfigComplete";
    public static final String INTENT_DOWNLOAD_CONFIG = "com.adobe.air.DownloadConfig";
    private static final String LOG_TAG = "AIRService";
    private static AIRService sServiceInstance = null;
    private final IBinder mBinder = new DummyBinder();
    private Entrypoints mEntrypoints;
    private boolean mPlayerInitialized;

    public AIRService()
    {
        mEntrypoints = null;
        mPlayerInitialized = false;
    }

    public static boolean IsRunningInServiceContext()
    {
        return getAIRService() != null;
    }

    private void downloadConfig()
    {
        if (!mPlayerInitialized)
        {
            mPlayerInitialized = true;
            if (!mEntrypoints.EntryDownloadConfig(getApplicationContext(), Utils.getRuntimePackageName()))
            {
                downloadDone(false);
            }
        }
    }

    public static AIRService getAIRService()
    {
        return sServiceInstance;
    }

    private Entrypoints getEntrypoints()
    {
        return mEntrypoints;
    }

    public void downloadDone(boolean flag)
    {
        if (flag)
        {
            Intent intent = new Intent("com.adobe.air.DownloadConfigComplete");
            intent.putExtra("com.adobe.air.DownloadConfigCompleteTime", SystemClock.uptimeMillis());
            sendStickyBroadcast(intent);
        }
        stopSelf();
    }

    public Context getContext()
    {
        getEntrypoints();
        return Entrypoints.s_context;
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
        super.onCreate();
        mEntrypoints = new Entrypoints();
        sServiceInstance = this;
        Utils.setRuntimePackageName(getApplicationContext().getPackageName());
    }

    public void onDestroy()
    {
        super.onDestroy();
        sServiceInstance = null;
        if (mPlayerInitialized)
        {
            mEntrypoints.EntryStopRuntime();
            mPlayerInitialized = false;
        }
        if (!Utils.hasCaptiveRuntime())
        {
            Process.killProcess(Process.myPid());
        }
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        if (intent.getAction().equals("com.adobe.air.DownloadConfig"))
        {
            downloadConfig();
        }
        return 2;
    }

}
