package com.withjetpack.bluetoothclassicconnection
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BluetoothClassicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}