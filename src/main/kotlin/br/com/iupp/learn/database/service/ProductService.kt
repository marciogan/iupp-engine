package br.com.iupp.learn.database.service

import br.com.iupp.learn.core.mapper.ProdutoConverter
import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.core.port.ProdutoRepositoryPort
import br.com.iupp.learn.database.repository.ProdutoRepository
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*
import javax.inject.Singleton

@Singleton
class ProductService(private val produtoRepository: ProdutoRepository) : ProdutoRepositoryPort {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    override fun findById(id: UUID): Produto {
        val produto = produtoRepository.findById(id)

        LOGGER.info("\n Retorno do DB/ProdutoService ===> $produto")
        return ProdutoConverter.produtoEntityToProduto(produto!!)
    }

    override fun findAll(): List<Produto> {

        val queryResult = produtoRepository.findAll()

        val retorno: MutableList<Produto> = ArrayList<Produto>()
        for (i in queryResult) {
            val nome: String = i.nome
            val emailDono: String = i.emailDono
            val descricao: String = i.descricao
            val preco: BigDecimal = i.preco
            retorno.add(Produto(nome = nome, descricao = descricao, preco = preco, emailDono = emailDono))
        }

        LOGGER.info("\n Retorno do DB/ProdutoService ===> $retorno")
        return retorno
    }
}
