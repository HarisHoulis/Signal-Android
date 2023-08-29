package com.app.signal.data.service

import app.cash.turbine.test
import com.app.signal.data.service.photo.PhotoServiceImpl
import com.app.signal.domain.model.State
import com.app.signal.domain.repository.PhotoRepository
import com.app.signal.domain.service.AppStorage
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoServiceImplTest {

    private val fakeRepo = mockk<PhotoRepository>(relaxed = true) {
        every { markPhotoAsFavourite(any()) } returns flow { emit(State.Success(1)) }
    }
    private val fakeStorage = mockk<AppStorage>(relaxed = true)
    private val subject = PhotoServiceImpl(photoRepository = fakeRepo, storage = fakeStorage)

    @Test
    fun testMarkPhotoAsFavourite() = runTest {
        val photoId = "photo_id"
        subject.markPhotoAsFavourite(photoId).test {
            awaitItem()
            verify { fakeRepo.markPhotoAsFavourite(photoId) }
            cancelAndConsumeRemainingEvents()
        }
    }
}