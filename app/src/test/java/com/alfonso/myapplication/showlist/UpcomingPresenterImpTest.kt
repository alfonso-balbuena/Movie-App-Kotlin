package com.alfonso.myapplication.showlist

import com.alfonso.myapplication.showlist.fake.FakeListViewPresenter
import com.alfonso.myapplication.showlist.fake.FakeRepositoryUpcoming
import com.alfonso.myapplication.showlist.presenter.imp.UpcomingPresenterImp
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
class UpcomingPresenterImpTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var fakeViewPresenter : FakeListViewPresenter
    private lateinit var fakeRepositoryPopular : FakeRepositoryUpcoming
    private lateinit var popularPresenter : UpcomingPresenterImp

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeViewPresenter = FakeListViewPresenter()
        fakeRepositoryPopular = FakeRepositoryUpcoming()
        popularPresenter = UpcomingPresenterImp(fakeRepositoryPopular,fakeViewPresenter,testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Get upcoming movies happy path Show Loading False No empty List`() = testDispatcher.runBlockingTest {
        popularPresenter.getUpcomingMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(1,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Get upcoming movies Repository error Showed error true Empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.getUpcomingMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }

    @Test
    fun `Refresh upcoming movies happy path show loading false no empty list id 2`() = testDispatcher.runBlockingTest {
        popularPresenter.refreshUpcomingMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(2,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Refresh upcoming movies with repository error, showed error true, empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.refreshUpcomingMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }
}