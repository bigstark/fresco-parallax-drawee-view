package com.bigstark.fresco.parallax.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by bigstark on 2016. 12. 1..
 */

public class ParallaxDraweeView extends SimpleDraweeView {

    private final RectF rect = new RectF();
    private final Matrix matrix = new Matrix();
    private float scale = 0f;
    private float ratio = 0f;
    private float translationX = 0f;

    private int parentTop = 0;
    private int parentBottom = 0;

    private float distance = 0;
    private float lastTranslationY = 0;



    public ParallaxDraweeView(Context context) {
        super(context);
    }

    public ParallaxDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void update() {
        initMatrixValues();
        initTranslationValues();
        scroll();
    }



    public void setParentTopAndBottom(int top, int bottom) {
        parentTop = top;
        parentBottom = bottom;

        initTranslationValues();
        scroll();
    }

    private void initMatrixValues() {
        matrix.reset();
        getHierarchy().getActualImageBounds(rect);
        matrix.mapRect(rect);

        scale = (float) getWidth()/ rect.width();
        ratio = rect.height() / rect.width();

        translationX = - (getWidth() - rect.width()) / 2 * scale;
        matrix.postScale(scale, scale);
        matrix.postTranslate(translationX, 0f);
    }


    private void initTranslationValues() {
        distance = (parentBottom - getHeight()) - parentTop;
        lastTranslationY = -(ratio * getWidth() - getHeight());
    }


    public void scroll() {
        resetMatrix();
        translate(getCurrentTranslationY());
    }


    private void resetMatrix() {
        matrix.reset();
        matrix.mapRect(rect);
        matrix.postScale(scale, scale);
        matrix.postTranslate(translationX, 0f);
    }


    int[] location = new int[2];
    public float getCurrentTranslationY() {
        getLocationInWindow(location);

        int top = location[1];
        float percent = (parentBottom - getHeight() - top) / distance;
        return (1 - percent) * lastTranslationY;
    }


    private void translate(float y) {
        matrix.postTranslate(0, y);
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(matrix);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

}
