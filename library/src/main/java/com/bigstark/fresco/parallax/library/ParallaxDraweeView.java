package com.bigstark.fresco.parallax.library;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by bigstark on 2016. 12. 1..
 */

public class ParallaxDraweeView extends SimpleDraweeView {

    private ValueAnimator observerImageLoading;


    // It would be actual image rect.
    private final RectF rect = new RectF();

    // It would be draw matrix.
    private final Matrix matrix = new Matrix();

    // scale between image width and view width
    private float scale = 1f;


    // It is fixed when initiated first time.
    private float translationX = 0f;


    public ParallaxDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public ParallaxDraweeView(Context context) {
        super(context);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public ParallaxDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public ParallaxDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setScaleType(ScaleType.FIT_CENTER);
    }

    public ParallaxDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setScaleType(ScaleType.FIT_CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (observerImageLoading == null) {
            initObserver();
        }

        int saveCount = canvas.save();
        canvas.concat(matrix);

        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }


    private void initObserver() {
        observerImageLoading = ValueAnimator.ofInt(0, 1);
        observerImageLoading.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {}

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {
                resetMatrix();
                postInvalidate();

                if (rect.width() != 0) {
                    observerImageLoading.cancel();
                }
            }
        });
        observerImageLoading.setDuration(60);
        observerImageLoading.setRepeatCount(ValueAnimator.INFINITE);
        observerImageLoading.start();
    }


    private void resetMatrix() {
        matrix.reset();
        getHierarchy().getActualImageBounds(rect);
        matrix.mapRect(rect);

        scale = (float) getWidth() / rect.width();

        translationX = - (getWidth() - rect.width()) / 2 * scale;
        matrix.postScale(scale, scale);
        matrix.postTranslate(translationX, 0f);
    }



    public void scrollY(float percent) {
        resetMatrix();

        float translationY = (rect.height() * scale - getHeight()) * percent;
        matrix.postTranslate(0, -translationY);
        postInvalidate();
    }


}
