// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import com.facebook.FacebookException;

// Referenced classes of package com.facebook.widget:
//            PickerFragment, GraphObjectAdapter

class this._cls1
    implements r
{

    final this._cls1 this$1;

    public void onError(GraphObjectAdapter graphobjectadapter, FacebookException facebookexception)
    {
        if (PickerFragment.access$1(cess._mth0(this._cls1.this)) != null)
        {
            PickerFragment.access$1(cess._mth0(this._cls1.this)).Error(cess._mth0(this._cls1.this), facebookexception);
        }
    }

    r()
    {
        this$1 = this._cls1.this;
        super();
    }
}
