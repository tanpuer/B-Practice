package com.example.cw.b_practice.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.test.adapter.RecyclerTestAdapter;

/**
 * Created by cw on 2017/5/24.
 */

public class RecyclerViewTestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerTestAdapter adapter = new RecyclerTestAdapter(200, this);
        mRecyclerView.setAdapter(adapter);
    }
}
