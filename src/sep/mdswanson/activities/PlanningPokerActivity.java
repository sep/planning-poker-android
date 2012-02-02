package sep.mdswanson.activities;

import sep.mdswanson.R;
import android.app.Activity;
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
                //TODO: launch settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
