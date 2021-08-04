package com.alfonso.myapplication.showlist

import com.alfonso.myapplication.showlist.fake.FakeListViewPresenter
import com.alfonso.myapplication.showlist.fake.FakeRepositoryPopular
import com.alfonso.myapplication.showlist.presenter.imp.PopularPresenterImp
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
class PopularPresenterImpTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var fakeViewPresenter : FakeListViewPresenter
    private lateinit var fakeRepositoryPopular : FakeRepositoryPopular
    private lateinit var popularPresenter : PopularPresenterImp

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeViewPresenter = FakeListViewPresenter()
        fakeRepositoryPopular = FakeRepositoryPopular()
        popularPresenter = PopularPresenterImp(fakeRepositoryPopular,fakeViewPresenter,testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Get popular movie happy path Show Loading False No empty List`() = testDispatcher.runBlockingTest {
        popularPresenter.getPopularMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(1,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Get popular movie Repository error Showed error true Empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.getPopularMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }

    @Test
    fun `Refresh popular movie happy path show loading false no empty list id 2`() = testDispatcher.runBlockingTest {
        popularPresenter.refreshPopularMovies()

        Assert.assertFalse(fakeViewPresenter.isShowingLoading)
        Assert.assertTrue(fakeViewPresenter.list.isNotEmpty())
        Assert.assertEquals(2,fakeViewPresenter.list[0].id)
    }

    @Test
    fun `Refresh popular movie with repository error, showed error true, empty list`() = testDispatcher.runBlockingTest {
        fakeRepositoryPopular.returnError = true

        popularPresenter.refreshPopularMovies()

        Assert.assertTrue(fakeViewPresenter.showedError)
        Assert.assertTrue(fakeViewPresenter.list.isEmpty())
    }

}