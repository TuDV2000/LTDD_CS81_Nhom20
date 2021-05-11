package com.example.salebookapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class RegistActivityTest {
    private RegistActivity registActivity = null;
    String text;
    @Before
    public void setUp() throws Exception {
        ActivityController<RegistActivity> activityActivityController = Robolectric.buildActivity(RegistActivity.class).create().resume().visible();
        registActivity = activityActivityController.get();
    }
    @Test
    public void test() throws Exception{
        text = registActivity.txtChangeLogin.getText().toString();
        assertEquals("swdwd",text);
    }
}