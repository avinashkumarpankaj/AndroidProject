package com.androidproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.androidproject.R;

import butterknife.BindView;


public class ListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }


}
