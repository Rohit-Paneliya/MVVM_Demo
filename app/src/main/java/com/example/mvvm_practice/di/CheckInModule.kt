package com.example.mvvm_practice.di

import com.example.mvvm_practice.network.NetworkApiInterface
import com.example.mvvm_practice.repositories.CheckInRepositoryImpl
import com.example.mvvm_practice.repositories.ICheckInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class CheckInModule {

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): NetworkApiInterface {
        return retrofit.create(NetworkApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideICheckInInterface(networkApiInterface: NetworkApiInterface): ICheckInRepository {
        return CheckInRepositoryImpl(networkApiInterface)
    }
}