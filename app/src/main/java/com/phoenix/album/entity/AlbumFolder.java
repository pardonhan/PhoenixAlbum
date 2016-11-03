package com.phoenix.album.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by HanFL on 2016/11/3.
 * <p>
 * AlbumFolder
 */
public class AlbumFolder implements Parcelable {

    private String folderName;
    private ArrayList<AlbumImage> imageList = new ArrayList<>();

    public AlbumFolder() {
        super();
    }

    public AlbumFolder(String folderName, ArrayList<AlbumImage> imageList) {
        this.folderName = folderName;
        this.imageList = imageList;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ArrayList<AlbumImage> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<AlbumImage> imageList) {
        this.imageList = imageList;
    }

    public AlbumFolder(Parcel in) {
        folderName = in.readString();
        imageList = in.createTypedArrayList(AlbumImage.CREATOR);
    }

    public static final Creator<AlbumFolder> CREATOR = new Creator<AlbumFolder>() {
        @Override
        public AlbumFolder createFromParcel(Parcel in) {
            return new AlbumFolder(in);
        }

        @Override
        public AlbumFolder[] newArray(int size) {
            return new AlbumFolder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(folderName);
        parcel.writeTypedList(imageList);
    }
}
