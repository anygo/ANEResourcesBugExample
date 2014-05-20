// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import java.util.concurrent.Semaphore;

// Referenced classes of package com.adobe.air:
//            AIRWindowSurfaceView, DrawThread, AndroidActivityWrapper

public class AIRWindowThreadedSurfaceView extends AIRWindowSurfaceView
{

    public final Semaphore drawSemaphore = new Semaphore(0);
    private DrawThread thread;

    public AIRWindowThreadedSurfaceView(Context context, AndroidActivityWrapper androidactivitywrapper)
    {
        super(context, androidactivitywrapper);
        thread = null;
        Init(context);
    }

    protected void Init(Context context)
    {
        thread = new DrawThread(this, mSurfaceHolder, context);
    }

    public void drawBitmap(int i, int j, int k, int l, Bitmap bitmap)
    {
        thread.requestDraw(i, j, k, l, bitmap);
        try
        {
            drawSemaphore.acquire();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void drawBitmap(int i, int j, int k, int l, Bitmap bitmap, int i1, int j1, 
            int k1, int l1, boolean flag, int i2)
    {
        thread.requestDraw(i, j, k, l, bitmap, i1, j1, k1, l1, flag, i2);
        try
        {
            drawSemaphore.acquire();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        super.surfaceChanged(surfaceholder, i, j, k);
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        super.surfaceCreated(surfaceholder);
        thread.setRunning(true);
        if (thread.isAlive())
        {
            break MISSING_BLOCK_LABEL_30;
        }
        thread.start();
        return;
        Exception exception;
        exception;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        boolean flag;
        super.surfaceDestroyed(surfaceholder);
        flag = true;
        thread.setRunning(false);
_L2:
        if (!flag || !thread.isAlive())
        {
            break MISSING_BLOCK_LABEL_41;
        }
        thread.join();
        flag = false;
        continue; /* Loop/switch isn't completed */
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        if (true) goto _L2; else goto _L1
_L1:
    }
}