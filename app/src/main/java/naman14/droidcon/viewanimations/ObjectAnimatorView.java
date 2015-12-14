package naman14.droidcon.viewanimations;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by naman on 14/12/15.
 */
public class ObjectAnimatorView extends View {

    Paint paint = new Paint();
    float radius;
    Animator radiusAnimator;

    public ObjectAnimatorView(Context context) {
        super(context);
    }

    public ObjectAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.parseColor("#009688"));
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }

    public void setRadius(float value) {
        this.radius = value;
        invalidate();
    }

    public void startAnimation() {

        if (radiusAnimator == null || !radiusAnimator.isRunning()) {

            // Define what value the radius is supposed to have at specific time values
            Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
            Keyframe kf2 = Keyframe.ofFloat(0.5f, 200f);
            Keyframe kf1 = Keyframe.ofFloat(1f, 400f);

            //make sure to have setRadius method if we pass 'radius' as property
            PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("radius", kf0, kf1, kf2);
            radiusAnimator = ObjectAnimator.ofPropertyValuesHolder(this, pvhRotation);
            radiusAnimator.setInterpolator(new LinearInterpolator());
            radiusAnimator.setDuration(2000);
            radiusAnimator.start();
        }
    }
}
