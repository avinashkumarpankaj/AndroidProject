package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Row;

public class CanadaListAdapter extends RecyclerView.Adapter<CanadaListAdapter.MyViewHolder> {
    private ArrayList<Row> mList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTxtTitle;

        @BindView(R.id.description)
        TextView mTxtDescription;

        @BindView(R.id.image)
        ImageView mImage;

        MyViewHolder(View v) {
            super(v);

        }
    }

    public CanadaListAdapter(ArrayList<Row> list) {
        mList = list;
    }

    @NonNull
    @Override
    public CanadaListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_row, parent, false);

        ButterKnife.bind(this, v);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Row rowData = mList.get(position);

        holder.mTxtTitle.setText(rowData.getTitle());
        holder.mTxtDescription.setText(rowData.getDescription());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}