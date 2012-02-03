package sep.mdswanson.activities;

import sep.mdswanson.R;
import sep.mdswanson.application.Actions;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PlanningPokerActivity extends Activity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent().setAction(Actions.SETTINGS));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
