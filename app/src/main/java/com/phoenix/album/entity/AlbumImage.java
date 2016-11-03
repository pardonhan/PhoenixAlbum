package com.phoenix.album.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by HanFL on 2016/11/3.
 * <p>
 * AlbumImage
 */
public class AlbumImage implements Parcelable, Comparable<AlbumImage> {
    /**
     * 图片名称
     */
    private String imgName;
    /**
     * 图片路径
     */
    private String imgPath;
    /**
     * 图片创建时间
     */
    private long imgCreateTime;

    public AlbumImage() {
        super();
    }

    public AlbumImage(String imgName, String imgPath, long imgCreateTime) {
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.imgCreateTime = imgCreateTime;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public long getImgCreateTime() {
        return imgCreateTime;
    }

    public void setImgCreateTime(long imgCreateTime) {
        this.imgCreateTime = imgCreateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imgName);
        parcel.writeString(imgPath);
        parcel.writeLong(imgCreateTime);
    }


    public static final Creator<AlbumImage> CREATOR = new Creator<AlbumImage>() {
        @Override
        public AlbumImage createFromParcel(Parcel in) {
            return new AlbumImage(in);
        }

        @Override
        public AlbumImage[] newArray(int size) {
            return new AlbumImage[size];
        }
    };

    public AlbumImage(Parcel in) {
        AlbumImage albumImage = new AlbumImage();
        albumImage.imgName = in.readString();
        albumImage.imgPath = in.readString();
        albumImage.imgCreateTime = in.readLong();
    }

    @Override
    public int compareTo(@NonNull AlbumImage albumImage) {
        long time = albumImage.getImgCreateTime() - getImgCreateTime();
        if (time > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if (time < -Integer.MAX_VALUE)
            return -Integer.MAX_VALUE;
        return (int) time;
    }
}
