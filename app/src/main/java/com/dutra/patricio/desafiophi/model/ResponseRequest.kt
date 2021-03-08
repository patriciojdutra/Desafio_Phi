package com.dutra.patricio.desafiophi.model

open class ResponseRequest{

    var reason: String = ""
    var erro: Boolean = false

    constructor()

    constructor(reason: String, erro: Boolean) {
        this.reason = reason
        this.erro = erro
    }


}


