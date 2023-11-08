package com.withjetpack.bluetoothclassicconnection.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.withjetpack.bluetoothclassicconnection.common.ToolBar
import com.withjetpack.bluetoothclassicconnection.presentation.detail.BluetoothViewModel

@Composable
fun DetailScreen(navController: NavHostController,viewModel: BluetoothViewModel = hiltViewModel()) {

    LaunchedEffect(true){
        viewModel.startScanning()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ToolBar()

        val deviceNames by viewModel.bluetoothDevices.collectAsState(emptyList())

        LazyColumn {
            items(deviceNames) { deviceName ->
                if (ActivityCompat.checkSelfPermission(
                        LocalContext.current,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                Text(text = deviceName.name, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
