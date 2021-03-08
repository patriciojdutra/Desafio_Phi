package com.dutra.patricio.desafiophi.model

open class ExtractModel: ResponseRequest{

    var id: String = ""
    var amount: Double = 0.0
    var to: String = ""
    var from: String = ""
    var bankName: String = ""
    var description: String = ""
    var tType: String = ""
    var createdAt: String = ""

    constructor()

    constructor(id: String, amount: Double, to: String, from: String, bankName: String, description: String, tType: String, createdAt: String){
        this.id = id
        this.amount = amount
        this.to = to
        this.from = from
        this.bankName = bankName
        this.description = description
        this.tType = tType
        this.createdAt = createdAt
    }

    constructor(obj: ResponseRequest){
        reason = obj.reason
        erro = obj.erro
    }



}











