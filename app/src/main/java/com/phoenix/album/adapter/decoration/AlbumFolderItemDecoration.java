package com.phoenix.album.adapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HanFL on 2016/11/4.
 * <p>
 * AlbumFolderItemDecoration
 */
public class AlbumFolderItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 0)
            outRect.set(2, 2, 2, 0);
        else
            outRect.set(0, 2, 2, 0);
    }
}
