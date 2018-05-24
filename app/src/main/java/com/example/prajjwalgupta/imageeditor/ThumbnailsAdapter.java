package com.example.prajjwalgupta.imageeditor;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Prajjwal Gupta on 5/24/2018.
 */

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.ViewHolder> {

    private List<ThumbnailItem> thumbnailItemList;
    public ThumbnailsAdapterListener listener;
    private Context mContext;
    private int selectedIndex = 0;

    public ThumbnailsAdapter(Context context, List<ThumbnailItem> thumbnailItemList, ThumbnailsAdapterListener listener) {
        mContext = context;
        this.thumbnailItemList = thumbnailItemList;
        this.listener = listener;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        @BindView(R.id.filter_name)
        TextView filterName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thumbnail_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final ThumbnailItem thumbnailItem = thumbnailItemList.get(position);
        holder.thumbnail.setImageBitmap(thumbnailItem.image);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFilterSelected(thumbnailItem.filter);
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });

        holder.filterName.setText(thumbnailItem.filterName);
        if(selectedIndex == position)
        {
            holder.filterName.setTextColor(ContextCompat.getColor(mContext, R.color.filter_label_selected));
        } else {
            holder.filterName.setTextColor(ContextCompat.getColor(mContext, R.color.filter_label_normal));
        }
        }


    @Override
    public int getItemCount() {
        return thumbnailItemList.size();
    }


    public   interface  ThumbnailsAdapterListener {
        void onFilterSelected(Filter filter);
    }




}


