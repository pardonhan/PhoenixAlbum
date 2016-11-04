package com.phoenix.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phoenix.album.R;
import com.phoenix.album.adapter.viewholder.AlbumFolderViewHolder;
import com.phoenix.album.entity.AlbumFolder;
import com.phoenix.album.entity.AlbumImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by HanFL on 2016/11/3.
 * <p>
 * 相册文件夹内容显示适配器。
 */
public class AlbumContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<AlbumFolder> list;

    public AlbumContentAdapter(Context context, List<AlbumFolder> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_folder_item, parent, false);
        return new AlbumFolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumFolderViewHolder itemHolder = (AlbumFolderViewHolder) holder;
        AlbumFolder albumFolder = list.get(position);
        List<AlbumImage> albumImageList = albumFolder.getImageList();
        Picasso.with(mContext).load(new File(albumImageList.get(0).getImgPath())).into(itemHolder.folderFirstImg);
        itemHolder.tvFolderName.setText(albumFolder.getFolderName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
