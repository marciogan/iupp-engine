package br.com.iupp.learn.core.port

import br.com.iupp.learn.core.model.Produto
import java.util.*
import javax.inject.Singleton

@Singleton
interface  ProdutoRepositoryPort {
    fun findById(id: UUID): Produto
    fun findAll(): List<Produto>
}