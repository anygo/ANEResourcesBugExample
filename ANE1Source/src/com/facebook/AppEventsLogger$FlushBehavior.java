// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


// Referenced classes of package com.facebook:
//            AppEventsLogger

public static final class  extends Enum
{

    public static final ENUM.VALUES AUTO;
    private static final ENUM.VALUES ENUM$VALUES[];
    public static final ENUM.VALUES EXPLICIT_ONLY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/AppEventsLogger$FlushBehavior, s);
    }

    public static [] values()
    {
         a[] = ENUM$VALUES;
        int i = a.length;
         a1[] = new ENUM.VALUES[i];
        System.arraycopy(a, 0, a1, 0, i);
        return a1;
    }

    static 
    {
        AUTO = new <init>("AUTO", 0);
        EXPLICIT_ONLY = new <init>("EXPLICIT_ONLY", 1);
        ENUM.VALUES avalues[] = new <init>[2];
        avalues[0] = AUTO;
        avalues[1] = EXPLICIT_ONLY;
        ENUM$VALUES = avalues;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
