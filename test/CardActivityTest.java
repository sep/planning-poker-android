import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.mdswanson.R;
import sep.mdswanson.activities.CardActivity;
import sep.mdswanson.models.decks.Deck;
import sep.mdswanson.models.decks.DeckFactory;
import android.view.View;
import android.widget.TextView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class CardActivityTest {

    private CardActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = new CardActivity();
        activity.onCreate(null);
        activity.onResume();
    }

    @Test
    public void itShouldDisplayALargerCardValueWhenTheUpArrowIsPresed() {
        setupFakeDeck("1", "2", "3", "4");

        TextView estimateTextView = (TextView) activity.findViewById(R.id.estimate_display);
        assertThat(estimateTextView.getText().toString(), equalTo("2"));

        View upArrow = (View) activity.findViewById(R.id.up_arrow);
        upArrow.performClick();

        assertThat(estimateTextView.getText().toString(), equalTo("3"));
    }

    @Test
    public void itShouldDisplayASmallerCardValueWhenTheDownArrowIsPresed() {
        setupFakeDeck("1", "2", "3", "4");

        TextView estimateTextView = (TextView) activity.findViewById(R.id.estimate_display);
        assertThat(estimateTextView.getText().toString(), equalTo("2"));

        View downArrow = (View) activity.findViewById(R.id.down_arrow);
        downArrow.performClick();

        assertThat(estimateTextView.getText().toString(), equalTo("1"));
    }

    private void setupFakeDeck(String... cards) {
        DeckFactory stubbedFactory = mock(DeckFactory.class);
        when(stubbedFactory.getDeck()).thenReturn(new Deck(cards));
        activity.setDeckFactory(stubbedFactory);
        activity.onResume();
    }
}
