// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.chartboost.sdk;

import com.chartboost.sdk.impl.k;
import org.json.JSONObject;

// Referenced classes of package com.chartboost.sdk:
//            a, Chartboost, b

class 
    implements com.chartboost.sdk.impl.t
{

    final a a;
    private final boolean b;
    private final boolean c;

    public void a(k k, String s)
    {
        com.chartboost.sdk.a.a(a, com.chartboost.sdk.impl.c, null);
        b b1 = com.chartboost.sdk.a.b(a).a();
        if (b1 != null && b1.a())
        {
            b1.a(true);
        }
    }

    public void a(JSONObject jsonobject, k k)
    {
        com.chartboost.sdk.a.a(a, jsonobject, com.chartboost.sdk.impl.c, b, null, c);
    }

    .k(a a1, boolean flag, boolean flag1)
    {
        a = a1;
        b = flag;
        c = flag1;
        super();
    }
}
