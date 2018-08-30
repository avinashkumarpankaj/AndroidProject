package com.androidproject.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidproject.R;

import com.androidproject.utils.Constants;
import com.androidproject.utils.UtilClass;
import com.androidproject.adapter.CanadaListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidproject.model.CanadaDetail;
import com.androidproject.model.ResponseListener;
import com.androidproject.servercommunication.ServerCommunicator;

import static com.androidproject.utils.Constants.LIST_TAG;

/**
 * Created by Avinash on 8/28/2018.
 */

public class ListActivity extends AppCompatActivity implements ResponseListener, View.OnClickListener {

    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerViewList;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_retry)
    LinearLayout layoutRetry;

    @BindView(R.id.btn_retry)
    Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);
        initializeView();

    }

    private void initializeView() {

        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);

        btnRetry.setOnClickListener(this);
        callApi();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                callApi();
            }
        });
    }

    private void callApi() {

        if (UtilClass.isNetworkAvailable(this)) {   //check if user is connected to internet or not

            layoutRetry.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(true);
            ServerCommunicator serverCommunicator = new ServerCommunicator(this, this);
            serverCommunicator.makeGetRequest(this, Constants.URL, LIST_TAG, CanadaDetail.class);
        } else {

            layoutRetry.setVisibility(View.VISIBLE);
            Toast.makeText(this, getResources().getString(R.string.no_internet_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Object obj, int tag) {

        if (tag == LIST_TAG) {

            if (swipeRefreshLayout.isRefreshing()) {

                swipeRefreshLayout.setRefreshing(false);
            }
            recyclerViewList.setHasFixedSize(true);

            CanadaDetail canadaDetail = (CanadaDetail) obj;

            getSupportActionBar().setTitle(canadaDetail.getTitle());
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerViewList.setLayoutManager(mLayoutManager);
            CanadaListAdapter adapter = new CanadaListAdapter(canadaDetail.getRows());
            recyclerViewList.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(VolleyError error, int tag) {

        if (swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        if (view == btnRetry) {

            callApi();
        }
    }
}
