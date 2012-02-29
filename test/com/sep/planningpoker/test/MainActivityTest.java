package com.sep.planningpoker.test;
import com.sep.planningpoker.R;

import com.sep.planningpoker.activities.MainActivity;
import com.sep.planningpoker.application.ApplicationPreferences;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowToast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
    public void shouldDisplayCardScreenIfThisIsNotTheFirstTimeILaunchTheApp() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(false);
        
        activity.onCreate(null);
        activity.onResume();

        assertTrue(activity.isEstimateViewIsShowing());
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
    public void shouldDisplayToastIfThisIsTheFirstTimeILaunchTheApp() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(true);
        
        activity.onCreate(null);
        activity.onResume();
        
        String tapToStartToastText = activity.getResources().getString(R.string.tap_to_start);
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo(tapToStartToastText));
    }
    
    @Test
    public void shouldNotDisplayToastIfThisINotsTheFirstTimeILaunchTheApp() {
        when(mockPreferences.getIsFirstTimeLaunched()).thenReturn(false);
        
        activity.onCreate(null);
        activity.onResume();
        
        assertNull(ShadowToast.getLatestToast());
    }
}