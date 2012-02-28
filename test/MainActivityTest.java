import android.widget.ViewFlipper;

import com.sep.planningpoker.activities.MainActivity;
import com.sep.planningpoker.application.ApplicationPreferences;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowViewAnimator;
import com.xtremelabs.robolectric.shadows.ShadowViewFlipper;
import com.xtremelabs.robolectric.shadows.ShadowViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sep.planningpoker.R;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;
    private ApplicationPreferences mockPreferences;

    @Before
    public void setUp() throws Exception {
        activity = new MainActivity();

        mockPreferences = Mockito.mock(ApplicationPreferences.class);
        activity.setAppPreferences(mockPreferences);
    }

    @Test
    public void shouldDisplayInstructionScreenIfThisIsTheFirstTimeILaunchTheApp() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(true);
        
        activity.onCreate(null);
        activity.onResume();
        
        assertFalse(activity.isEstimateViewIsShowing());
    }
    
    @Test
    public void shouldDisplayCardScreenIfITapTheCard() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(true);
        
        activity.onCreate(null);
        activity.onResume();
        activity.onCardTapped(null);

        assertTrue(activity.isEstimateViewIsShowing());
    }
    
    @Test
    public void shouldDisplayCardScreenIfThisIsNotTheFirstTimeILaunchTheApp() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(false);
        
        activity.onCreate(null);
        activity.onResume();

        assertTrue(activity.isEstimateViewIsShowing());
    }
}