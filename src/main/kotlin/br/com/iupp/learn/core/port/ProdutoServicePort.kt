package br.com.iupp.learn.core.port

import br.com.iupp.learn.entrypoint.dto.ProdutoDto
import java.util.*
import javax.inject.Singleton

@Singleton
interface ProdutoServicePort {
    fun findById(id: UUID): ProdutoDto
    fun findAll(): List<ProdutoDto>
}