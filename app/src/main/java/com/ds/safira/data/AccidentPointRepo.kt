package com.ds.safira.data

import android.content.Context
import com.ds.safira.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

object AccidentPointRepo {

    lateinit var accidentPoints: List<AccidentPoint>

    fun initRepo(context: Context) {
        val inputStream = context.resources.openRawResource(R.raw.accident_points)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val gson = Gson()
        accidentPoints = gson.fromJson(reader, Array<AccidentPoint>::class.java).toList()
    }
}