// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.fre;


// Referenced classes of package com.adobe.fre:
//            FREWrongThreadException, FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, 
//            FRENoSuchNameException, FREReadOnlyException

public class FREObject
{
    protected static class CFREObjectWrapper
    {

        private long m_objectPointer;


        private CFREObjectWrapper(long l)
        {
            m_objectPointer = l;
        }
    }


    private long m_objectPointer;

    protected FREObject(double d)
        throws FREWrongThreadException
    {
        FREObjectFromDouble(d);
    }

    protected FREObject(int i)
        throws FREWrongThreadException
    {
        FREObjectFromInt(i);
    }

    protected FREObject(CFREObjectWrapper cfreobjectwrapper)
    {
        m_objectPointer = cfreobjectwrapper.m_objectPointer;
    }

    protected FREObject(String s)
        throws FREWrongThreadException
    {
        FREObjectFromString(s);
    }

    public FREObject(String s, FREObject afreobject[])
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException, IllegalStateException
    {
        FREObjectFromClass(s, afreobject);
    }

    protected FREObject(boolean flag)
        throws FREWrongThreadException
    {
        FREObjectFromBoolean(flag);
    }

    private native void FREObjectFromBoolean(boolean flag)
        throws FREWrongThreadException;

    private native void FREObjectFromClass(String s, FREObject afreobject[])
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException;

    private native void FREObjectFromDouble(double d)
        throws FREWrongThreadException;

    private native void FREObjectFromInt(int i)
        throws FREWrongThreadException;

    private native void FREObjectFromString(String s)
        throws FREWrongThreadException;

    public static FREObject newObject(double d)
        throws FREWrongThreadException
    {
        return new FREObject(d);
    }

    public static FREObject newObject(int i)
        throws FREWrongThreadException
    {
        return new FREObject(i);
    }

    public static FREObject newObject(String s)
        throws FREWrongThreadException
    {
        return new FREObject(s);
    }

    public static native FREObject newObject(String s, FREObject afreobject[])
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException, IllegalStateException;

    public static FREObject newObject(boolean flag)
        throws FREWrongThreadException
    {
        return new FREObject(flag);
    }

    public native FREObject callMethod(String s, FREObject afreobject[])
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException, IllegalStateException;

    public native boolean getAsBool()
        throws FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException, IllegalStateException;

    public native double getAsDouble()
        throws FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException, IllegalStateException;

    public native int getAsInt()
        throws FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException, IllegalStateException;

    public native String getAsString()
        throws FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException, IllegalStateException;

    public native FREObject getProperty(String s)
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREWrongThreadException, IllegalStateException;

    public native void setProperty(String s, FREObject freobject)
        throws FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException, FREReadOnlyException, FREWrongThreadException, IllegalStateException;
}
