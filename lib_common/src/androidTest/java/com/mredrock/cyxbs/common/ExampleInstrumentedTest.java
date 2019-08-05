package com.mredrock.cyxbs.common;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented freshman_define_window, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under freshman_define_window.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mredrock.cyxbs.common.freshman_define_window", appContext.getPackageName());
    }
}
