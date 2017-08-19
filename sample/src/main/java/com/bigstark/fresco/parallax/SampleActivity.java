package com.bigstark.fresco.parallax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.bigstark.fresco.parallax.library.ParallaxDraweeView;

public class SampleActivity extends AppCompatActivity {

    private ParallaxDraweeView pdvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        pdvSample = (ParallaxDraweeView) findViewById(R.id.pdv_sample);
        pdvSample.setImageURI("http://vidur.net/wp-content/uploads/2017/04/17-mejores-ideas-sobre-iron-man-wallpaper-en-pinterest-ironman-iron-man-wallpaper-3.jpg");

        SeekBar seekBar = (SeekBar) findViewById(R.id.sb_sample);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pdvSample.setOffset((float) progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

}
