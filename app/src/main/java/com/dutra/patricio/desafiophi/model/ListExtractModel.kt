package com.dutra.patricio.desafiophi.model

class ListExtractModel : ResponseRequest{

    val items = ArrayList<ExtractModel>()

    constructor()

    constructor(obj: ResponseRequest){
        reason = obj.reason
        erro = obj.erro
    }
}





