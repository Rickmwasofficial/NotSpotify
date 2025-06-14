package com.example.musify.onboarding.di

import android.app.Application
import com.example.musify.onboarding.data.LocalUserManagerImpl
import com.example.musify.onboarding.domain.AppEntryUseCases
import com.example.musify.onboarding.domain.LocalUserManager
import com.example.musify.onboarding.domain.ReadAppEntry
import com.example.musify.onboarding.domain.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }
}