package com.mnp.observerconnectivity

import kotlinx.coroutines.flow.Flow

interface NetworkStatus {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}