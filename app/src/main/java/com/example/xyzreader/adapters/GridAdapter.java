package com.example.xyzreader.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.xyzreader.R;
import com.example.xyzreader.extras.AdapterListener;
import com.example.xyzreader.pojos.SocialItem;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter implements View.OnClickListener {

    private List<SocialItem> mList;
    private AdapterListener mListener;
    private int mPostion;

    public GridAdapter(AdapterListener listener) {
        this.mListener = listener;
        this.mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.mPostion = position;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_view, parent, false);
        }

        ImageView view = (ImageView) convertView;
        view.setImageResource(((SocialItem) getItem(position)).getIconSocilal());
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        mListener.onItemAdapterClick(getItem(this.mPostion));
    }

    /**
     * Adiciona os dados ao adapter.
     *
     * @param list recebe um array list contendo os itens.
     */
    public void addItem(List<SocialItem> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }
}
