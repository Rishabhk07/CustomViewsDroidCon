package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class WaveAnimationView extends View {

    private Paint paint;

    //the path of the wave
    private Path path;

    //2π
    private final double PI2 = 2 * Math.PI;

    //y coordinate offset
    private float offset = 0;

    public WaveAnimationView(Context context) {
        super(context);
    }

    public WaveAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint properties
        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#303F9F"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        //start runnable
        post(animate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    /**
     * Calculates the path of the wave to be drawn
     */
    private void calculatePath() {
        //reset the path and start over
        path.reset();
        //get wave offset, this offset is increased by 0.1 on each method call
        getWaveOffset();
        //move path to the left bottom of screen
        path.moveTo(getLeft(), getBottom());

        float y;

        //for each x coordinate, calculate y coordinate and draw a path to x,y
        for (float x = 0; x <= getWidth(); x++) {
            y = (float) (150 * Math.sin((PI2 / (getWidth() * 1.5) * x) + offset) + 800);
            path.lineTo(x, y);
        }
        //move path to right bottom of screen
        path.lineTo(getRight(), getBottom());
    }

    private Runnable animate = new Runnable() {
        @Override
        public void run() {
            synchronized (WaveAnimationView.this) {

                //calculate the wave path and then invalidate and draw it on canvas
                calculatePath();
                invalidate();

                postDelayed(this, 10);

            }
        }
    };


    /**
     * get y coordinate offset, offset gets increased by 0.1f till it reaches a little less
     * than max value of float and then becomes zero and again starts increasing
     */
    private void getWaveOffset() {

        if (offset > Float.MAX_VALUE - 100) {
            offset = 0;
        } else {
            offset += 0.1f;
        }
    }
}


