package br.com.iupp.learn.database.entity

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal

@Introspected
data class ProdutoEntity(
    val id: String = "",
    val nome: String,
    val descricao: String,
    val preco: BigDecimal = BigDecimal.ZERO,
    val emailDono: String
)
