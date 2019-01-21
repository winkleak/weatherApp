package com.home.wink.weatherapp

import com.home.wink.weatherapp.data.network.ForecastApi
import com.home.wink.weatherapp.data.network.response.ForecastNetworkModel
import com.home.wink.weatherapp.data.storage.ForecastModelDb
import com.home.wink.weatherapp.data.storage.ForecastsDao
import com.home.wink.weatherapp.domain.repository.RealForecastRepository
import com.home.wink.weatherapp.utils.toForecasts
import com.home.wink.weatherapp.utils.toForecastsDbModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.*

class ForecastRepositoryUnitTest {
    private lateinit var forecastsDao: ForecastsDao
    private var currentTime: Long = Date().time
    private var fiveMinutes: Long = 5 * 60 * 60 * 1000L
    private val timeStep: Long = 10000L
    private lateinit var api: ForecastApi
    private val ID_WITH_ACTUAL_DATA_IN_DB = 0
    private val ID_WITH_OLD_DATA_IN_DB = 1
    private val ID_WITHOUT_DATA_IN_DB = 2
    private val forecastsDB = mutableListOf(
        ForecastModelDb(
            0, 0, "", 0, 0.0, 0, 0.0, 0,
            null, "", 0.0, 0.0, 0, 0
        )
    )
    private val forecastsNetworkModel: ForecastNetworkModel = mock()


    @Before
    @Throws(Exception::class)
    fun before() {
        this.forecastsDao = mockForecastDao()
        this.api = mockApi()
    }

    @Test
    fun dbHaveActualData() {
        val repository = makeRepository()

        val expectedList =
            listOf(forecastsDB.first().copy(refreshingDate = currentTime - timeStep)).toForecasts()

        val testObservable = repository.getForecastsForCity(ID_WITH_ACTUAL_DATA_IN_DB).test()
        testObservable.awaitCount(1)
        verify(forecastsDao, times(0)).deleteAllWithCityId(ID_WITH_ACTUAL_DATA_IN_DB)
        testObservable.assertValue(expectedList)
    }

    @Test
    fun dbHaveEmptyData() {
        val repository = makeRepository()

        val expectedList = forecastsNetworkModel.toForecastsDbModel().toForecasts()

        val testObservable = repository.getForecastsForCity(ID_WITHOUT_DATA_IN_DB).test()
        testObservable.awaitCount(1)
        verify(forecastsDao, times(1)).deleteAllWithCityId(ID_WITHOUT_DATA_IN_DB)
        testObservable.assertValue(expectedList)
    }

    @Test
    fun dbHaveOldData() {
        val repository = makeRepository()

        val expectedList = forecastsNetworkModel.toForecastsDbModel().toForecasts()

        val testObservable = repository.getForecastsForCity(ID_WITH_OLD_DATA_IN_DB).test()
        testObservable.awaitCount(1)
        verify(forecastsDao, times(1)).deleteAllWithCityId(ID_WITH_OLD_DATA_IN_DB)
        testObservable.assertValue(expectedList)
    }

    private fun makeRepository(
        dao: ForecastsDao = this.forecastsDao,
        api: ForecastApi = this.api
    ) = RealForecastRepository(api, dao)


    private fun mockForecastDao(): ForecastsDao {
        return mock {
            on { getForecastsByCity(ID_WITH_ACTUAL_DATA_IN_DB) } doReturn Single.just(
                listOf(forecastsDB.first().copy(refreshingDate = currentTime - timeStep))
            )
            on { getForecastsByCity(ID_WITH_OLD_DATA_IN_DB) } doReturn Single.just(
                listOf(forecastsDB.first().copy(refreshingDate = currentTime - fiveMinutes - 10000))
            )
            on { getForecastsByCity(ID_WITHOUT_DATA_IN_DB) } doReturn Single.just(
                emptyList()
            )
        }
    }

    private fun mockApi(): ForecastApi {
        return mock {
            on { getForecastFromCity(ID_WITHOUT_DATA_IN_DB) } doReturn Single.just(
                forecastsNetworkModel
            )
            on { getForecastFromCity(ID_WITH_OLD_DATA_IN_DB) } doReturn Single.just(
                forecastsNetworkModel
            )
            on { getForecastFromCity(ID_WITH_ACTUAL_DATA_IN_DB) } doReturn Single.just(
                forecastsNetworkModel
            )

        }
    }
}