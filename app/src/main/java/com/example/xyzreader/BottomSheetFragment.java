package com.example.xyzreader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.xyzreader.adapters.GridAdapter;
import com.example.xyzreader.extras.AdapterListener;
import com.example.xyzreader.pojos.SocialItem;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class BottomSheetFragment extends BottomSheetDialogFragment implements AdapterListener {

    private GridAdapter mAdapter;

    public BottomSheetFragment() {
        this.mAdapter = new GridAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GridView gridView = (GridView) inflater.inflate(R.layout.grid_view_layout, container, false);
        gridView.setAdapter(mAdapter);
        return gridView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<SocialItem> list = new ArrayList<>();
        list.add(new SocialItem(R.drawable.facebook));
        list.add(new SocialItem(R.drawable.facebook_messenger));
        list.add(new SocialItem(R.drawable.linkedin));
        list.add(new SocialItem(R.drawable.twitter));

        /*Adiciona ao adapter os itens.*/
        mAdapter.addItem(list);
    }

    @Override
    public void onItemAdapterClick(Object object) {
        dismiss();
    }
}
