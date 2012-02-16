package com.sep.planningpoker.activities;

import com.sep.planningpoker.models.decks.Deck;
import com.sep.planningpoker.models.decks.DeckFactory;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import com.sep.planningpoker.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends PlanningPokerActivity {

    ViewFlipper mFlipper;

    private Deck mCurrentDeck;

    private View mUpArrowView;
    private View mDownArrowView;
    private TextView mEstimateTextView;

    private int mCardPosition = 0;
    private DeckFactory mDeckFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mFlipper = (ViewFlipper) findViewById(R.id.flipper);
        mFlipper.setOnTouchListener(null);
        mFlipper.setOnClickListener(null);
        
        View instructionView = getLayoutInflater().inflate(R.layout.instructions, null);
        View estimateView = getLayoutInflater().inflate(R.layout.estimate, null);
        
        mFlipper.addView(instructionView);
        mFlipper.addView(estimateView);

        mDeckFactory = new DeckFactory(this);

        mUpArrowView = findViewById(R.id.up_arrow);
        mDownArrowView = findViewById(R.id.down_arrow);

        mEstimateTextView = (TextView) findViewById(R.id.estimate_display);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/DejaVuSansCondensedBold.ttf");
        mEstimateTextView.setTypeface(typeFace);
    }

    @Override
    public void onResume() {
        super.onResume();

        mCurrentDeck = getCurrentDeck();

        mCardPosition = mCurrentDeck.getStartingCardIndex();

        updateArrowVisibility();
        setDisplayItem();
    }
    
    public void onDealMeIn(View v) {
        if (mFlipper.indexOfChild(mFlipper.getCurrentView()) != 0) return;
        onFlip(v);
    }

    public boolean onFlip(View v) {
        AnimationFactory.flipTransition(mFlipper, FlipDirection.LEFT_RIGHT);
        return true;
    }

    private Deck getCurrentDeck() {
        return mDeckFactory.getDeck();
    }

    public void setDeckFactory(DeckFactory factory) {
        mDeckFactory = factory;
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
        Log.d("NDS", getCurrentCardValue());
        mEstimateTextView.setText(Html.fromHtml(getCurrentCardValue()).toString());
    }

    public String getCurrentCardValue() {
        return mCurrentDeck.getCard(mCardPosition);

    }

}