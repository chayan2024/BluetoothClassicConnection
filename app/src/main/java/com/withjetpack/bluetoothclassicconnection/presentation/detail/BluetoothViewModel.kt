package com.withjetpack.bluetoothclassicconnection.presentation.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val application: Application // Inject the Application
) : ViewModel() {

    private val bluetoothScanner = BluetoothScanner(application)

    val deviceNames: Flow<List<String>> = bluetoothScanner.startScan()

    fun startScan() {
        bluetoothScanner.startScan()
    }

    fun stopScan() {
        bluetoothScanner.stopScan()
    }
}

