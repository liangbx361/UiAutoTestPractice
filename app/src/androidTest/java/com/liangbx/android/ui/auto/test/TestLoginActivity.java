package com.liangbx.android.ui.auto.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/7/5.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestLoginActivity {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.liangbx.android.ui.auto.test";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mUiDevice;

//    @Rule
//    ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        mUiDevice = UiDevice.getInstance(instrumentation);
        mUiDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mUiDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mUiDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testLogin() throws Exception {
        UiObject emailObject = mUiDevice.findObject(new UiSelector()
                .resourceId("com.liangbx.android.ui.auto.test:id/email")
                .className("android.widget.EditText"));

        emailObject.setText("liangbx361@163.com");

        UiObject passwordObject = mUiDevice.findObject(new UiSelector()
                .resourceId("com.liangbx.android.ui.auto.test:id/password")
                .className("android.widget.EditText"));

        passwordObject.setText("123456");

        UiObject loginObject = mUiDevice.findObject(new UiSelector()
                .resourceId("com.liangbx.android.ui.auto.test:id/email_sign_in_button")
                .className("android.widget.Button"));

        loginObject.click();
    }
}
