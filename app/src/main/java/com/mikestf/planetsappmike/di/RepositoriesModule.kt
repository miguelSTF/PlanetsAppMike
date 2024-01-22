package com.mikestf.planetsappmike.di

import com.mikestf.planetsappmike.data.repositories.PlanetRepositoryImpl
import com.mikestf.planetsappmike.domain.repositories.PlanetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*
* Modulo para proveer los repositories
*/
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindPlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository
}