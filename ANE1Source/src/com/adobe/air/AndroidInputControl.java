// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.air;


// Referenced classes of package com.adobe.air:
//            ControlType

class AndroidInputControl
{

    private int mCode;
    private long mInternalReference;
    private float mMaxValue;
    private float mMinValue;
    private ControlType mType;
    private float mValue;

    public AndroidInputControl(ControlType controltype, int i, float f, float f1)
    {
        mCode = 0;
        mValue = 0.0F;
        mMinValue = 0.0F;
        mMaxValue = 0.0F;
        mType = controltype;
        mCode = i;
        mMinValue = f;
        mMaxValue = f1;
    }

    private native void OnValueChange(long l, float f);

    public int getCode()
    {
        return mCode;
    }

    public String getId()
    {
        return (new StringBuilder()).append(mType.name()).append("_").append(mCode).toString();
    }

    public float getMaxValue()
    {
        return mMaxValue;
    }

    public float getMinValue()
    {
        return mMinValue;
    }

    public int getType()
    {
        return mType.ordinal();
    }

    public float getValue()
    {
        return mValue;
    }

    public void setData(float f)
    {
        if (mValue != f)
        {
            mValue = f;
            OnValueChange(mInternalReference, mValue);
        }
    }

    public void setInternalReference(long l)
    {
        mInternalReference = l;
    }
}
