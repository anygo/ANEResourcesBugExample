// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.adobe.air:
//            TouchEventData, KeyEventData

public class customHandler extends Handler
{

    public static final int KEY_MSG_ID = 1121;
    public static final int TIME_OUT_MSG_ID = 1119;
    public static final int TOUCH_MSG_ID = 1120;

    public customHandler()
    {
    }

    public native void callTimeoutFunction(int i, int j);

    public void handleMessage(Message message)
    {
        if (message.what == 1119)
        {
            callTimeoutFunction(message.arg1, message.arg2);
        } else
        {
            if (message.what == 1120)
            {
                TouchEventData toucheventdata = (TouchEventData)message.obj;
                nativeOnTouchCallback(toucheventdata.mTouchEventType, toucheventdata.mXCoord, toucheventdata.mYCoord, toucheventdata.mPressure, toucheventdata.mPointerID, toucheventdata.mContactX, toucheventdata.mContactY, toucheventdata.mIsPrimaryPoint, toucheventdata.mHistory, toucheventdata.mMetaState);
                return;
            }
            if (message.what == 1121)
            {
                KeyEventData keyeventdata = (KeyEventData)message.obj;
                nativeOnKeyCallback(keyeventdata.mKeyAction, keyeventdata.mKeyCode, keyeventdata.mUnicode, keyeventdata.mAlt, keyeventdata.mShift, keyeventdata.mSym);
                return;
            }
        }
    }

    public native boolean nativeOnKeyCallback(int i, int j, int k, boolean flag, boolean flag1, boolean flag2);

    public native boolean nativeOnTouchCallback(int i, float f, float f1, float f2, int j, float f3, float f4, 
            boolean flag, float af[], int k);
}
