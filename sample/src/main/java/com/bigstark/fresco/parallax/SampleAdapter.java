package com.bigstark.fresco.parallax;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bigstark on 2017. 8. 3..
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

    public static final String[] URLS = {
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
            "https://www.android.com/static/2016/img/one/a1_andy_1x.png"
    };

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_sample, null);

        int screenWidth = parent.getResources().getDisplayMetrics().widthPixels;
        int height = (int) (screenWidth / 2f);

        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(screenWidth, height);
        itemView.setLayoutParams(params);

        return new SampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.bind(URLS[position]);
    }

    @Override
    public int getItemCount() {
        return URLS.length;
    }
}
