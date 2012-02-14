import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.mdswanson.R;
import sep.mdswanson.activities.CardActivity;
import sep.mdswanson.application.Actions;
import sep.mdswanson.application.IntentKeys;
import sep.mdswanson.models.decks.Deck;
import sep.mdswanson.models.decks.DeckFactory;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowView;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.mockito.Mockito.*;

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
        assertThat(startedIntent.getAction(), equalTo(Actions.FACEDOWN_CARD));
    }

    @Test
    public void itShouldPassTheCardsDigitAsAnExtraToFreezeCardActivity() {
        setupFakeDeck("X");

        performLongClickOnDigitsContainer();

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        String cardValueExtra = startedIntent.getExtras().getString(IntentKeys.CARD_VALUE);
        assertThat(cardValueExtra, notNullValue());
        assertThat(cardValueExtra, equalTo("X"));
    }

    @Test
    public void itShouldDisplayALargerCardValueWhenTheUpArrowIsPresed(){
        setupFakeDeck("1", "2", "3", "4");
        
        TextView estimateTextView = (TextView) activity.findViewById(R.id.estimate_display);
        assertThat(estimateTextView.getText().toString(), equalTo("2"));
        
        View upArrow = (View) activity.findViewById(R.id.up_arrow);
        upArrow.performClick();

        assertThat(estimateTextView.getText().toString(), equalTo("3"));
    }
    
    @Test
    public void itShouldDisplayASmallerCardValueWhenTheDownArrowIsPresed(){
        setupFakeDeck("1", "2", "3", "4");
        
        TextView estimateTextView = (TextView) activity.findViewById(R.id.estimate_display);
        assertThat(estimateTextView.getText().toString(), equalTo("2"));
        
        View downArrow = (View) activity.findViewById(R.id.down_arrow);
        downArrow.performClick();

        assertThat(estimateTextView.getText().toString(), equalTo("1"));
    }
    
    private void performLongClickOnDigitsContainer() {
        digitContainer = (View) activity.findViewById(R.id.digit_container);
        ShadowView sDigitContainer = (ShadowView) shadowOf(digitContainer);

        boolean handledLongClick = sDigitContainer.performLongClick();
        assertThat(handledLongClick, equalTo(true));
    }
    
    private void setupFakeDeck(String... cards) {
        DeckFactory stubbedFactory = mock(DeckFactory.class);
        when(stubbedFactory.getDeck()).thenReturn(new Deck(cards));
        activity.setDeckFactory(stubbedFactory);
        activity.onResume();
    }
}
