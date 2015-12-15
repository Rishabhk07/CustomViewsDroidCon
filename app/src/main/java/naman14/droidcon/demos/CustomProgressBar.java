package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by naman on 14/12/15.
 */
public class CustomProgressBar extends View {

    Paint paint = new Paint();
    RectF oval = new RectF();

    int sweepAngle1 = 220;
    int sweepAngle2 = 150;
    int delta = 5;

    int startAngle1 = 120;
    int startAngle2 = 0;

    public CustomProgressBar(Context context) {
        super(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        post(animate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#009688"));
        paint.setStrokeWidth(12);
        oval.set(getWidth() / 2 - 150, getHeight() / 2 - 150, getWidth() / 2 + 150, getHeight() / 2 + 150);
        canvas.drawArc(oval, startAngle1, sweepAngle1, false, paint);

        paint.setColor(Color.parseColor("#99009688"));
        paint.setStrokeWidth(7);
        oval.set(getWidth() / 2 - 100, getHeight() / 2 - 100, getWidth() / 2 + 100, getHeight() / 2 + 100);
        canvas.drawArc(oval, startAngle2, sweepAngle2, false, paint);

    }

    private Runnable animate = new Runnable() {
        @Override
        public void run() {

            if (startAngle1 < 360)
                startAngle1 += 10;
            else startAngle1 = 0;

            if (startAngle2 < 360)
                startAngle2 += 10;
            else startAngle2 = 0;

            if (sweepAngle1 > 220) {
                delta = -5;
            } else if (sweepAngle1 < 5) {
                delta = 5;
            }
            sweepAngle1 += delta;

            if (sweepAngle2 > 150) {
                delta = -5;
            } else if (sweepAngle1 < 5) {
                delta = 5;
            }
            sweepAngle2 += delta;


            invalidate();
            postDelayed(this, 10);
        }
    };
}
