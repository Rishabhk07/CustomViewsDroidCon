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

    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    RectF oval1 = new RectF();
    RectF oval2 = new RectF();

    int sweepAngle1 = 220;
    int sweepAngle2 = 150;

    int startAngle1 = 120;
    int startAngle2 = 0;

    boolean increasing1 = true;
    boolean increasing2 = true;

    public CustomProgressBar(Context context) {
        super(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint1.setColor(Color.parseColor("#009688"));
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(10);

        paint2.setColor(Color.parseColor("#77009688"));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);

        post(animate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        oval1.set(getWidth() / 2 - 120, getHeight() / 2 - 120, getWidth() / 2 + 120, getHeight() / 2 + 120);
        oval2.set(getWidth() / 2 - 80, getHeight() / 2 - 80, getWidth() / 2 + 80, getHeight() / 2 + 80);

        canvas.drawArc(oval1, startAngle1, sweepAngle1, false, paint1);
        canvas.drawArc(oval2, startAngle2, sweepAngle2, false, paint2);

    }

    private Runnable animate = new Runnable() {
        @Override
        public void run() {

            if (startAngle1 < 360)
                startAngle1 += 20;
            else startAngle1 = 0;

            if (startAngle2 < 360)
                startAngle2 += 20;
            else startAngle2 = 0;

            if (increasing1) {
                if (sweepAngle1 < 220) {
                    sweepAngle1 += 10;
                } else increasing1 = false;
            } else {
                if (sweepAngle1 > 0) {
                    sweepAngle1 -= 10;
                } else increasing1 = true;
            }

            if (increasing2) {
                if (sweepAngle2 < 150) {
                    sweepAngle2 += 10;
                } else increasing2 = false;
            } else {
                if (sweepAngle2 > 0) {
                    sweepAngle2 -= 10;
                } else increasing2 = true;
            }

            invalidate();
            postDelayed(this, 10);
        }
    };
}
