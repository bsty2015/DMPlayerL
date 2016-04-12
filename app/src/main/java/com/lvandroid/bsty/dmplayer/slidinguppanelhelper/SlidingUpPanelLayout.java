package com.lvandroid.bsty.dmplayer.slidinguppanelhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by bsty on 4/12/16.
 */
public class SlidingUpPanelLayout extends ViewGroup {
    private static final String TAG = SlidingUpPanelLayout.class.getSimpleName();

    /**
     * default peeking out panel height
     */
    private static final int DEFAULT_PANEL_HEIGHT = 68; //dp

    /**
     * Default anchor point height
     */
    private static final float DEFAULT_ANCHOR_POINT = 1.0f;//In relative %

    /**
     * Default initial state of the component
     */
    private static PanelState DEFAULT_SLIDE_STATE = PanelState.COLLAPSED;

    /**
     * Default height of the shadow above the peeking out panel
     */
    private static final int DEFAULT_SHADOW_HEIGHT = 4; //dp

    /**
     * If no fade color is given by default it will fade to 80% gray
     */
    private static final int DEFAULT_FADE_COLOR = 0x99000000;

    /**
     * Default Minimum velocity that will be detected as a fling
     */
    private static final int DEFAULT_MIN_FLING_VELOCITY = 400;//dips per second

    /**
     * Default is set to false because that is how it was written
     */
    private static final boolean DEFAULT_OVERLAY_FLAG = false;

    /**
     * Default is set to true for clip panel for performance reasons
     */
    private static final boolean DEFAULT_CLIP_PANEL_FLAG = true;

    /**
     * Default attributes for layout
     */
    private static final int[] DEFAULT_ATTRS = new int[]{android.R.attr.gravity};

    /**
     * Minimum velocity that will be detected as a fling
     */
    private int mMinFlingVelocity = DEFAULT_MIN_FLING_VELOCITY;

    /**
     * The fade color used for the panel covered by the slider. 0 = no fading
     */
    private int mCoveredFadeColor = DEFAULT_FADE_COLOR;

    /**
     * Default paralax length of the main view
     */
    private static final int DEFAULT_PARALAX_OFFSET = 0;


    /**
     * Current state of the slideable view
     */
    public enum PanelState {
        EXPANDED, COLLAPSED, ANCHORED, HIDDEN, DRAGGING
    }

    public SlidingUpPanelLayout(Context context) {
        super(context);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public SlidingUpPanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
