package com.bigstark.fresco.parallax;

import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigstark.fresco.parallax.library.ParallaxDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;

public class RecyclerForwardActivity extends AppCompatActivity {

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

                    // top == screenHeight                  -> offset 1
                    // top == statusBarHeight - viewHeight  -> offset 0
                    // (top - statusBarHeight + viewHeight) / (screenHeight - statusBarHeight + viewHeight)

                    int top = view.getTop();
                    int viewHeight = view.getHeight();
                    float offset = (float) (top - statusBarHeight + viewHeight) / (screenHeight - statusBarHeight + viewHeight);
                    if (holder instanceof ForwardViewHolder) {
                        ((ForwardViewHolder) holder).setOffset(offset);
                    }
                }
            }
        });
        rvSample.setAdapter(new ForwardAdapter());
    }

    private class ForwardAdapter extends RecyclerView.Adapter<ForwardViewHolder> {

        @Override
        public ForwardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recycler, parent, false);
            return new ForwardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ForwardViewHolder holder, int position) {}

        @Override
        public int getItemCount() {
            return 30;
        }

    }


    private class ForwardViewHolder extends RecyclerView.ViewHolder {

        ParallaxDraweeView pdvSample;

        public ForwardViewHolder(View itemView) {
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
