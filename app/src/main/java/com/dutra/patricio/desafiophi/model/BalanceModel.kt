package com.dutra.patricio.desafiophi.model

class BalanceModel : ResponseRequest{

    var amount: Double = 0.0

    constructor(amount: Double){
        this.amount = amount
    }

    constructor(obj: ResponseRequest){
        reason = obj.reason
        erro = obj.erro
    }
}


