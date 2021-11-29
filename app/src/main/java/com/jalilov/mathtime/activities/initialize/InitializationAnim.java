package com.jalilov.mathtime.activities.initialize;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jalilov.mathtime.R;

public class InitializationAnim {

    public static void initialize(Context mContext, View v, MotionEvent event){
        Animation scaleUp, scaleDown;
        scaleUp = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(mContext, R.anim.scale_down);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            v.startAnimation(scaleUp);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            v.startAnimation(scaleDown);
        }
    }

}
