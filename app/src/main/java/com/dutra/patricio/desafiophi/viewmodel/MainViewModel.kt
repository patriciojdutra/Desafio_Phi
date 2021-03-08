package com.dutra.patricio.desafiophi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dutra.patricio.desafiophi.data.repository.webApi
import com.dutra.patricio.desafiophi.model.*
import com.dutra.patricio.desafiophi.util.ResponseUtil

class MainViewModel: ViewModel() {

    val limit = 10
    var offset = 0

    val balance = MutableLiveData<Double>()
    val erro = MutableLiveData<String>()
    val loadinng = MutableLiveData<Boolean>()
    val listExtrac = ArrayList<ExtractModel>()
    val extractDetail = MutableLiveData<ExtractDetailModel>()
    val listExtractLiveData = MutableLiveData<ArrayList<ExtractModel>>()

    fun getMyBalance(){
        webApi.getMyBalance(object: ResponseUtil<BalanceModel>{
            override fun sucesso(obj: BalanceModel) {
                if (!obj.erro) {
                    balance.value = obj.amount
                }else{
                    erro.value = obj.reason
                }
            }
        })
    }

    fun getMoreExtract(){

        loadinng.value = true

        webApi.getExtract(limit, offset, object: ResponseUtil<ListExtractModel>{
            override fun sucesso(obj: ListExtractModel) {

                if(obj.erro){
                    erro.value = obj.reason
                    loadinng.value = false
                }else {
                    offset++
                    loadinng.value = false
                    listExtrac.addAll(obj.items)
                    listExtractLiveData.value = listExtrac
                }
            }
        })
    }

    fun getExtractDetail(id : String){

        loadinng.value = true

        webApi.getExtractDetail(id, object: ResponseUtil<ExtractDetailModel>{
            override fun sucesso(obj: ExtractDetailModel) {

                loadinng.value = false

                if (obj.erro) {
                    erro.value = obj.reason
                }else{
                    extractDetail.value = obj
                }
            }
        })
    }
}