package br.com.iupp.learn.entrypoint.dto

import java.math.BigDecimal

data class ProdutoDto(
    val id: String = "",
    val nome: String,
    val descricao: String,
    val preco: BigDecimal,
    val emailDono: String
    )