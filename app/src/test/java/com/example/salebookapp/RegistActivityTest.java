//package com.example.salebookapp;
//
//import android.content.Intent;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.RuntimeEnvironment;
//
//import static org.junit.Assert.*;
//import static org.robolectric.Shadows.shadowOf;
//
//@RunWith(RobolectricTestRunner.class)
//public class RegistActivityTest{
//
//    @Test
//    public void testRegistionIsTrue(){
//        RegistActivity activity = Robolectric.setupActivity(RegistActivity.class);
//
//        activity.edtFullName.setText("");
//        activity.edtUserName.setText("");
//        activity.edtPassword.setText("");
//        activity.edtConfirmPass.setText("");
//        activity.edtCellPhone.setText("");
//        activity.edtAddress.setText("");
//
//        activity.btnCreate.performClick();
//
//        Intent expectedIntent = new Intent(activity, ConfirmActivity.class);
//        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());
//    }
//}