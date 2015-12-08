package naman14.droidcon.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * draws the circle with spikes
 */
public class WheelSpikesView extends View {

    Paint paint = new Paint();

    //radius of wheel
    int radius = 500;

    //center of wheel
    //x coordinate - 600
    //y coordinate - 600
    Point centerPoint = new Point(600, 600);

    public WheelSpikesView(Context context) {
        super(context);
    }

    public WheelSpikesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set paint properties for drawing circle
        paint.setColor(Color.parseColor("#009688"));

        //draw the main circle wheel
        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, paint);

        //set paint properties of spikes
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);

        //draw the spike lines (8 lines to be drawn)
        drawCirclePoints(canvas, 8, radius);
    }


    /**
     * finds the xy coordinates of points on the perimeter of circle lying equal distance apart
     * and the draws the lines joining center of wheel and such xy coordinates
     *
     * @param canvas canvas where the spikes will be drawn
     * @param points the number of points to find or the number of lines (spikes) to be drawn
     * @param radius the radius of the wheel
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
            canvas.drawLine(centerPoint.x, centerPoint.y, p.x, p.y, paint);
        }
    }
}
