// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air;

import android.app.Activity;
import android.os.Build;
import android.view.SurfaceView;
import android.view.Window;
import com.adobe.flashruntime.air.VideoViewAIR;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

// Referenced classes of package com.adobe.air:
//            AIRWindowSurfaceView, AIRStage3DSurfaceView, AndroidActivityWrapper

public class FlashEGL
{

    private static int EGL_BUFFER_DESTROYED;
    private static int EGL_BUFFER_PRESERVED;
    private static int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static int EGL_COVERAGE_BUFFERS_NV = 12512;
    private static int EGL_COVERAGE_SAMPLES_NV = 12513;
    private static int EGL_OPENGL_ES2_BIT;
    private static int EGL_SWAP_BEHAVIOR;
    private static String TAG = "FlashEGL";
    private static int cfgAttrs[];
    private static int fbPBufferSurfaceAttrs[] = {
        12375, 64, 12374, 64, 12344
    };
    private static int fbWindowSurfaceOffAttrs[];
    private static int fbWindowSurfaceOnAttrs[];
    private int kAlphaBits;
    private int kBlueBits;
    private int kColorBits;
    private int kConfigId;
    private int kCsaaSamp;
    private int kDepthBits;
    private int kGreenBits;
    private int kMsaaSamp;
    private int kNumElements;
    private int kRedBits;
    private int kStencilBits;
    private int kSurfaceTypes;
    private int kSwapPreserve;
    private int kSwapPreserveDefault;
    private int kSwapPreserveOff;
    private int kSwapPreserveOn;
    private EGL10 mEgl;
    private EGLConfig mEglConfig;
    private int mEglConfigCount;
    private EGLConfig mEglConfigList[];
    volatile EGLContext mEglContext;
    private EGLDisplay mEglDisplay;
    private EGLSurface mEglPbufferSurface;
    private EGLSurface mEglSurface;
    private int mEglVersion[];
    private EGLSurface mEglWindowSurface;
    private boolean mIsARGBSurface;
    private boolean mIsBufferPreserve;
    private boolean mIsGPUOOM;
    private int mPbufferConfigCount;
    private int mPixmapConfigCount;
    private int mWindowConfigCount;

    public FlashEGL()
    {
        kSurfaceTypes = 0;
        kConfigId = 1;
        kRedBits = 2;
        kGreenBits = 3;
        kBlueBits = 4;
        kAlphaBits = 5;
        kColorBits = 6;
        kDepthBits = 7;
        kStencilBits = 8;
        kMsaaSamp = 9;
        kCsaaSamp = 10;
        kSwapPreserve = 11;
        kNumElements = 12;
        kSwapPreserveDefault = 0;
        kSwapPreserveOn = 1;
        kSwapPreserveOff = 2;
        mEgl = null;
        mEglDisplay = EGL10.EGL_NO_DISPLAY;
        mEglSurface = EGL10.EGL_NO_SURFACE;
        mEglWindowSurface = EGL10.EGL_NO_SURFACE;
        mEglPbufferSurface = EGL10.EGL_NO_SURFACE;
        mEglConfig = null;
        mEglConfigList = null;
        mEglVersion = null;
        mEglConfigCount = 0;
        mWindowConfigCount = 0;
        mPixmapConfigCount = 0;
        mPbufferConfigCount = 0;
        mEglContext = EGL10.EGL_NO_CONTEXT;
        mIsARGBSurface = false;
        mIsGPUOOM = false;
        mIsBufferPreserve = false;
    }

    private int XX(int i, int j)
    {
        return j + i * kNumElements;
    }

    private int checkEglError(String s)
    {
        int i = mEgl.eglGetError();
        if (i != 12288 && !mIsGPUOOM && i == 12291)
        {
            if (mEglWindowSurface != EGL10.EGL_NO_SURFACE)
            {
                mEgl.eglDestroySurface(mEglDisplay, mEglWindowSurface);
                int j = mEgl.eglGetError();
                mEglWindowSurface = EGL10.EGL_NO_SURFACE;
                mEglSurface = EGL10.EGL_NO_SURFACE;
                if (j == 12288);
                mEglWindowSurface = EGL10.EGL_NO_SURFACE;
                mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                if (mEgl.eglGetError() == 12288);
            }
            if (mEglPbufferSurface != EGL10.EGL_NO_SURFACE && mEglContext != EGL10.EGL_NO_CONTEXT)
            {
                mEglSurface = mEglPbufferSurface;
                mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface, mEglContext);
                if (mEgl.eglGetError() == 12288);
            }
            mIsGPUOOM = true;
        }
        return i;
    }

    public boolean ChooseConfig(EGLDisplay egldisplay, int ai[], EGLConfig aeglconfig[], int i, int ai1[])
    {
        if (!IsEmulator())
        {
            return mEgl.eglChooseConfig(egldisplay, ai, aeglconfig, i, ai1);
        }
        int ai2[] = new int[1];
        mEgl.eglGetConfigs(egldisplay, null, 0, ai2);
        int j = ai2[0];
        EGLConfig aeglconfig1[] = new EGLConfig[j];
        mEgl.eglGetConfigs(egldisplay, aeglconfig1, j, ai2);
        int k = ai.length;
        if (ai.length % 2 != 0)
        {
            k = ai.length - 1;
        }
        int l = 0;
        for (int i1 = 0; i1 < j; i1++)
        {
            int j1;
            for (j1 = 0; j1 < k; j1 += 2)
            {
                if (ai[j1 + 1] == -1)
                {
                    continue;
                }
                int ai3[] = new int[1];
                mEgl.eglGetConfigAttrib(egldisplay, aeglconfig1[i1], ai[j1], ai3);
                if ((ai3[0] & ai[j1 + 1]) != ai[j1 + 1])
                {
                    break;
                }
            }

            if (j1 != k)
            {
                continue;
            }
            if (aeglconfig != null && l < i)
            {
                aeglconfig[l] = aeglconfig1[i1];
            }
            l++;
        }

        ai1[0] = l;
        return true;
    }

    public int CreateDummySurfaceAndContext()
    {
        if (mEglDisplay == EGL10.EGL_NO_DISPLAY)
        {
            return 12296;
        }
        if (mEglContext != EGL10.EGL_NO_CONTEXT)
        {
            if (mEglWindowSurface != EGL10.EGL_NO_SURFACE)
            {
                mEgl.eglMakeCurrent(mEglDisplay, mEglWindowSurface, mEglWindowSurface, mEglContext);
                return 12288;
            }
            if (mEglPbufferSurface != EGL10.EGL_NO_SURFACE)
            {
                mEgl.eglMakeCurrent(mEglDisplay, mEglPbufferSurface, mEglPbufferSurface, mEglContext);
                return 12288;
            }
            mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            mEgl.eglDestroyContext(mEglDisplay, mEglContext);
            mEglContext = EGL10.EGL_NO_CONTEXT;
        }
        int ai[] = new int[1];
        EGLConfig aeglconfig[] = new EGLConfig[1];
        cfgAttrs[1] = 1;
        ChooseConfig(mEglDisplay, cfgAttrs, aeglconfig, 1, ai);
        cfgAttrs[1] = -1;
        if (ai[0] == 0)
        {
            return 12294;
        }
        int ai1[] = new int[3];
        ai1[0] = EGL_CONTEXT_CLIENT_VERSION;
        ai1[1] = 2;
        ai1[2] = 12344;
        checkEglError("Before eglCreateContext");
        mEglContext = mEgl.eglCreateContext(mEglDisplay, aeglconfig[0], EGL10.EGL_NO_CONTEXT, ai1);
        checkEglError("After eglCreateContext");
        if (mEglContext == EGL10.EGL_NO_CONTEXT)
        {
            return 12294;
        }
        checkEglError("Before eglCreatePbufferSurface");
        mEglPbufferSurface = mEgl.eglCreatePbufferSurface(mEglDisplay, aeglconfig[0], fbPBufferSurfaceAttrs);
        checkEglError("After eglCreatePbufferSurface");
        if (mEglPbufferSurface == EGL10.EGL_NO_SURFACE)
        {
            return 12294;
        } else
        {
            checkEglError("Before eglMakeCurrent");
            mEgl.eglMakeCurrent(mEglDisplay, mEglPbufferSurface, mEglPbufferSurface, mEglContext);
            checkEglError("After eglMakeCurrent");
            return 12288;
        }
    }

    public int CreateGLContext(boolean flag)
    {
        if (mEglConfig == null)
        {
            return 12293;
        }
        if (mEglContext != EGL10.EGL_NO_CONTEXT && !flag)
        {
            return 12288;
        }
        int ai[] = new int[3];
        ai[0] = EGL_CONTEXT_CLIENT_VERSION;
        ai[1] = 2;
        ai[2] = 12344;
        if (flag)
        {
            EGLContext eglcontext = mEglContext;
            checkEglError("Before eglCreateContext");
            mEglContext = mEgl.eglCreateContext(mEglDisplay, mEglConfig, eglcontext, ai);
            checkEglError("After eglCreateContext");
            mEgl.eglDestroyContext(mEglDisplay, eglcontext);
            checkEglError("After eglDestroyContext");
        } else
        {
            checkEglError("Before eglCreateContext");
            mEglContext = mEgl.eglCreateContext(mEglDisplay, mEglConfig, EGL10.EGL_NO_CONTEXT, ai);
            checkEglError("After eglCreateContext");
        }
        if (mEglContext == EGL10.EGL_NO_CONTEXT)
        {
            return 12294;
        }
        if (EGL10.EGL_NO_SURFACE == mEglPbufferSurface)
        {
            checkEglError("Before eglCreatePbufferSurface");
            mEglPbufferSurface = mEgl.eglCreatePbufferSurface(mEglDisplay, mEglConfig, fbPBufferSurfaceAttrs);
            checkEglError("After eglCreatePbufferSurface");
        }
        return 12288;
    }

    public int CreateWindowSurface(SurfaceView surfaceview, int i)
    {
_L2:
        if (mEglWindowSurface == EGL10.EGL_NO_SURFACE)
        {
            checkEglError("Before eglCreateWindowSurface");
            mEglWindowSurface = mEgl.eglCreateWindowSurface(mEglDisplay, mEglConfig, surfaceview.getHolder(), null);
            int j = checkEglError("After eglCreateWindowSurface");
            if (j != 12288)
            {
                return j;
            }
        }
        if (mEglWindowSurface == EGL10.EGL_NO_SURFACE)
        {
            return 12301;
        }
        mEglSurface = mEglWindowSurface;
        if (flag)
        {
            ((AIRWindowSurfaceView)surfaceview).setFlashEGL(this);
            Activity activity = ((AIRWindowSurfaceView)surfaceview).getActivityWrapper().getActivity();
            if (activity != null)
            {
                activity.getWindow().setSoftInputMode(34);
            }
        }
        int ai[] = {
            0
        };
        mIsBufferPreserve = false;
        if (flag1 && mEgl.eglQuerySurface(mEglDisplay, mEglSurface, EGL_SWAP_BEHAVIOR, ai))
        {
            boolean flag2;
            if (ai[0] == EGL_BUFFER_PRESERVED)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            mIsBufferPreserve = flag2;
        }
        return MakeGLCurrent();
        if (mIsGPUOOM)
        {
            return 12291;
        }
        boolean flag = surfaceview instanceof AIRWindowSurfaceView;
        if (!(surfaceview instanceof VideoViewAIR) && !(surfaceview instanceof AIRStage3DSurfaceView) && !flag)
        {
            return 12301;
        }
        if (mEglWindowSurface != EGL10.EGL_NO_SURFACE)
        {
            mEglSurface = mEglWindowSurface;
            return MakeGLCurrent();
        }
        boolean flag1;
        if (i == kSwapPreserveOn)
        {
label0:
            {
                checkEglError("Before eglCreateWindowSurface");
                mEglWindowSurface = mEgl.eglCreateWindowSurface(mEglDisplay, mEglConfig, surfaceview.getHolder(), fbWindowSurfaceOnAttrs);
                if (mEglWindowSurface == EGL10.EGL_NO_SURFACE)
                {
                    checkEglError("After eglCreateWindowSurface");
                    flag1 = false;
                    break label0;
                }
            }
        } else
        if (i == kSwapPreserveOff)
        {
            checkEglError("Before eglCreateWindowSurface");
            mEglWindowSurface = mEgl.eglCreateWindowSurface(mEglDisplay, mEglConfig, surfaceview.getHolder(), fbWindowSurfaceOffAttrs);
            if (mEglWindowSurface == EGL10.EGL_NO_SURFACE)
            {
                checkEglError("After eglCreateWindowSurface");
                flag1 = false;
                continue; /* Loop/switch isn't completed */
            }
        }
        flag1 = true;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean DestroyGLContext()
    {
        if (mEglContext == EGL10.EGL_NO_CONTEXT || mEglDisplay == EGL10.EGL_NO_DISPLAY)
        {
            return false;
        }
        checkEglError("DestroyGLContext: Before eglMakeCurrent for noSurface");
        mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        checkEglError("DestroyGLContext: After eglMakeCurrent");
        if (mEglPbufferSurface != EGL10.EGL_NO_SURFACE)
        {
            mEgl.eglDestroySurface(mEglDisplay, mEglPbufferSurface);
            mEglPbufferSurface = EGL10.EGL_NO_SURFACE;
        }
        checkEglError("Before eglDestroyContext");
        boolean flag = mEgl.eglDestroyContext(mEglDisplay, mEglContext);
        checkEglError("After eglDestroyContext");
        mEglContext = EGL10.EGL_NO_CONTEXT;
        return flag;
    }

    public boolean DestroyWindowSurface()
    {
        if (mEglWindowSurface != EGL10.EGL_NO_SURFACE)
        {
            checkEglError("Before eglMakeCurrent");
            mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            if (12288 != checkEglError("After eglMakeCurrent"))
            {
                return false;
            }
            checkEglError("Before eglDestroySurface (window)");
            mEgl.eglDestroySurface(mEglDisplay, mEglWindowSurface);
            if (12288 != checkEglError("After eglDestroySurface (window)"))
            {
                return false;
            }
            if (mEglSurface == mEglWindowSurface)
            {
                mEglSurface = EGL10.EGL_NO_SURFACE;
            }
            mEglWindowSurface = EGL10.EGL_NO_SURFACE;
            if (mEglPbufferSurface != EGL10.EGL_NO_SURFACE && mEglContext != EGL10.EGL_NO_CONTEXT)
            {
                mEglSurface = mEglPbufferSurface;
                mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface, mEglContext);
                if (12288 != checkEglError("After eglMakeCurrent"))
                {
                    return false;
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void FlashEGL()
    {
        mEgl = null;
        mEglDisplay = EGL10.EGL_NO_DISPLAY;
        mEglConfig = null;
        mEglContext = EGL10.EGL_NO_CONTEXT;
        mEglSurface = EGL10.EGL_NO_SURFACE;
        mEglWindowSurface = EGL10.EGL_NO_SURFACE;
        mEglPbufferSurface = EGL10.EGL_NO_SURFACE;
        mIsARGBSurface = false;
    }

    public int[] GetConfigs(boolean flag, boolean flag1)
    {
        int ai[] = new int[mEglConfigCount * kNumElements];
        int ai1[] = new int[1];
        int ai2[] = new int[1];
        mEglConfigList = new EGLConfig[mEglConfigCount];
        checkEglError("Before eglChooseConfig");
        ChooseConfig(mEglDisplay, cfgAttrs, mEglConfigList, mEglConfigCount, ai1);
        checkEglError("After eglChooseConfig");
        int i = ai1[0];
        mEglConfigCount = i;
        int j = 0;
        while (j < i) 
        {
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12339, ai2);
            ai[XX(j, kSurfaceTypes)] = ai2[0];
            ai[XX(j, kConfigId)] = j;
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12324, ai2);
            ai[XX(j, kRedBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12323, ai2);
            ai[XX(j, kGreenBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12322, ai2);
            ai[XX(j, kBlueBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12321, ai2);
            ai[XX(j, kAlphaBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12320, ai2);
            ai[XX(j, kColorBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12325, ai2);
            ai[XX(j, kDepthBits)] = ai2[0];
            mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12326, ai2);
            ai[XX(j, kStencilBits)] = ai2[0];
            ai[XX(j, kCsaaSamp)] = 0;
            ai[XX(j, kMsaaSamp)] = 0;
            if (flag)
            {
                mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], EGL_COVERAGE_SAMPLES_NV, ai2);
                if (ai2[0] != 1)
                {
                    ai[XX(j, kCsaaSamp)] = ai2[0];
                }
            } else
            {
                mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfigList[j], 12337, ai2);
                if (ai2[0] != 1)
                {
                    ai[XX(j, kMsaaSamp)] = ai2[0];
                }
            }
            if (flag1)
            {
                int k = XX(j, kSwapPreserve);
                int l;
                if ((mEglVersion[0] > 1 || mEglVersion[1] > 3) && (ai[XX(j, kSurfaceTypes)] & EGL_BUFFER_PRESERVED) != 0)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                ai[k] = l;
            } else
            {
                ai[XX(j, kSwapPreserve)] = 0;
            }
            j++;
        }
        return ai;
    }

    public int[] GetNumConfigs()
    {
        int ai[] = new int[4];
        int ai1[] = new int[1];
        ChooseConfig(mEglDisplay, cfgAttrs, null, 0, ai1);
        int i = ai1[0];
        ai[0] = i;
        mEglConfigCount = i;
        cfgAttrs[1] = 4;
        ChooseConfig(mEglDisplay, cfgAttrs, null, 0, ai1);
        int j = ai1[0];
        ai[1] = j;
        mWindowConfigCount = j;
        cfgAttrs[1] = 2;
        ChooseConfig(mEglDisplay, cfgAttrs, null, 0, ai1);
        int k = ai1[0];
        ai[2] = k;
        mPixmapConfigCount = k;
        cfgAttrs[1] = 1;
        ChooseConfig(mEglDisplay, cfgAttrs, null, 0, ai1);
        int l = ai1[0];
        ai[3] = l;
        mPbufferConfigCount = l;
        cfgAttrs[1] = -1;
        return ai;
    }

    public int GetSurfaceHeight()
    {
        int ai[] = new int[1];
        mEgl.eglQuerySurface(mEglDisplay, mEglSurface, 12374, ai);
        return ai[0];
    }

    public int GetSurfaceWidth()
    {
        int ai[] = new int[1];
        mEgl.eglQuerySurface(mEglDisplay, mEglSurface, 12375, ai);
        return ai[0];
    }

    public boolean HasGLContext()
    {
        return mEglContext != EGL10.EGL_NO_CONTEXT;
    }

    public int InitEGL()
    {
        int i;
        if (mEglContext != EGL10.EGL_NO_CONTEXT)
        {
            i = 12288;
        } else
        {
            mEgl = (EGL10)EGLContext.getEGL();
            checkEglError("Before eglGetDisplay");
            mEglDisplay = mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            i = checkEglError("After eglGetDisplay");
            if (12288 == i)
            {
                mEglVersion = new int[2];
                checkEglError("Before eglInitialize");
                mEgl.eglInitialize(mEglDisplay, mEglVersion);
                i = checkEglError("After eglInitialize");
                if (12288 == i)
                {
                    return 12288;
                }
            }
        }
        return i;
    }

    public boolean IsARGBSurface()
    {
        return mIsARGBSurface;
    }

    public boolean IsBufferPreserve()
    {
        return mIsBufferPreserve;
    }

    public boolean IsEmulator()
    {
        return Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic");
    }

    public int MakeGLCurrent()
    {
        if (mEglContext == EGL10.EGL_NO_CONTEXT)
        {
            return 12294;
        }
        if (mEglSurface == EGL10.EGL_NO_SURFACE)
        {
            return 12301;
        }
        if (mEglDisplay == EGL10.EGL_NO_DISPLAY)
        {
            return 12296;
        } else
        {
            checkEglError("Before eglMakeCurrent");
            mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface, mEglContext);
            return checkEglError("After eglMakeCurrent");
        }
    }

    public void ReleaseGPUResources()
    {
        if (mEglContext == EGL10.EGL_NO_CONTEXT)
        {
            return;
        }
        checkEglError("Before eglMakeCurrent");
        mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        checkEglError("After eglMakeCurrent");
        synchronized (mEgl)
        {
            checkEglError("Before eglDestroySurface");
            if (mEglWindowSurface != EGL10.EGL_NO_SURFACE)
            {
                mEgl.eglDestroySurface(mEglDisplay, mEglWindowSurface);
                mEglWindowSurface = EGL10.EGL_NO_SURFACE;
            }
            checkEglError("After eglDestroySurface (window)");
        }
        if (mEglPbufferSurface != EGL10.EGL_NO_SURFACE)
        {
            checkEglError("Before eglDestroySurface (pbuffer)");
            mEgl.eglDestroySurface(mEglDisplay, mEglPbufferSurface);
            checkEglError("After eglDestroySurface (pbuffer)");
            mEglPbufferSurface = EGL10.EGL_NO_SURFACE;
        }
        checkEglError("Before eglDestroyContext");
        mEgl.eglDestroyContext(mEglDisplay, mEglContext);
        checkEglError("After eglDestroyContext");
        mEglContext = EGL10.EGL_NO_CONTEXT;
        mEglSurface = EGL10.EGL_NO_SURFACE;
        return;
        exception;
        egl10;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void SetConfig(int i)
    {
        mEglConfig = mEglConfigList[i];
        int ai[] = new int[1];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12324, ai);
        int _tmp = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12323, ai);
        int _tmp1 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12322, ai);
        int _tmp2 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12321, ai);
        int _tmp3 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12325, ai);
        int _tmp4 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12326, ai);
        int _tmp5 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12337, ai);
        int _tmp6 = ai[0];
        mEgl.eglGetConfigAttrib(mEglDisplay, mEglConfig, 12338, ai);
        int _tmp7 = ai[0];
    }

    public void SwapEGLBuffers()
    {
        if (12288 != MakeGLCurrent())
        {
            return;
        } else
        {
            checkEglError("Before eglSwapBuffers");
            mEgl.eglSwapBuffers(mEglDisplay, mEglSurface);
            checkEglError("After eglSwapBuffers");
            return;
        }
    }

    public void TerminateEGL()
    {
        if (mEgl != null && mEglDisplay != EGL10.EGL_NO_DISPLAY)
        {
            mEgl.eglTerminate(mEglDisplay);
        }
        mEglDisplay = EGL10.EGL_NO_DISPLAY;
    }

    static 
    {
        EGL_OPENGL_ES2_BIT = 4;
        EGL_SWAP_BEHAVIOR = 12435;
        EGL_BUFFER_PRESERVED = 12436;
        EGL_BUFFER_DESTROYED = 12437;
        int ai[] = new int[9];
        ai[0] = 12339;
        ai[1] = -1;
        ai[2] = 12325;
        ai[3] = -1;
        ai[4] = 12326;
        ai[5] = -1;
        ai[6] = 12352;
        ai[7] = EGL_OPENGL_ES2_BIT;
        ai[8] = 12344;
        cfgAttrs = ai;
        int ai1[] = new int[3];
        ai1[0] = EGL_SWAP_BEHAVIOR;
        ai1[1] = EGL_BUFFER_PRESERVED;
        ai1[2] = 12344;
        fbWindowSurfaceOnAttrs = ai1;
        int ai2[] = new int[3];
        ai2[0] = EGL_SWAP_BEHAVIOR;
        ai2[1] = EGL_BUFFER_DESTROYED;
        ai2[2] = 12344;
        fbWindowSurfaceOffAttrs = ai2;
    }
}
