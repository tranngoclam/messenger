package com.donguyen.domain.usecase.attachment

import com.donguyen.domain.repository.AttachmentRepository
import com.donguyen.domain.usecase.Result
import com.donguyen.domain.util.None
import com.donguyen.domain.util.TestTransformer
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by DoNguyen on 28/10/18.
 */
@RunWith(MockitoJUnitRunner::class)
class DeleteAttachmentUseCaseTest {

    // sut
    private lateinit var deleteAttachmentUseCase: DeleteAttachmentUseCase

    // dependencies
    @Mock
    private lateinit var attachmentRepository: AttachmentRepository
    private val transformer = TestTransformer<Result<None>>()

    @Before
    fun setUp() {
        deleteAttachmentUseCase = DeleteAttachmentUseCase(attachmentRepository, transformer)
    }

    @Test
    fun testDeleteAttachmentSucceeded() {
        // GIVEN
        val input = DeleteAttachmentUseCase.Input("attachmentId")
        val result = Result.success(None())
        `when`(attachmentRepository.deleteAttachment(input.attachmentId))
                .thenReturn(Observable.just(result))

        // WHEN
        val testObserver = deleteAttachmentUseCase.execute(input).test()

        // THEN
        testObserver
                .assertValue(result)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun testDeleteAttachmentFailed() {
        // GIVEN
        val input = DeleteAttachmentUseCase.Input("attachmentId")
        val result = Result.failure<None>("delete attachment failed")
        `when`(attachmentRepository.deleteAttachment(input.attachmentId))
                .thenReturn(Observable.just(result))

        // WHEN
        val testObserver = deleteAttachmentUseCase.execute(input).test()

        // THEN
        testObserver
                .assertValue(result)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun testDeleteAttachmentError() {
        // GIVEN
        val input = DeleteAttachmentUseCase.Input("attachmentId")
        val throwable = Throwable("error")
        `when`(attachmentRepository.deleteAttachment(input.attachmentId))
                .thenReturn(Observable.error(throwable))

        // WHEN
        val testObserver = deleteAttachmentUseCase.execute(input).test()

        // THEN
        testObserver
                .assertValue(Result.failure(throwable.message.orEmpty()))
                .assertNoErrors()
                .assertComplete()
    }
}