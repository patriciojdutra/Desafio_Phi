package com.dutra.patricio.desafiophi.model

class ExtractDetailModel: ExtractModel{

    var authentication: String = ""

    constructor()

    constructor(authentication: String){
        this.authentication = authentication
    }

    constructor(obj: ResponseRequest){
        reason = obj.reason
        erro = obj.erro
    }

}











