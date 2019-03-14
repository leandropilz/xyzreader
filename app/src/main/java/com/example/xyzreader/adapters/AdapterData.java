package com.example.xyzreader.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.extras.AdapterListener;
import com.example.xyzreader.extras.ImageLoader;
import com.example.xyzreader.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class AdapterData extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mList = new ArrayList<>();
    private AdapterListener mListener;

    public AdapterData(AdapterListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_list, viewGroup, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemHolder) viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        private AdapterListener mListener;

        ItemHolder(@NonNull View itemView, AdapterListener listener) {
            super(itemView);
            mListener = listener;
        }

        void bind(final Item item) {
            /*Carrega a imagem thumb.*/
            ImageLoader.load(itemView.getContext(), item.getThumb(), (ImageView) itemView.findViewById(R.id.item_view_img));
            ((TextView) itemView.findViewById(R.id.item_view_primary_text)).setText(item.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemAdapterClick(item);
                }
            });
        }
    }

    /**
     * Adiciona dados ao adapter.
     *
     * @param list recebe o list de dados.
     */
    public void addItems(List<Item> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
}