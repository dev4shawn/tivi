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

package app.tivi.data.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import app.tivi.data.entities.Episode
import io.reactivex.Flowable

@Dao
abstract class EpisodesDao : EntityDao<Episode> {
    @Query("SELECT * from episodes WHERE season_id = :seasonId ORDER BY number")
    abstract fun episodesFromSeasonId(seasonId: Long): List<Episode>?

    @Query("SELECT * from episodes WHERE trakt_id = :traktId")
    abstract fun episodeWithTraktId(traktId: Int): Episode?

    @Query("SELECT * from episodes WHERE tmdb_id = :tmdbId")
    abstract fun episodeWithTmdbId(tmdbId: Int): Episode?

    @Query("SELECT * from episodes WHERE id = :id")
    abstract fun episodeWithId(id: Long): Episode?

    @Query("SELECT trakt_id from episodes WHERE id = :id")
    abstract fun episodeTraktIdForId(id: Long): Int?

    @Query("SELECT * from episodes WHERE id = :id")
    abstract fun episodeWithIdFlowable(id: Long): Flowable<Episode>

    @Query("SELECT shows.id FROM shows" +
            " INNER JOIN seasons AS s ON s.show_id = shows.id" +
            " INNER JOIN episodes AS eps ON eps.season_id = s.id" +
            " WHERE eps.id = :episodeId")
    abstract fun showIdForEpisodeId(episodeId: Long): Long
}