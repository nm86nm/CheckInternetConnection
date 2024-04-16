package com.mnp.observerconnectivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.mnp.observerconnectivity.ui.theme.ObserverConnectivityTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private lateinit var networkStatus: NetworkStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStatus = ObserverConnection(applicationContext)
        networkStatus.observe().onEach {
            println("Status is $it")
        }.launchIn(lifecycleScope)
        setContent {
             ObserverConnectivityTheme {
                 val status by networkStatus.observe().collectAsState(
                     initial = NetworkStatus.Status.Unavailable
                 )
                 Box(
                     modifier = Modifier.fillMaxSize(),
                     contentAlignment = Alignment.Center
                 ) {
                    Text(text = "Network status: $status")
                 }
            }
        }
    }
}