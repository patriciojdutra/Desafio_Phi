package com.dutra.patricio.desafiophi.data.service

import com.dutra.patricio.desafiophi.data.remote.Endpoint
import com.dutra.patricio.desafiophi.util.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class Retrofit {

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URLSERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val endpoint: Endpoint = getRetrofitInstance().create(Endpoint::class.java)
}