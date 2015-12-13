package naman14.droidcon.viewanimations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by naman on 10/12/15.
 */
public class WaveAnimationView extends View {

    private Paint paint;
    private Path path;
    private final double PI2 = 2 * Math.PI;

    private float offset = 0;

    public WaveAnimationView(Context context) {
        super(context);
    }

    public WaveAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#303F9F"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        post(animate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    private void calculatePath() {
        path.reset();
        getWaveOffset();
        path.moveTo(getLeft(), getBottom());
        float y;
        for (float x = 0; x <= getWidth(); x++) {
            y = (float) (150 * Math.sin((PI2 / (getWidth() * 1.5) * x) + offset) + 800);
            path.lineTo(x, y);
        }
        path.lineTo(getRight(), getBottom());
    }

    private Runnable animate = new Runnable() {
        @Override
        public void run() {
            synchronized (WaveAnimationView.this) {

                calculatePath();
                invalidate();

                postDelayed(this, 10);

            }
        }
    };

    private void getWaveOffset() {

        if (offset > Float.MAX_VALUE - 100) {
            offset = 0;
        } else {
            offset += 0.1f;
        }
    }
}


