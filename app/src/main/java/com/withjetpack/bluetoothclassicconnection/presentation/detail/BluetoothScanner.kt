package com.withjetpack.bluetoothclassicconnection.presentation.detail
import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BluetoothScanner(private val context: Context) {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var deviceDiscoveryReceiver: BroadcastReceiver ?= null

    fun startScan(): Flow<List<String>> {
        val deviceNames = mutableListOf<String>()
         deviceDiscoveryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (BluetoothDevice.ACTION_FOUND == intent?.action) {
                    val device =
                        intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    if (context?.let {
                            ActivityCompat.checkSelfPermission(
                                it,
                                Manifest.permission.BLUETOOTH_CONNECT
                            )
                        } != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    device?.name?.let {
                        deviceNames.add(it)
                    }
                }
            }
        }
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(deviceDiscoveryReceiver, filter)
        bluetoothAdapter?.startDiscovery()

        // Create a MutableStateFlow and emit the deviceNames as they are discovered
        val deviceNamesFlow = MutableStateFlow<List<String>>(deviceNames)

        return deviceNamesFlow
    }

    @SuppressLint("MissingPermission")
    fun stopScan() {
        context.unregisterReceiver(deviceDiscoveryReceiver)
        bluetoothAdapter?.cancelDiscovery()
    }
}
