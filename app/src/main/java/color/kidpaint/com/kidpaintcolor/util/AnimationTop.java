package color.kidpaint.com.kidpaintcolor.util;

import android.animation.Animator;
import android.content.Context;
import android.view.View;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class AnimationTop implements Animator.AnimatorListener {
    private View view;
    private Context context;
    public AnimationTop(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
       /* RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.topMargin = context.getResources().getDimensionPixelSize(R.dimen.item_image_draw);
        view.setTranslationY(0.0f);
        view.setLayoutParams(params);*/
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationRepeat(Animator animation) {
    }
}
