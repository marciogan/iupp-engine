package br.com.iupp.learn.core.service

import br.com.iupp.learn.core.mapper.ProdutoConverter
import br.com.iupp.learn.core.port.ProdutoRepositoryPort
import br.com.iupp.learn.core.port.ProdutoServicePort
import br.com.iupp.learn.entrypoint.dto.ProdutoDto
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoService(private val produtoRepositoryPort: ProdutoRepositoryPort): ProdutoServicePort {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    override fun findById(id: UUID) : ProdutoDto {
        LOGGER.info("\n Produto solicitado pelo id: $id")

        val produto = produtoRepositoryPort.findById(id)

        LOGGER.info("\n Retorno do CORE/ProdutoService => $produto")
        return ProdutoConverter.produtoToProdutoDto(produto)

    }

    override fun findAll(): List<ProdutoDto> {
        LOGGER.info("\n Lista solicitada de todos produtos")

        val listaProdutoRetornada = produtoRepositoryPort.findAll()

        val listaProdutoDto: MutableList<ProdutoDto> = ArrayList<ProdutoDto>()
        for (i in listaProdutoRetornada) {
            val nome: String = i.nome
            val emailDono: String = i.emailDono
            val descricao: String = i.descricao
            val preco: BigDecimal = i.preco
            listaProdutoDto.add(ProdutoDto(nome = nome, descricao = descricao, preco = preco, emailDono = emailDono))
        }
        LOGGER.info("\n Retorno do CORE/ProdutoService => $listaProdutoDto")
        return listaProdutoDto
    }
}