package com.example.xyzreader.extras;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerInitialConf {

    public RecyclerInitialConf(Context context, RecyclerView recyclerView, boolean isGridLayout, RecyclerView.Adapter adapter) {
        configRecyclerView(context, recyclerView, isGridLayout, adapter);
    }

    private void configRecyclerView(Context context, RecyclerView recyclerView, boolean isGridLayout, RecyclerView.Adapter adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(isGridLayout ? new GridLayoutManager(context, 2) : new LinearLayoutManager(context));
        if (isGridLayout) {
            int spanCount = 2;
            int spacing = MyUtils.dpToPx(context);

            recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        }
        recyclerView.setAdapter(adapter);
    }
}