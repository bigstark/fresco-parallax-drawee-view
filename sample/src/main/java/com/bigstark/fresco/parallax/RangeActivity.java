package com.bigstark.fresco.parallax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.bigstark.fresco.parallax.library.ParallaxDraweeView;

public class RangeActivity extends AppCompatActivity {

    private ParallaxDraweeView pdvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        pdvSample = (ParallaxDraweeView) findViewById(R.id.pdv_sample);
        pdvSample.setImageURI(Defines.IMAGE_URL);

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
