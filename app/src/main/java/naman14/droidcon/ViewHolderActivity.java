package naman14.droidcon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by naman on 08/12/15.
 */
public class ViewHolderActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getIntent().getIntExtra("layout_id",R.layout.activity_line_animation_simple_runnable));
    }
}
