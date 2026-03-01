/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2025
 *
 * Project: AAID
 * Module: AAID.app.unitTest
 */

package cl.figonzal.aaid.ui.screens.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class AAIDViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() {
        val viewModel = AAIDViewModel(ioDispatcher = testDispatcher)
        assertEquals(AaidState.Loading, viewModel.state)
    }

    @Test
    fun `requestAAID success emits Success state`() = runTest {
        val expectedAaid = "91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1"
        val viewModel = AAIDViewModel(ioDispatcher = testDispatcher)

        viewModel.requestAAID { expectedAaid }
        advanceUntilIdle()

        assertEquals(AaidState.Success(expectedAaid), viewModel.state)
    }

    @Test
    fun `requestAAID with IOException emits Error state`() = runTest {
        val viewModel = AAIDViewModel(ioDispatcher = testDispatcher)

        viewModel.requestAAID { throw IOException("network error") }
        advanceUntilIdle()

        assertTrue(viewModel.state is AaidState.Error)
        assertEquals("network error", (viewModel.state as AaidState.Error).message)
    }

    @Test
    fun `requestAAID with generic exception emits Error state`() = runTest {
        val viewModel = AAIDViewModel(ioDispatcher = testDispatcher)

        viewModel.requestAAID { throw Exception("GMS not available") }
        advanceUntilIdle()

        assertTrue(viewModel.state is AaidState.Error)
        assertEquals("GMS not available", (viewModel.state as AaidState.Error).message)
    }
}
