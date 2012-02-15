package sep.mdswanson.activities;

import sep.mdswanson.R;
import sep.mdswanson.application.IntentKeys;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FacedownActivity extends PlanningPokerActivity {

    private String mCardValue;
    private TextView mEstimateDisplay;
    private TextView mTapToRevealDisplay;
    private View mBackgroundContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facedown);

        mCardValue = getIntent().getExtras().getString(IntentKeys.CARD_VALUE);

        mTapToRevealDisplay = (TextView) findViewById(R.id.tap_to_reveal_text);
        mEstimateDisplay = (TextView) findViewById(R.id.estimate_text);
        mBackgroundContainer = (View) findViewById(R.id.background_container);
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    public boolean onFacedownCardClicked(View v) {
        mEstimateDisplay.setText(mCardValue);
        
        mTapToRevealDisplay.setVisibility(View.GONE);
        mBackgroundContainer.setBackgroundDrawable(null);
        
        return true;
    }
}
