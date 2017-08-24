package com.bigstark.fresco.parallax;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigstark.fresco.parallax.library.ParallaxDraweeView;

public class RecyclerOppositeActivity extends AppCompatActivity {

    private int statusBarHeight;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        statusBarHeight = rect.top;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

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
                    if (holder == null) {
                        continue;
                    }

                    View view = holder.itemView;
                    if (view == null) {
                        continue;
                    }

                    // top == statusBarHeight - viewHeight      -> offset 1
                    // top == screenHeight                      -> offset 0
                    // (screenHeight - top) / (screenHeight - statusBarHeight + viewHeight)

                    int top = view.getTop();
                    int viewHeight = view.getHeight();
                    float offset = (float) (screenHeight - top) / (screenHeight - statusBarHeight + viewHeight);
                    if (holder instanceof OppositeViewHolder) {
                        ((OppositeViewHolder) holder).setOffset(offset);
                    }
                }
            }
        });
        rvSample.setAdapter(new OppositeAdapter());
    }

    private class OppositeAdapter extends RecyclerView.Adapter<OppositeViewHolder> {

        @Override
        public OppositeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recycler, parent, false);
            return new OppositeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OppositeViewHolder holder, int position) {}

        @Override
        public int getItemCount() {
            return 30;
        }

    }


    private class OppositeViewHolder extends RecyclerView.ViewHolder {

        ParallaxDraweeView pdvSample;

        public OppositeViewHolder(View itemView) {
            super(itemView);
            pdvSample = (ParallaxDraweeView) itemView.findViewById(R.id.pdv_sample);
            pdvSample.setImageURI(Defines.IMAGE_URL);
        }



        public void setOffset(float offset) {
            if (pdvSample == null) {
                return;
            }

            pdvSample.setOffset(offset);
        }
    }
}
