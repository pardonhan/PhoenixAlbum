package com.phoenix.album;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.phoenix.album.adapter.AlbumContentAdapter;
import com.phoenix.album.adapter.decoration.AlbumFolderItemDecoration;
import com.phoenix.album.entity.AlbumFolder;
import com.phoenix.album.task.AlbumScanner;
import com.phoenix.album.utils.DisplayUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @BindView(R.id.folder_recycler_view)
    RecyclerView folderRecyclerView;

    AlbumContentAdapter albumContentAdapter;
    private List<AlbumFolder> mAlbumFolders = new ArrayList<>();
    private MainHandler mainHandler = new MainHandler(new WeakReference<>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayUtils.initScreen(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        folderRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        albumContentAdapter = new AlbumContentAdapter(this, mAlbumFolders);
        folderRecyclerView.addItemDecoration(new AlbumFolderItemDecoration());
        folderRecyclerView.setAdapter(albumContentAdapter);
        scanImages();
    }

    /**
     * 扫描有图片的文件夹
     */
    private void scanImages() {
        executorService.execute(scanner);
    }

    /**
     * 图片扫描线程
     */
    private Runnable scanner = new Runnable() {
        @Override
        public void run() {
            //TODO
            mAlbumFolders.addAll(AlbumScanner.getInstance().getPhotoAlbum(MainActivity.this));
            Log.d(TAG, String.valueOf(mAlbumFolders.size()));
            mainHandler.sendEmptyMessage(1);
        }
    };

    private static class MainHandler extends Handler {
        private WeakReference<MainActivity> weakReference;

        private MainHandler(WeakReference<MainActivity> wk) {
            this.weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = weakReference.get();
            if (activity == null) {
                return;
            }
            if (msg.what == 1) {
                activity.albumContentAdapter.notifyDataSetChanged();
            }
        }
    }
}
