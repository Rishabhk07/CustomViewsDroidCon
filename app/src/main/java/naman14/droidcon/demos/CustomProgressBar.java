package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * A custom circular indeterminate progressbar with two rings rotating in a varying angle
 */
public class CustomProgressBar extends View {

    Paint paint = new Paint();

    //the oval for the arcs
    RectF oval = new RectF();

    //the sweep angle of the outer arc
    int sweepAngle1 = 220;

    //the sweep angle of the inner arc
    int sweepAngle2 = 150;

    //delta of sweep angle change
    //sweep angle will be increased or decreased by this amount every 10ms
    int delta = 5;

    //start angle of outer arc, the arc will start at this value of angle
    int startAngle1 = 120;
    //start angle of inner arc
    int startAngle2 = 0;

    public CustomProgressBar(Context context) {
        super(context);
    }

    //XML Constructor
    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //set paint as stroke
        paint.setStyle(Paint.Style.STROKE);
        //start runnable
        post(animate);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //outer arc
        paint.setColor(Color.parseColor("#009688"));
        paint.setStrokeWidth(12);
        //oval.set(left,top,right,bottom);
        //radius of this oval will be (3000/2)=150
        oval.set(getWidth() / 2 - 150, getHeight() / 2 - 150, getWidth() / 2 + 150, getHeight() / 2 + 150);
        canvas.drawArc(oval, startAngle1, sweepAngle1, false, paint);


        //inner arc
        //add a little translucency to inner arc
        paint.setColor(Color.parseColor("#99009688"));
        paint.setStrokeWidth(7);
        //radius of this oval will be (200/2)=100
        oval.set(getWidth() / 2 - 100, getHeight() / 2 - 100, getWidth() / 2 + 100, getHeight() / 2 + 100);
        canvas.drawArc(oval, startAngle2, sweepAngle2, false, paint);

    }

    private Runnable animate = new Runnable() {
        @Override
        public void run() {

            //increase startAngle from 0 to 360
            if (startAngle1 < 360)
                startAngle1 += 10;
            else startAngle1 = 0;

            if (startAngle2 < 360)
                startAngle2 += 10;
            else startAngle2 = 0;

            //increase sweepAngle from 0 to 220 and then decrease from 220 to 0
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
