package com.donguyen.domain.usecase.attachment

import com.donguyen.domain.repository.AttachmentRepository
import com.donguyen.domain.usecase.Result
import com.donguyen.domain.usecase.UseCase
import com.donguyen.domain.usecase.attachment.DeleteAttachmentUseCase.Input
import com.donguyen.domain.util.None
import com.donguyen.domain.util.Transformer
import io.reactivex.Observable

/**
 * Created by DoNguyen on 23/10/18.
 */
class DeleteAttachmentUseCase(private val attachmentRepository: AttachmentRepository,
                              transformer: Transformer<Result<None>>? = null)
    : UseCase<Input, None>(transformer) {

    override fun buildObservable(input: Input): Observable<Result<None>> {
        return attachmentRepository.deleteAttachment(input.attachmentId)
    }

    data class Input(val attachmentId: String)
}