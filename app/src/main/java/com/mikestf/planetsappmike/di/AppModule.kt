package com.mikestf.planetsappmike.di

import android.app.Application
import androidx.room.Room
import com.mikestf.planetsappmike.data.UserDataBase
import com.mikestf.planetsappmike.data.repositories.UserRepositoryImpl
import com.mikestf.planetsappmike.data.source.remote.PlanetsMikeApi
import com.mikestf.planetsappmike.domain.repositories.UserRepository
import com.mikestf.planetsappmike.utils.BASE_URL_PLANETS
import com.mikestf.planetsappmike.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePlanetsMikeApi(): PlanetsMikeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_PLANETS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlanetsMikeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDataBase(app: Application): UserDataBase {
        return Room.databaseBuilder(
            app,
            UserDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        db: UserDataBase
    ): UserRepository {
        return UserRepositoryImpl(
            dao = db.userDao
        )
    }
}