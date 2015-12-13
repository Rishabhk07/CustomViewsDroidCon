package naman14.droidcon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by naman on 07/12/15.
 */
public class CustomView extends View {

    Paint paint = new Paint();
    int varRadius = 0;
    int radius = 500;
    Point centerPoint = new Point(600, 600);

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        animateSpikes();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#009688"));
        paint.setAntiAlias(false);

        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);

        drawCirclePoints(canvas, 8, varRadius);

    }

    void drawCirclePoints(Canvas canvas, int points, double radius) {
        double slice = 2 * Math.PI / points;
        for (int i = 0; i < points; i++) {
            double angle = slice * i;
            int newX = (int) (centerPoint.x + radius * Math.cos(angle));
            int newY = (int) (centerPoint.y + radius * Math.sin(angle));
            Point p = new Point(newX, newY);
            canvas.drawLine(centerPoint.x, centerPoint.y, (int) p.x, (int) p.y, paint);
        }
    }


    private Runnable animateSpikes = new Runnable() {
        @Override
        public void run() {

            boolean reachedCircleEnd = false;

            if (varRadius < radius) {
                varRadius = varRadius + 15;
            } else reachedCircleEnd = true;

            if (!reachedCircleEnd) {
                postDelayed(this, 15);
            }
            invalidate();
        }
    };

    public void animateSpikes() {
        removeCallbacks(animateSpikes);
        post(animateSpikes);

    }
}
