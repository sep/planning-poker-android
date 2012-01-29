package sep.mdswanson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CardActivity extends Activity {

	private String[] mEstimateOptions;
	private int mEstimatePosition = 0;

	private View mUpArrowView;
	private View mDownArrowView;
	private TextView mEstimateTextView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        
        mEstimateOptions = getResources().getStringArray(R.array.fibonacci_options);
        
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
    	if (mEstimatePosition == mEstimateOptions.length - 1) return;
    	mEstimatePosition++;
    	updateArrowVisibility();
    	setDisplayItem();
    }    
    
    public void onDownArrowClicked(View v) {
    	if (mEstimatePosition == 0) return;
    	mEstimatePosition--;
    	updateArrowVisibility();
    	setDisplayItem();
    }   
    
    private void updateArrowVisibility() {
    	setArrowVisibility(0, mDownArrowView);
    	setArrowVisibility(mEstimateOptions.length - 1, mUpArrowView);
    }
    
    private void setArrowVisibility(int target, View arrow) {
    	if (mEstimatePosition == target) {
    		arrow.setVisibility(View.INVISIBLE);
    	} else {
    		arrow.setVisibility(View.VISIBLE);
    	}
    }
    
    private void setDisplayItem() {
    	mEstimateTextView.setText(mEstimateOptions[mEstimatePosition]);
    }
    
}

