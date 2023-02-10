package com.jnicomedes.myapplication.data.domain.mapper

import com.jnicomedes.myapplication.data.domain.model.Welcome
import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote

fun WelcomeRemote.toDomain(): Welcome = Welcome()
