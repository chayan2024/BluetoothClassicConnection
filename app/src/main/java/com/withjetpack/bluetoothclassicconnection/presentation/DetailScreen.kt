package com.withjetpack.bluetoothclassicconnection.presentation
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.withjetpack.bluetoothclassicconnection.common.ToolBar
import com.withjetpack.bluetoothclassicconnection.presentation.detail.BluetoothDeviceList
import com.withjetpack.bluetoothclassicconnection.presentation.detail.BluetoothViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: BluetoothViewModel) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var switchState by remember {
            mutableStateOf(false)
        }
        ToolBar()

        val deviceNames by viewModel.deviceNames.collectAsState(emptyList())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = { viewModel.startScan() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Start Scan")
            }

            Button(
                onClick = { viewModel.stopScan() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Stop Scan")
            }

            BluetoothDeviceList(deviceNames)
        }    }
}
