package com.dutra.patricio.desafiophi.data.remote

import com.dutra.patricio.desafiophi.model.*
import com.dutra.patricio.desafiophi.util.ResponseUtil
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

      @GET("myBalance")
      fun getMyBalance(@Header("token") token: String) : Call<BalanceModel>

      @GET("myStatement/{limit}/{offset}")
      fun getExtract(@Header("token") token: String,
                     @Path("limit") limit: Int,
                     @Path("offset") offset: Int) : Call<ListExtractModel>

      @GET("myStatement/detail/{id}/")
      fun getExtractDetail(@Header("token") token: String, @Path("id") id: String) : Call<ExtractDetailModel>

}