package com.SoftwareDesign.Notizen;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4; // 추가 : deprecated 대체
import androidx.test.platform.app.InstrumentationRegistry;//추가 : deprecated 대체

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();


        assertEquals("com.delaroystudios.alarmreminder", appContext.getPackageName());
    }
}
