package com.dutra.patricio.desafiophi.data.repository

import com.dutra.patricio.desafiophi.data.service.Retrofit
import com.dutra.patricio.desafiophi.model.*
import com.dutra.patricio.desafiophi.util.Constants
import com.dutra.patricio.desafiophi.util.ResponseUtil
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class webApi {

    companion object {

        @JvmStatic
        fun getMyBalance( responseUtil : ResponseUtil<BalanceModel>) {

            val callback = Retrofit().endpoint.getMyBalance(Constants.TOKEN)

            callback.enqueue(object : Callback<BalanceModel> {

                override fun onResponse(call: Call<BalanceModel>, response: Response<BalanceModel>) {

                    if (response.isSuccessful && response.body() != null) {
                        responseUtil.sucesso(response.body()!!)
                    }else{
                        val result = BalanceModel(errorResultRequest(response.errorBody().toString()))
                        responseUtil.sucesso(result)
                    }

                }

                override fun onFailure(call: Call<BalanceModel>, t: Throwable) {
                    val result = BalanceModel(errorResultRequest(t.message.toString()))
                    responseUtil.sucesso(result)
                }
            })
        }

        @JvmStatic
        fun getExtract(limit: Int, offset: Int, responseUtil : ResponseUtil<ListExtractModel>) {

            val callback = Retrofit().endpoint.getExtract(Constants.TOKEN, limit, offset)

            callback.enqueue(object : Callback<ListExtractModel> {

                override fun onResponse(call: Call<ListExtractModel>, response: Response<ListExtractModel>) {

                    if (response.isSuccessful && response.body() != null) {
                        responseUtil.sucesso(response.body()!!)
                    }else{
                        val result = ListExtractModel(errorResultRequest(response.errorBody().toString()))
                        responseUtil.sucesso(result)
                    }
                }

                override fun onFailure(call: Call<ListExtractModel>, t: Throwable) {
                    val result = ListExtractModel(errorResultRequest(t.message.toString()))
                    responseUtil.sucesso(result)
                }
            })
        }


        @JvmStatic
        fun getExtractDetail(id : String, responseUtil : ResponseUtil<ExtractDetailModel>) {

            val callback = Retrofit().endpoint.getExtractDetail(Constants.TOKEN, id)

            callback.enqueue(object : Callback<ExtractDetailModel> {

                override fun onResponse(call: Call<ExtractDetailModel>, response: Response<ExtractDetailModel>) {

                    if (response.isSuccessful && response.body() != null) {
                        responseUtil.sucesso(response.body()!!)
                    }else{
                        val result = ExtractDetailModel(errorResultRequest(response.errorBody().toString()))
                        responseUtil.sucesso(result)
                    }
                }

                override fun onFailure(call: Call<ExtractDetailModel>, t: Throwable) {
                    val result = ExtractDetailModel(errorResultRequest(t.message.toString()))
                    responseUtil.sucesso(result)
                }
            })
        }

        fun errorResultRequest(erro: String) : ResponseRequest{
            return ResponseRequest(
                    if(!erro.isNullOrEmpty())
                        erro
                    else
                        "Ocorreu um erro ao tentar se conectar com o servidor.",
                    true)
        }

    }

}