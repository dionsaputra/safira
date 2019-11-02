package com.ds.safira

import android.app.Application
import com.ds.safira.data.AccidentPointRepo

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AccidentPointRepo.initRepo(applicationContext)
    }
}