package com.raioncommunity.android.ob2017.animation;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by bradhawk on 8/20/2016.
 */
public class TranslateScaleAnimation {
    private final int Y = 1;
    private final int X = 0;
    private final int LENGTH_LOCATION_ARRAY = 2;
    private final long DURATION = 1000L;
    private ObjectAnimator objectAnimatorY;
    private ObjectAnimator objectAnimatorX;
    private ObjectAnimator objectAnimatorScaleX;
    private ObjectAnimator objectAnimatorScaleY;
    private View sourceView;
    private View destinationView;
    private float startTime = 0f;
    private float endTime = 1f;

    public TranslateScaleAnimation(View sourceView, View destinationView) {
        this.sourceView = sourceView;
        this.destinationView = destinationView;
    }

    public void update() {
        configureTranslation();
        configureScale();
    }

    private void configureTranslation() {
        int[] viewLocation = new int[LENGTH_LOCATION_ARRAY];
        getLeftPositionFrom(sourceView, viewLocation);
        int[] destinyViewLocation = new int[LENGTH_LOCATION_ARRAY];
        getLeftPositionFrom(destinationView, destinyViewLocation);
        objectAnimatorY = ObjectAnimator.ofFloat(sourceView, View.TRANSLATION_Y, 0, -(viewLocation[Y] - destinyViewLocation[Y]));
        objectAnimatorY.setInterpolator(new AccelerateInterpolator());
        objectAnimatorY.setDuration(DURATION);
        objectAnimatorX = ObjectAnimator.ofFloat(sourceView, View.TRANSLATION_X, 0, -(viewLocation[X] - destinyViewLocation[X]));
        objectAnimatorX.setInterpolator(new LinearInterpolator());
        objectAnimatorX.setDuration(DURATION);
    }

    private void configureScale() {
        float scaleXFactor = ((float) destinationView.getMeasuredWidth() / (float) sourceView.getMeasuredWidth());
        objectAnimatorScaleX = ObjectAnimator.ofFloat(sourceView, View.SCALE_X, 1f, scaleXFactor);
        objectAnimatorScaleX.setDuration(DURATION);
        objectAnimatorScaleX.setInterpolator(new LinearInterpolator());
        float scaleYFactor = ((float) destinationView.getMeasuredHeight() / (float) sourceView.getMeasuredHeight());
        objectAnimatorScaleY = ObjectAnimator.ofFloat(sourceView, View.SCALE_Y, 1f, scaleYFactor);
        objectAnimatorScaleY.setDuration(DURATION);
        objectAnimatorScaleY.setInterpolator(new LinearInterpolator());
    }

    private void getLeftPositionFrom(View view, int[] location) {
        int x = view.getLeft();
        int y = view.getTop();
        View parent = (View) view.getParent();
        while (parent != null) {
            x += parent.getLeft();
            y += parent.getTop();
            ViewParent viewParent = parent.getParent();
            if (viewParent instanceof View) {
                parent = (View) parent.getParent();
            } else {
                break;
            }
        }
        location[X] = x;
        location[Y] = y;
    }

    public void setCurrentTimeInAllAnimators(float playTime) {
        if (playTime >= startTime && playTime <= endTime) {
            if (objectAnimatorY != null && objectAnimatorX != null && objectAnimatorScaleX != null
                    && objectAnimatorScaleY != null) {
                long actualPlayTime = (long) ((playTime - startTime) * DURATION);
                objectAnimatorY.setCurrentPlayTime(actualPlayTime);
                objectAnimatorX.setCurrentPlayTime(actualPlayTime);
                objectAnimatorScaleX.setCurrentPlayTime(actualPlayTime);
                objectAnimatorScaleY.setCurrentPlayTime(actualPlayTime);
            }
        }
    }
}
