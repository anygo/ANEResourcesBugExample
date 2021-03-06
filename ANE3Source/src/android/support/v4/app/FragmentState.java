// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

// Referenced classes of package android.support.v4.app:
//            Fragment, FragmentActivity, FragmentManagerImpl

final class FragmentState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FragmentState createFromParcel(Parcel parcel)
        {
            return new FragmentState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FragmentState[] newArray(int i)
        {
            return new FragmentState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    public FragmentState(Parcel parcel)
    {
        mClassName = parcel.readString();
        mIndex = parcel.readInt();
        boolean flag;
        boolean flag1;
        boolean flag2;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mFromLayout = flag;
        mFragmentId = parcel.readInt();
        mContainerId = parcel.readInt();
        mTag = parcel.readString();
        if (parcel.readInt() != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mRetainInstance = flag1;
        if (parcel.readInt() != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        mDetached = flag2;
        mArguments = parcel.readBundle();
        mSavedFragmentState = parcel.readBundle();
    }

    public FragmentState(Fragment fragment)
    {
        mClassName = fragment.getClass().getName();
        mIndex = fragment.mIndex;
        mFromLayout = fragment.mFromLayout;
        mFragmentId = fragment.mFragmentId;
        mContainerId = fragment.mContainerId;
        mTag = fragment.mTag;
        mRetainInstance = fragment.mRetainInstance;
        mDetached = fragment.mDetached;
        mArguments = fragment.mArguments;
    }

    public int describeContents()
    {
        return 0;
    }

    public Fragment instantiate(FragmentActivity fragmentactivity, Fragment fragment)
    {
        if (mInstance != null)
        {
            return mInstance;
        }
        if (mArguments != null)
        {
            mArguments.setClassLoader(fragmentactivity.getClassLoader());
        }
        mInstance = Fragment.instantiate(fragmentactivity, mClassName, mArguments);
        if (mSavedFragmentState != null)
        {
            mSavedFragmentState.setClassLoader(fragmentactivity.getClassLoader());
            mInstance.mSavedFragmentState = mSavedFragmentState;
        }
        mInstance.setIndex(mIndex, fragment);
        mInstance.mFromLayout = mFromLayout;
        mInstance.mRestored = true;
        mInstance.mFragmentId = mFragmentId;
        mInstance.mContainerId = mContainerId;
        mInstance.mTag = mTag;
        mInstance.mRetainInstance = mRetainInstance;
        mInstance.mDetached = mDetached;
        mInstance.mFragmentManager = fragmentactivity.mFragments;
        if (FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Instantiated fragment ").append(mInstance).toString());
        }
        return mInstance;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mClassName);
        parcel.writeInt(mIndex);
        int j;
        int k;
        int l;
        if (mFromLayout)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeInt(mFragmentId);
        parcel.writeInt(mContainerId);
        parcel.writeString(mTag);
        if (mRetainInstance)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        parcel.writeInt(k);
        if (mDetached)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        parcel.writeInt(l);
        parcel.writeBundle(mArguments);
        parcel.writeBundle(mSavedFragmentState);
    }

}
