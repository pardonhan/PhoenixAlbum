package com.phoenix.album.task;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.phoenix.album.R;
import com.phoenix.album.entity.AlbumFolder;
import com.phoenix.album.entity.AlbumImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HanFL on 2016/11/4.
 * <p>
 * AlbumScanner
 */

public class AlbumScanner {

    private static AlbumScanner instance;

    private AlbumScanner() {
    }

    public static AlbumScanner getInstance() {
        if (instance == null)
            synchronized (AlbumScanner.class) {
                if (instance == null)
                    instance = new AlbumScanner();

            }
        return instance;
    }

    private static final String[] STORE_IMAGES = {
            MediaStore.Images.Media._ID,// 图片Id
            MediaStore.Images.Media.DATA,//图片路径
            MediaStore.Images.Media.DATE_ADDED,//添加时间
            MediaStore.Images.Media.DISPLAY_NAME,//文件名称
            MediaStore.Images.Media.BUCKET_ID,//文件夹Id
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME//文件夹路径
    };

    public List<AlbumFolder> getPhotoAlbum(Context context) {

        Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);
        Map<String, AlbumFolder> albumFolderMap = new HashMap<>();

        AlbumFolder allImageAlbumFolder = new AlbumFolder();
        allImageAlbumFolder.setChecked(true);
        allImageAlbumFolder.setFolderName(context.getString(R.string.album_all_image));

        while (cursor.moveToNext()) {
            AlbumImage albumImage = new AlbumImage();
            albumImage.setImgId(cursor.getInt(0));
            albumImage.setImgPath(cursor.getString(1));
            albumImage.setImgName(cursor.getString(2));
            albumImage.setImgCreateTime(cursor.getLong(3));
            allImageAlbumFolder.addAlbumImage(albumImage);
            int bucketId = cursor.getInt(4);
            String bucketName = cursor.getString(5);
            AlbumFolder albumFolder = albumFolderMap.get(bucketName);
            if (albumFolder == null) {
                albumFolder = new AlbumFolder();
                albumFolder.setFolderId(bucketId);
                albumFolder.setFolderName(bucketName);
                albumFolder.addAlbumImage(albumImage);

                albumFolderMap.put(bucketName, albumFolder);
            } else {
                albumFolder.addAlbumImage(albumImage);
            }
        }
        cursor.close();
        List<AlbumFolder> albumFolders = new ArrayList<>();

        Collections.sort(allImageAlbumFolder.getImageList());
        albumFolders.add(allImageAlbumFolder);
        for (Map.Entry<String, AlbumFolder> folderEntry : albumFolderMap.entrySet()) {
            AlbumFolder albumFolder = folderEntry.getValue();
            Collections.sort(albumFolder.getImageList());
            albumFolders.add(albumFolder);
        }

        return albumFolders;
    }

}
