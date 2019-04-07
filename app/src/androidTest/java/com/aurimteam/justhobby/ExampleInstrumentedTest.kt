package com.aurimteam.justhobby

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented change_back_edittext, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under change_back_edittext.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.aurimteam.justhobby", appContext.packageName)
    }
}
