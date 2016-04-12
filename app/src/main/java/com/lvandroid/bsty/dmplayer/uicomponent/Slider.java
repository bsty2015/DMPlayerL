package com.lvandroid.bsty.dmplayer.uicomponent;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lvandroid.bsty.dmplayer.R;

/**
 * Created by bsty on 4/12/16.
 */
public class Slider extends CustomView {
    private int backgroundColor = Color.parseColor("#4CAF50");
    private int backgroundcolorLine = Color.parseColor("#4CAF50");
    private Ball ball;
    private Bitmap bitmap;
    private int max = 100;
    private int min = 0;

    private OnValueChangedListener onValueChangedListener;
    private boolean placedBall = false;
    private boolean press = false;
    private int value = 0;

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public OnValueChangedListener getOnValueChangedListener() {
        return onValueChangedListener;
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.onValueChangedListener = onValueChangedListener;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        if (placedBall == false) {
            post(new Runnable() {
                @Override
                public void run() {
                    setValue(value);
                }
            });
        } else {
            this.value = value;
        }
    }

    /**
     * set attributes of XML to view
     *
     * @param attrs
     */
    private void setAttributes(AttributeSet attrs) {
        setBackgroundResource(R.drawable.background_transparent);

        //set size of view
        setMinimumHeight(dpToPx(48, getResources()));
        setMinimumWidth(dpToPx(80, getResources()));

        //set background color
        //color by resource
        int bacgroundcolor = attrs.getAttributeResourceValue(ANDROIDXML, "background", -1);
        if (bacgroundcolor != -1) {
            setBackgroundColor(getResources().getColor(bacgroundcolor));
        } else {
            //color by hexadecimal
            int background = attrs.getAttributeIntValue(ANDROIDXML, "background", -1);
            if (background != -1)
                setBackgroundColor(background);
        }

        min = attrs.getAttributeIntValue(MATERIALDESIGNXML, "min", 0);
        max = attrs.getAttributeIntValue(MATERIALDESIGNXML, "max", 0);
        value = attrs.getAttributeIntValue(MATERIALDESIGNXML, "value", min);

        ball = new Ball(getContext());
        LayoutParams params = new LayoutParams(dpToPx(15, getResources()), dpToPx(15, getResources()));
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        ball.setLayoutParams(params);
        addView(ball);
    }

    /**
     * convert Dp to pixel
     *
     * @param dp
     * @param resources
     * @return
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    class Ball extends View {
        float xIni, xFin, xCen;

        public Ball(Context context) {
            super(context);
        }

        public void changeBackground() {
            setBackgroundResource(R.drawable.background_checkbox);
            LayerDrawable layer = (LayerDrawable) getBackground();
            GradientDrawable shape = (GradientDrawable) layer.findDrawableByLayerId(R.id.shape_bacground);
            shape.setColor(backgroundColor);
        }
    }

    //Event when slider change value
    public interface OnValueChangedListener {
        public void onValueChanged(int value);
    }
}
