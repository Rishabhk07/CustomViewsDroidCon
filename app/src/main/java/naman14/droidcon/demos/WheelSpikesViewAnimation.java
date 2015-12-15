package naman14.droidcon.demos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * draws the circle with spikes
 * spikes are drawn with animation-  spikes emerge from center and slowly reach the perimeter of circle
 */
public class WheelSpikesViewAnimation extends View {

    Paint paint = new Paint();

    //the actual radius of the wheel
    int radius = 500;

    //variable radius of wheel- ranging from 0 to actual radius
    int varRadius = 0;

    //center of wheel
    //x coordinate - 600
    //y coordinate - 600
    Point centerPoint = new Point(600, 600);


    public WheelSpikesViewAnimation(Context context) {
        super(context);
    }

    public WheelSpikesViewAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);

        //start animation
        animateSpikes();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#009688"));

        //draw the main circle wheel
        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);

        //draw the spike lines with animation(8 lines to be drawn)
        //we pass the variable radius here, the xy coordinates will be calculated using this radius
        //and lines will be drawn to the calculated points on each onDraw
        drawCirclePoints(canvas, 8, varRadius);

    }

    /**
     * finds the xy coordinates of points on the perimeter of circle lying equal distance apart
     * and the draws the lines joining center of wheel and such xy coordinates
     *
     * @param canvas canvas where the spikes will be drawn
     * @param points the number of points to find or the number of lines (spikes) to be drawn
     * @param radius the variable radius of the circles inside the wheel, this radius is increased by
     *               15 every 15 milliseconds using runnable.
     */
    void drawCirclePoints(Canvas canvas, int points, double radius) {

        //the circle is divided into pieces each having angle of 2π/<no. of points>
        double slice = 2 * Math.PI / points;

        for (int i = 0; i < points; i++) {

            // the angle θ of the line to be drawn
            double angle = slice * i;

            // the coordinates of the points
            // x coordinate - rcosθ + x
            // y coordinate -  rsinθ + y
            int newX = (int) (centerPoint.x + radius * Math.cos(angle));
            int newY = (int) (centerPoint.y + radius * Math.sin(angle));
            Point p = new Point(newX, newY);

            // draw a line from center of circle to the above coordinates
            canvas.drawLine(centerPoint.x, centerPoint.y, (int) p.x, (int) p.y, paint);
        }
    }


    private Runnable animateSpikes = new Runnable() {
        @Override
        public void run() {

            //boolean to keep track if the perimeter of circle is reached
            boolean reachedCircleEnd = false;

            if (varRadius < radius) {
                //increase varRadius by 15
                varRadius += 15;
            } else reachedCircleEnd = true;

            if (!reachedCircleEnd) {
                postDelayed(this, 15);
            }

            //call onDraw with the increased varRadius
            invalidate();
        }
    };


    public void animateSpikes() {
        removeCallbacks(animateSpikes);
        post(animateSpikes);

    }
}
