package com.semirsuljevic.internal.di

import com.semirsuljevic.api.validation.CreditCardHandler
import com.semirsuljevic.internal.validation.CreditCardHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ValidationModule {
    @Binds
    //small note about singleton, this will keep card info filled
    //if user goes back to onboarding and then to card screen again
    @Singleton
    abstract fun bindCardValidator(impl: CreditCardHandlerImpl): CreditCardHandler
}