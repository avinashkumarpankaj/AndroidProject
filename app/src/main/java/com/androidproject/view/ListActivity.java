package com.androidproject.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidproject.R;

import Utils.Constants;
import Utils.UtilClass;
import adapter.CanadaListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.CanadaDetail;
import model.ResponseListener;
import servercommunication.ServerCommunicator;

/**
 * Created by Avinash on 8/28/2018.
 */

public class ListActivity extends AppCompatActivity implements ResponseListener {

    private final int LIST_TAG = 100001;

    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerViewList;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        if (UtilClass.isNetworkAvailable(this)) {
            ServerCommunicator serverCommunicator = new ServerCommunicator(this, this);
            serverCommunicator.makeGetRequest(this, Constants.URL, LIST_TAG, CanadaDetail.class);
        } else {

            Toast.makeText(this, "Oops! It seems you are not connected with internet.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccess(Object obj, int tag) {

        recyclerViewList.setHasFixedSize(true);

        CanadaDetail canadaDetail = (CanadaDetail) obj;

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewList.setLayoutManager(mLayoutManager);
        CanadaListAdapter adapter = new CanadaListAdapter(canadaDetail.getRows());
        recyclerViewList.setAdapter(adapter);
    }

    @Override
    public void onFailure(VolleyError error, int tag) {

        Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
