package com.ds.safira.data

import android.content.Context
import com.ds.safira.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

object ReviewRepo {
    lateinit var reviews: List<Review>

    fun initRepo(context: Context) {
        val inputStream = context.resources.openRawResource(R.raw.reviews)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val gson = Gson()
        reviews = gson.fromJson(reader, Array<Review>::class.java).toList()
    }
    }