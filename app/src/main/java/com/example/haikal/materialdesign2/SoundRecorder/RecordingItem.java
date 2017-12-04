package com.example.haikal.materialdesign2.SoundRecorder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haikal on 04/12/2017.
 */

public class RecordingItem implements Parcelable {
    private String mName;
    private String mFilePath;
    private int mId;
    private int mLength;
    private long mTime;

    public RecordingItem() {
    }

    protected RecordingItem(Parcel in) {
        mName = in.readString();
        mFilePath = in.readString();
        mId = in.readInt();
        mLength = in.readInt();
        mTime = in.readLong();
    }


    public static final Parcelable.Creator<RecordingItem> CREATOR = new Parcelable.Creator<RecordingItem>() {
        @Override
        public RecordingItem createFromParcel(Parcel in) {
            return new RecordingItem(in);
        }

        @Override
        public RecordingItem[] newArray(int size) {
            return new RecordingItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeInt(mLength);
        parcel.writeLong(mTime);
        parcel.writeString(mFilePath);
        parcel.writeString(mName);

    }

    public String getmFilePath() {
        return mFilePath;
    }

    public void setmFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmLength() {
        return mLength;
    }

    public void setmLength(int mLength) {
        this.mLength = mLength;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
