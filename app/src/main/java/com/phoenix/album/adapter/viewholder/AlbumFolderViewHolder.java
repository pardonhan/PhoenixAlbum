package com.phoenix.album.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phoenix.album.R;
import com.phoenix.album.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HanFL on 2016/11/4.
 * <p>
 * AlbumFolderViewHolder
 */

public class AlbumFolderViewHolder extends RecyclerView.ViewHolder {
    private int size = (DisplayUtils.screenHeight - 5) / 2;
    @BindView(R.id.folder_first_image)
    public ImageView folderFirstImg;
    @BindView(R.id.folder_name_tv)
    public TextView tvFolderName;

    public AlbumFolderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.getLayoutParams().width = size;
        itemView.getLayoutParams().height = size;
    }
}
