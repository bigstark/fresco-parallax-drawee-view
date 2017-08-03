package com.bigstark.fresco.parallax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Fresco.initialize(this);


        RecyclerView rvSample = (RecyclerView) findViewById(R.id.rv_sample);
        rvSample.setLayoutManager(new LinearLayoutManager(this));
        rvSample.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager =  (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                    RecyclerView.ViewHolder holder = recyclerView.findViewHolderForLayoutPosition(i);
                    if (holder instanceof SampleViewHolder) {
                        ((SampleViewHolder) holder).scroll();
                    }
                }
            }
        });
        rvSample.setAdapter(new SampleAdapter());
    }

}
