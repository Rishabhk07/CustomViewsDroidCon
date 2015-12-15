package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * a music visualizer sort of animation (with random data)
 */
public class MusicVisualizer extends View {

    Random random = new Random();

    Paint paint = new Paint();

    public MusicVisualizer(Context context) {
        super(context);
    }

    public MusicVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);

        //start runnable
        removeCallbacks(animateView);
        post(animateView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set paint color
        paint.setColor(Color.parseColor("#009688"));
        //set paint style, Style.FILL will fill the color, Style.STROKE will stroke the color
        paint.setStyle(Paint.Style.FILL);

        //i varies from 0 to width of screen increasing by 100 on each loop
        for (int i = 0; i < getWidth(); i += 100) {

            /**
             * draws a rectangle with specified dimensions
             * left - distance of the left side of rectangular from left side of canvas.
             * top - distance of top side of rectangular from the top side of canvas.
             * right - distance of the right side of rectangular from left side of canvas.
             * bottom- distance of the bottom side of rectangle from top side of canvas.
             * paint - the paint to be used for drawing rectangle
             */

            /**
             * left - i+20, 20 will be the margin between two rectangles, i is 0,100,200,300... till the end of screen
             * top - getHeight() - random.nextInt(getHeight() / 4)
             *       the height is random between 0 and maxHeight/4
             * right - i + 100, the width of rectangle is 100
             * bottom- getHeight(), hence bottom of rectangle is at the bottom of screen
             */

            canvas.drawRect(i + 20, getHeight() - random.nextInt(getHeight() / 4), i + 100, getHeight(), paint);
        }


    }

    private Runnable animateView = new Runnable() {
        @Override
        public void run() {

            //run every 150 ms
            postDelayed(this, 150);

            invalidate();
        }
    };
}
