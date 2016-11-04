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
    private int folderId;
    private String folderName;
    private ArrayList<AlbumImage> imageList = new ArrayList<>();
    /**
     * 文件夹是否被选中。
     */
    private boolean isChecked;

    public AlbumFolder() {
        super();
    }

    public AlbumFolder(int folderId, String folderName, ArrayList<AlbumImage> imageList, boolean isChecked) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.imageList = imageList;
        this.isChecked = isChecked;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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

    public void addAlbumImage(AlbumImage albumImage) {
        this.imageList.add(albumImage);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public AlbumFolder(Parcel in) {
        AlbumFolder af = new AlbumFolder();
        af.folderId = in.readInt();
        af.folderName = in.readString();
        af.imageList = in.createTypedArrayList(AlbumImage.CREATOR);
        af.isChecked = in.readInt() == 1;
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
        parcel.writeInt(folderId);
        parcel.writeString(folderName);
        parcel.writeTypedList(imageList);
        parcel.writeInt(isChecked ? 1 : 0);
    }
}
