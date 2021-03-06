/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.interactors

import app.tivi.data.entities.Episode
import app.tivi.data.repositories.episodes.SeasonsEpisodesRepository
import app.tivi.util.AppCoroutineDispatchers
import app.tivi.util.AppRxSchedulers
import io.reactivex.Flowable
import kotlinx.coroutines.experimental.CoroutineDispatcher
import javax.inject.Inject

class UpdateEpisodeDetails @Inject constructor(
    private val seasonsEpisodesRepository: SeasonsEpisodesRepository,
    dispatchers: AppCoroutineDispatchers,
    private val schedulers: AppRxSchedulers
) : SubjectInteractor<UpdateEpisodeDetails.Params, Episode>() {
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override fun createObservable(param: Params): Flowable<Episode> {
        return seasonsEpisodesRepository.observeEpisode(param.episodeId)
                .subscribeOn(schedulers.io)
    }

    override suspend fun execute(param: Params) {
        seasonsEpisodesRepository.updateEpisode(param.episodeId)
    }

    data class Params(val episodeId: Long, val forceLoad: Boolean)
}