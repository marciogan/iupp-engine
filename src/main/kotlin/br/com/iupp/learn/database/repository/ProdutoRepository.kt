package br.com.iupp.learn.database.repository

import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.database.entity.ProdutoEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface ProdutoRepository {
    fun findById(id: UUID): ProdutoEntity?
    fun findAll(): ArrayList<Produto>
}