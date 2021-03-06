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

package app.tivi.home.followedshows

import android.arch.lifecycle.ViewModel
import app.tivi.inject.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class FollowedShowsBuilder {
    @ContributesAndroidInjector
    internal abstract fun followedShowsFragment(): FollowedShowsFragment

    @Binds
    @IntoMap
    @ViewModelKey(FollowedShowsViewModel::class)
    abstract fun bindFollowedShowsViewModel(viewModel: FollowedShowsViewModel): ViewModel
}