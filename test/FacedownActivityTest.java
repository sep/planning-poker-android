import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.mdswanson.R;
import sep.mdswanson.activities.FacedownActivity;
import sep.mdswanson.application.IntentKeys;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
public class FacedownActivityTest {

    private FacedownActivity activity;
    private ShadowActivity shadowActivity;
    private TextView revealTextView;

    @Before
    public void setUp() throws Exception {
        activity = new FacedownActivity();
        shadowActivity = shadowOf(activity);

        shadowActivity.setIntent(new Intent().putExtra(IntentKeys.CARD_VALUE, "40"));
        activity.onCreate(null);
        
        revealTextView = (TextView) activity.findViewById(R.id.tap_to_reveal_text);
    }

    @Test
    public void itDisplaysTapToRevealWhenLoaded() {
        String revealMsg = activity.getResources().getString(R.string.tap_to_reveal);

        assertThat(revealTextView.getText().toString(), equalTo(revealMsg));
    }

    @Test
    public void itShouldDisplayTheCorrectCardValueWhenTapped() {
        View container = (View) activity.findViewById(R.id.facedown_container);
        boolean handledClickEvent = container.performClick();

        TextView estimateTextView = (TextView) activity.findViewById(R.id.estimate_text);

        assertThat(handledClickEvent, equalTo(true));
        assertThat(estimateTextView.getText().toString(), equalTo("40"));
        assertThat(revealTextView.getVisibility(), equalTo(View.GONE));
    }
}
