package com.withjetpack.bluetoothclassicconnection.di

import android.content.Context
import com.withjetpack.bluetoothclassicconnection.repository.BluetoothRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BluetoothModule {

    @Provides
    @Singleton
    fun provideBluetoothRepository(@ApplicationContext context: Context): BluetoothRepository {
        return BluetoothRepository(context)
    }
}
