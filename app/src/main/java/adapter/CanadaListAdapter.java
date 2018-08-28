package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Row;

/**
 * Created by Avinash on 8/28/2018.
 */

public class CanadaListAdapter extends RecyclerView.Adapter<CanadaListAdapter.ListViewHolder> {
    private ArrayList<Row> mList;

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTxtTitle;

        @BindView(R.id.description)
        TextView mTxtDescription;

        @BindView(R.id.image_view)
        ImageView mImage;

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
            holder.mTxtTitle.setText(rowData.getTitle());
            if (rowData.getDescription() != null)
                holder.mTxtDescription.setText(rowData.getDescription());
            else
                holder.mTxtDescription.setText("**Description not available**");


            //Load image with piccaso
            Picasso.get()
                    .load(rowData.getImageHref())
                    .placeholder(R.drawable.blank_image)
                    .error(R.drawable.blank_image)
                    .into(holder.mImage);

        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}