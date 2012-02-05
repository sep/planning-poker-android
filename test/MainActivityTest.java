import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.mdswanson.R;
import sep.mdswanson.activities.MainActivity;
import sep.mdswanson.application.Actions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;
    private Button dealMeInButton;
    private ImageView flipArrow;

    @Before
    public void setUp() throws Exception {
        activity = new MainActivity();
        activity.onCreate(null);

        dealMeInButton = (Button) activity.findViewById(R.id.deal_me_in);
        flipArrow = (ImageView) activity.findViewById(R.id.flip_arrow);
    }

    @Test
    public void shouldGoToCardActivityWhenDealMeInButtonIsPressed() {
        dealMeInButton.performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);

        assertThat(shadowIntent.getAction(), equalTo(Actions.CARD));
    }

    @Test
    public void shouldGoToCardActivityWhenFlipArrowIsPressed() {
        flipArrow.performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);

        assertThat(shadowIntent.getAction(), equalTo(Actions.CARD));
    }
}