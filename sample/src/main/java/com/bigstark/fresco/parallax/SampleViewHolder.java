package com.bigstark.fresco.parallax;

import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bigstark.fresco.parallax.library.ParallaxDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;

/**
 * Created by bigstark on 2017. 8. 3..
 */

public class SampleViewHolder extends RecyclerView.ViewHolder {

    private ParallaxDraweeView ivSample;


    public SampleViewHolder(View itemView) {
        super(itemView);
        ivSample = (ParallaxDraweeView) itemView.findViewById(R.id.iv_sample);

        int resourceId = itemView.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int status = itemView.getResources().getDimensionPixelSize(resourceId);
        int screenHeight = itemView.getResources().getDisplayMetrics().heightPixels;

        ivSample.setParentTopAndBottom(status, screenHeight);
    }


    public void bind(String uri) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(uri))
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                        super.onFinalImageSet(id, imageInfo, anim);
                        if (imageInfo == null) {
                            return;
                        }

                        ivSample.update();
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        super.onIntermediateImageSet(id, imageInfo);
                        if (imageInfo == null) {
                            return;
                        }

                        ivSample.update();
                    }
                })
                .build();

        ivSample.setController(controller);
    }

    public void scroll() {
        if (ivSample != null) {
            ivSample.scroll();
        }
    }
}
