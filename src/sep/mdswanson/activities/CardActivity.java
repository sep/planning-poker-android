package sep.mdswanson.activities;

import sep.mdswanson.R;
import sep.mdswanson.application.Actions;
import sep.mdswanson.models.decks.Deck;
import sep.mdswanson.models.decks.DeckFactory;
import sep.mdswanson.models.decks.DeckType;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CardActivity extends PlanningPokerActivity {

    private Deck mCurrentDeck;
    private DeckFactory mDeckFactory;

    private View mUpArrowView;
    private View mDownArrowView;
    private TextView mEstimateTextView;

    private int mCardPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);

        mDeckFactory = new DeckFactory(this);
        mCurrentDeck = mDeckFactory.buildDeck(DeckType.TSHIRT_SIZE);

        mUpArrowView = findViewById(R.id.up_arrow);
        mDownArrowView = findViewById(R.id.down_arrow);

        mEstimateTextView = (TextView) findViewById(R.id.estimate_display);

        updateArrowVisibility();
        setDisplayItem();
    }

    public void onFlip(View v) {
        startActivity(new Intent().setAction(Actions.INSTRUCTIONS));
    }

    public void onUpArrowClicked(View v) {
        if (mCardPosition == mCurrentDeck.size() - 1)
            return;
        mCardPosition++;
        updateArrowVisibility();
        setDisplayItem();
    }

    public void onDownArrowClicked(View v) {
        if (mCardPosition == 0)
            return;
        mCardPosition--;
        updateArrowVisibility();
        setDisplayItem();
    }

    private void updateArrowVisibility() {
        setArrowVisibility(0, mDownArrowView);
        setArrowVisibility(mCurrentDeck.size() - 1, mUpArrowView);
    }

    private void setArrowVisibility(int target, View arrow) {
        if (mCardPosition == target) {
            arrow.setVisibility(View.INVISIBLE);
        } else {
            arrow.setVisibility(View.VISIBLE);
        }
    }

    private void setDisplayItem() {
        mEstimateTextView.setText(mCurrentDeck.getCard(mCardPosition));
    }

}
