package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.creditCard.CreditCardValidator
import com.semirsuljevic.foundation.internal.creditCard.CreditCardValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CreditCardModule {
    @Binds
    @Singleton
    abstract fun bindCreditCardValidator(impl: CreditCardValidatorImpl): CreditCardValidator
}