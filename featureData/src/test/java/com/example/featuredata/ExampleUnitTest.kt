package com.example.featuredata

import com.example.featuredata.api.FeatureApi
import com.example.featuredata.impl.FeatureRepoImpl
import com.example.featuredomian.models.School
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private val dispatcher = UnconfinedTestDispatcher()
    private val api = mockk<FeatureApi>(relaxed = true)
    private val repoImpl = FeatureRepoImpl(dispatcher, api)


    @Test
    fun testServerCall()  {

        val dummyResponses = mutableListOf<School>()
        dummyResponses.add(0, School (
            "123", "dummy_name", "", "", "",
            "", "", ""
        ))

        coEvery {
            api.getSchoolList()
        } returns dummyResponses

        runBlocking {
            assert(repoImpl.getSchoolList()[0].school_name == "dummy_name")
        }


    }
}