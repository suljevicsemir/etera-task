package com.semirsuljevic.ui.internal.di

import com.semirsuljevic.ui.api.navigation.AppNavigator
import com.semirsuljevic.ui.internal.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {
    @Binds
    @Singleton
    abstract fun bindNavigator(
        navigatorImpl: NavigatorImpl
    ): AppNavigator
}
