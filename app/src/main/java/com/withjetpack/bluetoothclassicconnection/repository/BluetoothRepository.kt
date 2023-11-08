package com.withjetpack.bluetoothclassicconnection.repository
import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BluetoothRepository @Inject constructor(var application: Context) {

    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothDevicesFlow: MutableStateFlow<List<BluetoothDevice>> =
        MutableStateFlow(emptyList())
    private val scanningInProgressFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getBluetoothDevicesFlow(): StateFlow<List<BluetoothDevice>> = bluetoothDevicesFlow

    suspend fun startScanning() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
            // Handle Bluetooth not available or not enabled case
            return
        }

        // Check for runtime permissions here and request if needed

        scanningInProgressFlow.value = true

        if (application?.applicationContext?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.BLUETOOTH_SCAN
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        bluetoothAdapter.startDiscovery()
        val bluetoothReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        val device =
                            intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                        device?.let {
                            val updatedList = bluetoothDevicesFlow.value.toMutableList()
                            updatedList.add(device)
                            bluetoothDevicesFlow.value = updatedList
                        }
                    }
                }
            }
        }
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        application?.getApplicationContext()?.registerReceiver(bluetoothReceiver, filter)
    }
}
