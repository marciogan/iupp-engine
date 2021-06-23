package br.com.iupp.learn.core.model

import java.math.BigDecimal

data class Produto (
    val id: String = "",
    val nome: String = "",
    val descricao: String= "",
    val preco: BigDecimal = BigDecimal.ZERO,
    val emailDono: String = ""
    )