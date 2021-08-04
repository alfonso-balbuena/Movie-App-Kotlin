package com.alfonso.myapplication.showlist

import com.alfonso.myapplication.showlist.fake.FakeListViewPresenter
import com.alfonso.myapplication.showlist.fake.FakeRepositoryNowPlaying
import com.alfonso.myapplication.showlist.presenter.imp.NowPlayingPresenterImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NowPlayingPresenterImpTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var fakeViewPresenter : FakeListViewPresenter
    private lateinit var fakeRepositoryPopular : FakeRepositoryNowPlaying
    private lateinit var popularPresenter : NowPlayingPresenterImp

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeViewPresenter = FakeListViewPresenter()
        fakeRepositoryPopular = FakeRepositoryNowPlaying()
        popularPresenter = NowPlayingPresenterImp(fakeRepositoryPopular,fakeViewPresenter,testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Get now playing movies happy path Show Loading False No empty List`() = testDispatcher.runBlockingTest {
        popularPresenter.getNowPlayingMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(1,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Get now playing movies Repository error Showed error true Empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.getNowPlayingMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }

    @Test
    fun `Refresh now playing movies happy path show loading false no empty list id 2`() = testDispatcher.runBlockingTest {
        popularPresenter.refreshNowPlayingMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(2,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Refresh now playing movies with repository error, showed error true, empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.refreshNowPlayingMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }
}