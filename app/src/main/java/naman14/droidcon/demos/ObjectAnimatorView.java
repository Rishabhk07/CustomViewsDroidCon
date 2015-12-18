package naman14.droidcon.demos;

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


    public void startAnimation() {

        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf2 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe kf1 = Keyframe.ofFloat(1f, 300f);

        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("radius", kf0, kf1, kf2);
        radiusAnimator = ObjectAnimator.ofPropertyValuesHolder(this, pvhRotation);
        radiusAnimator.setInterpolator(new LinearInterpolator());
        radiusAnimator.setDuration(2000);
        radiusAnimator.start();
    }

    public void setRadius(float value) {
        this.radius = value;
        invalidate();
    }
}
