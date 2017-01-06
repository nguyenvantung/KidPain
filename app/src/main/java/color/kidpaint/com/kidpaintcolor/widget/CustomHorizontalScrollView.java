package color.kidpaint.com.kidpaintcolor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by Tung Nguyen on 1/4/2017.
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    private boolean mHasReachedEnd;
    private CustomScrollListener mListener;

    public interface CustomScrollListener {
        void onLeftReached();

        void onNonReached();

        void onRightReached();
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHasReachedEnd = false;
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHasReachedEnd = false;
    }

    public CustomHorizontalScrollView(Context context) {
        super(context);
        this.mHasReachedEnd = false;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(getChildCount() - 1);
        int diff = view.getRight() - (getWidth() + getScrollX());
        if (view.getLeft() == l && this.mListener != null) {
            this.mListener.onLeftReached();
            this.mHasReachedEnd = false;
        } else if (diff == 0 && this.mListener != null) {
            this.mListener.onRightReached();
            this.mHasReachedEnd = true;
        } else if (this.mListener != null) {
            this.mListener.onNonReached();
            this.mHasReachedEnd = false;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public CustomScrollListener getCustomScrollListener() {
        return this.mListener;
    }

    public void setCustomScrollListener(CustomScrollListener customScrollListener) {
        this.mListener = customScrollListener;
    }

    public boolean hasReachedEnd() {
        return this.mHasReachedEnd;
    }
}
