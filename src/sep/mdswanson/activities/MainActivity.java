package sep.mdswanson.activities;

import sep.mdswanson.R;
import sep.mdswanson.application.Actions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onFlip(View v) {
        startActivity(new Intent().setAction(Actions.CARD));
    }

}