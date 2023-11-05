package com.withjetpack.bluetoothclassicconnection.presentation.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BluetoothDeviceList(deviceNames: List<String>) {
    LazyColumn {
        items(deviceNames) { deviceName ->
            Text(text = deviceName, modifier = Modifier.padding(16.dp))
        }
    }
}
