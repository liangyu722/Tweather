package com.twitter.challenge.domain

import com.twitter.challenge.common.InvalidCalculationException
import com.twitter.challenge.common.Result.Error
import com.twitter.challenge.common.Result.Success
import com.twitter.challenge.data.WeatherEntity
import com.twitter.challenge.data.WeatherRepository
import com.twitter.challenge.model.TempStandDeviation
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewWeatherUseCaseTest {

    private lateinit var sut: ViewWeatherUseCase
    private val repository: WeatherRepository = mockk()

    @Before
    fun setup() {
        sut = ViewWeatherUseCase(repository)
    }

    @Test
    fun `getFutureWeatherStandardDeviation correct value passed to repository`() = runBlockingTest {
        // Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns emptyWeatherEntry
        //Act
        sut.getFutureWeatherStandardDeviation(5)
        //Assert
        coVerify(exactly = 1) { repository.getFutureWeatherEntry(5) }
    }

    @Test
    fun `getFutureWeatherStandardDeviation return error from repository`() = runBlockingTest {
        //Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns errorWeatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(5)
        //Assert
        result shouldBeInstanceOf Error::class
        (result as Error).exception shouldEqual errorWeatherEntry.exception
    }

    @Test
    fun `getFutureWeatherStandardDeviation with empty future list days`() = runBlockingTest {
        // Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns emptyWeatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(5)
        //Assert
        result shouldBeInstanceOf Error::class
        (result as Error).exception shouldBeInstanceOf InvalidCalculationException::class
    }

    @Test
    fun `getFutureWeatherStandardDeviation handles large temperature from repository`() = runBlockingTest {
        //Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns largeWeatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(3)
        //Assert
        result shouldBeInstanceOf Success::class
        (result as Success).data shouldEqual largeTempStandDeviation
    }

    @Test
    fun `getFutureWeatherStandardDeviation handles negative temperature from repository`() = runBlockingTest {
        //Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns negativeWeatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(3)
        //Assert
        result shouldBeInstanceOf Success::class
        (result as Success).data shouldEqual negativeTempStandDeviation
    }

    @Test
    fun `getFutureWeatherStandardDeviation single weather entry from repository`() = runBlockingTest {
        //Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns singleWeatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(1)
        //Assert
        result shouldBeInstanceOf Error::class
        (result as Error).exception shouldBeInstanceOf InvalidCalculationException::class
    }

    @Test
    fun `getFutureWeatherStandardDeviation calculate standard deviation`() = runBlockingTest {
        //Arrange
        coEvery { repository.getFutureWeatherEntry(any()) } returns weatherEntry
        //Act
        val result = sut.getFutureWeatherStandardDeviation(5)
        //Assert
        result shouldBeInstanceOf Success::class
        (result as Success).data shouldEqual weatherTempStandDeviation
    }

    //----------------------------------Helper-------------------------------------------------
    private val xl1 = WeatherEntity("xl1", 500000.0f, 50.0, 50)
    private val xl2 = WeatherEntity("xl2", 800000.0f, 50.0, 50)
    private val xl3 = WeatherEntity("xl3", 1500000000000.0f, 50.0, 50)

    private val ng1 = WeatherEntity("ng1", -1f, 50.0, 50)
    private val ng2 = WeatherEntity("ng2", -53f, 50.0, 50)
    private val ng3 = WeatherEntity("ng3", -150f, 50.0, 50)

    private val we1 = WeatherEntity("we1", 16.83f, 50.0, 50)
    private val we2 = WeatherEntity("we2", 11.15f, 50.0, 50)
    private val we3 = WeatherEntity("we3", 14.20f, 50.0, 50)
    private val we4 = WeatherEntity("we4", 9.88f, 50.0, 50)
    private val we5 = WeatherEntity("we5", 19.19f, 50.0, 50)

    private val emptyWeatherEntry = Success(emptyList<WeatherEntity>())
    private val errorWeatherEntry = Error(Exception("Fake exception"))
    private val largeWeatherEntry = Success(listOf(xl1, xl2, xl3))
    private val largeTempStandDeviation = TempStandDeviation(3, 8.660250438781503E11, 1.558845109231977E12)
    private val negativeWeatherEntry = Success(listOf(ng1, ng2, ng3))
    private val negativeTempStandDeviation = TempStandDeviation(3, 75.62407024221852, 136.12332737927557)
    private val singleWeatherEntry = Success(listOf(we1))
    private val weatherEntry = Success(listOf(we1, we2, we3, we4, we5))
    private val weatherTempStandDeviation = TempStandDeviation(5, 3.8655338010029467, 6.957960161619819)
}
