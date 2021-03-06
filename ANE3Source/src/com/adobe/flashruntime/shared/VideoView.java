// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.flashruntime.shared;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoView extends SurfaceView
{

    private static final String TAG = "VideoSurfaceView";
    private boolean mAmCreated;
    private long mCPPInstance;
    private Context mContext;
    private boolean mPlanePositionSet;
    private Surface mSurface;
    private int mXmax;
    private int mXmin;
    private int mYmax;
    private int mYmin;

    public VideoView(Context context)
    {
        super(context);
        mXmin = 0;
        mYmin = 0;
        mXmax = 16;
        mYmax = 16;
        mAmCreated = false;
        mPlanePositionSet = false;
        mSurface = null;
        mContext = context;
        setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        if (useOverlay())
        {
            getHolder().setFormat(0x32315659);
        }
        getHolder().addCallback(new android.view.SurfaceHolder.Callback() {

            final VideoView this$0;

            public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
            {
                Log.v("VideoSurfaceView", (new StringBuilder()).append("surfaceChanged format=").append(i).append(", width=").append(j).append(", height=").append(k).toString());
                if (useOverlay() && mPlanePositionSet && (j != mXmax - mXmin || k != mYmax - mYmin))
                {
                    setPlanePosition(mXmin, mYmin, mXmax, mYmax);
                }
            }

            public void surfaceCreated(SurfaceHolder surfaceholder)
            {
                Log.v("VideoSurfaceView", "surfaceCreated");
                mSurface = surfaceholder.getSurface();
                mAmCreated = true;
                notifyNativeReadyForVideo();
            }

            public void surfaceDestroyed(SurfaceHolder surfaceholder)
            {
                Log.v("VideoSurfaceView", "surfaceDestroyed");
                mSurface.release();
                mAmCreated = false;
                notifyNativeReadyForVideo();
            }

            
            {
                this$0 = VideoView.this;
                super();
            }
        });
    }

    private native void nativeSetJavaViewReady(long l, boolean flag);

    public void VideoPlaybackRestarted()
    {
    }

    public long getFPInstance()
    {
        return mCPPInstance;
    }

    public Surface getSurface()
    {
        if (mAmCreated && useOverlay())
        {
            return mSurface;
        } else
        {
            return null;
        }
    }

    public void notifyNativeReadyForVideo()
    {
        if (mCPPInstance != 0L)
        {
            nativeSetJavaViewReady(mCPPInstance, mAmCreated);
        }
    }

    public void setFPInstance(long l)
    {
        Log.d("VideoSurfaceView", (new StringBuilder()).append("Changing FP Instance from ").append(mCPPInstance).append(" to ").append(l).toString());
        mCPPInstance = l;
        notifyNativeReadyForVideo();
    }

    public void setPlanePosition(int i, int j, int k, int l)
    {
        mXmin = i;
        mYmin = j;
        mXmax = k;
        mYmax = l;
        mPlanePositionSet = true;
        getHandler().post(new Runnable() {

            final VideoView this$0;

            public void run()
            {
                layout(mXmin, mYmin, mXmax, mYmax);
            }

            
            {
                this$0 = VideoView.this;
                super();
            }
        });
    }

    protected boolean useOverlay()
    {
        return android.os.Build.VERSION.SDK_INT >= 14;
    }








/*
    static Surface access$502(VideoView videoview, Surface surface)
    {
        videoview.mSurface = surface;
        return surface;
    }

*/


/*
    static boolean access$602(VideoView videoview, boolean flag)
    {
        videoview.mAmCreated = flag;
        return flag;
    }

*/
}
