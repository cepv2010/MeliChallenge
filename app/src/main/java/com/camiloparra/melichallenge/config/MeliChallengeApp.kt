package com.camiloparra.melichallenge.config

import android.app.Application
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Camilo Parra on 7/06/2022.
 */
@HiltAndroidApp
class MeliChallengeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
    }

}