import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sep.mdswanson.R;
import sep.mdswanson.activities.CardActivity;
import sep.mdswanson.application.Actions;
import sep.mdswanson.application.IntentKeys;
import android.content.Intent;
import android.view.View;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowView;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class CardActivityTest {

    private CardActivity activity;
    private View digitContainer;
    private ShadowActivity shadowActivity;

    @Before
    public void setUp() throws Exception {
        activity = new CardActivity();
        shadowActivity = shadowOf(activity);
        activity.onCreate(null);
        activity.onResume();
    }

    @Test
    public void itShouldLaunchFreezeCardActivityWhenILongTouchOnTheNumber() {
        performLongClickOnDigitsContainer();

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat(startedIntent, notNullValue());
        assertThat(startedIntent.getAction(), equalTo(Actions.FREEZE_CARD));
    }

    @Test
    public void itShouldPassTheCardsDigitAsAnExtraToFreezeCardActivity() {
        performLongClickOnDigitsContainer();

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        String cardValueExtra = startedIntent.getExtras().getString(IntentKeys.CARD_VALUE);
        assertThat(cardValueExtra, notNullValue());
        assertThat(cardValueExtra, equalTo("5"));
    }

    private void performLongClickOnDigitsContainer() {
        digitContainer = (View) activity.findViewById(R.id.digit_container);
        ShadowView sDigitContainer = (ShadowView) shadowOf(digitContainer);

        boolean handledLongClick = sDigitContainer.performLongClick();
        assertThat(handledLongClick, equalTo(true));
    }
}
