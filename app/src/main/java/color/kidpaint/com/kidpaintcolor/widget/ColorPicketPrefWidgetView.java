package color.kidpaint.com.kidpaintcolor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class ColorPicketPrefWidgetView extends View {
    Paint paint;
    float rectSize;
    float strokeWidth;

    public ColorPicketPrefWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);

        float density = context.getResources().getDisplayMetrics().density;
        rectSize = (float) Math.floor(24.f * density + 0.5f);
        strokeWidth = (float) Math.floor(1.f * density + 0.5f);

        paint = new Paint();
        paint.setColor(0xffffffff);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(strokeWidth, strokeWidth, rectSize - strokeWidth, rectSize - strokeWidth, paint);
    }
}

