package com.withjetpack.bluetoothclassicconnection.presentation.detail

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.withjetpack.bluetoothclassicconnection.repository.BluetoothRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) : ViewModel() {
    val bluetoothDevices: StateFlow<List<BluetoothDevice>> =
        bluetoothRepository.getBluetoothDevicesFlow()

    // Add a function to start scanning
    fun startScanning() {
        viewModelScope.launch {
            bluetoothRepository.startScanning()
        }
    }
}



