package com.androidproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.model.Row;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Avinash on 8/28/2018.
 */

public class CanadaListAdapter extends RecyclerView.Adapter<CanadaListAdapter.ListViewHolder> {
    private ArrayList<Row> mList;
    private Context context;

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTxtTitle;

        @BindView(R.id.description)
        TextView mTxtDescription;

        @BindView(R.id.image_view)
        ImageView mImage;

        @BindView(R.id.layout_parent)
        RelativeLayout layoutParent;

        ListViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public CanadaListAdapter(ArrayList<Row> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_row, parent, false);

        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        Row rowData = mList.get(position);

        if (rowData != null) {

            if (rowData.getDescription() == null && rowData.getImageHref() == null && rowData.getImageHref() == null) {

                holder.layoutParent.setVisibility(View.GONE);
            } else {

                // set view visible and set data to view only if atleast one value is not null
                holder.layoutParent.setVisibility(View.VISIBLE);

                holder.mTxtTitle.setText(rowData.getTitle());
                if (rowData.getDescription() != null)
                    holder.mTxtDescription.setText(rowData.getDescription());
                else
                    holder.mTxtDescription.setText(R.string.empty_description);
                //Load image with piccaso
                Picasso.get()
                        .load(rowData.getImageHref())
                        .placeholder(R.drawable.blank_image)
                        .error(R.drawable.blank_image)
                        .into(holder.mImage);
            }

        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}