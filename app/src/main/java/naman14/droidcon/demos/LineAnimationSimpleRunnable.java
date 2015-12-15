package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * animates a line horizontally starting from 0 and reaching the end of screen
 * like a horizontal determinate progress bar
 */
public class LineAnimationSimpleRunnable extends View {

    Paint paint = new Paint();

    //the variable x position of the line
    float posX = 0;

    public LineAnimationSimpleRunnable(Context context) {
        super(context);
    }

    //XML Constructor
    public LineAnimationSimpleRunnable(Context context, AttributeSet attrs) {
        super(context, attrs);

        //start the animation
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set paint properties
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        /**
         * draws a line with variable x position of line end
         * startX - 0
         * startY - getHeight()/2 - Y position of line is in the middle of screen
         * stopX - posX - the variable position of the end x coordinate of line - this property is update from the runnable
         * stopY- getHeight()/2 - its a horizontal line, y coordinate remains same
         * paint - the paint to be used for drawing line
         */
        canvas.drawLine(0, getHeight()/2, posX, getHeight()/2, paint);
    }

    public void startAnimation() {
        removeCallbacks(animateLine);
        post(animateLine);
    }

    private Runnable animateLine = new Runnable() {
        @Override
        public void run() {

            // boolean to keep track if the end of screen is reached
            // animaton stops when screen end is reached
            boolean reachedEnd = false;

            //increase x coordinate by 10 till the end of screen is reached
            if (posX < getWidth())
                posX+= 10;
            else reachedEnd = true;

            //keep the runnable running till the end of screen is reached
            if (!reachedEnd) {
                //run every 15 milliseconds
                postDelayed(this, 15);
            }

            //finally call invalidate()- this will call the onDraw() method
            invalidate();
        }
    };
}
