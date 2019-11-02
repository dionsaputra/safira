package com.ds.safira

import android.app.Application
import com.ds.safira.data.AccidentPointRepo
import com.ds.safira.data.ReviewRepo

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AccidentPointRepo.initRepo(applicationContext)
        ReviewRepo.initRepo(applicationContext)
    }
}