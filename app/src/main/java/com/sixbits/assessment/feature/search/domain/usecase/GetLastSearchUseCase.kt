package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import com.sixbits.reactive.interactor.NoParamsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetLastSearchUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val spotifyRepository: SpotifyRepository
) : NoParamsUseCase<List<SpotifyDataModel>>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(): Single<List<SpotifyDataModel>> {
        return spotifyRepository.getCached()
    }
}
