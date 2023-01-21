package com.example.featuredomian

import com.example.featuredomian.models.School
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // model test
    @Test
    fun dummySchoolObject() {
        // add more if needed
        val testObject = School (
            "123", "dummy_name", "", "", "",
            "", "", ""
                )

        assert(testObject.dbn == "123")
        assert(testObject.school_name != "hello")
    }

}