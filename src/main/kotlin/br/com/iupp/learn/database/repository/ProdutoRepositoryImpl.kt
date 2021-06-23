package br.com.iupp.learn.database.repository

import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.database.entity.ProdutoEntity
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.ResultSet
import com.datastax.oss.driver.api.core.cql.Row
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoRepositoryImpl(private val cqlSession: CqlSession) : ProdutoRepository {

    override fun findById(id: UUID): ProdutoEntity? {
        val queryResult: ResultSet = cqlSession.execute(
            SimpleStatement
                .newInstance("SELECT * FROM mykeyspace.produto WHERE id = ?", id)
        )

        val retorno: MutableList<ProdutoEntity> = ArrayList<ProdutoEntity>()
        for (i: Row in queryResult) {
            val nome: String = i.getString("nome") ?: ""
            val emailDono: String = i.getString("emailDono") ?: ""
            val descricao: String = i.getString("descricao") ?: ""
            val preco: BigDecimal = i.getBigDecimal("preco") ?: BigDecimal.ZERO
            retorno.add(ProdutoEntity(nome = nome, descricao = descricao, preco = preco, emailDono = emailDono))
        }
        return if (retorno.isEmpty()) {
            null
        } else {
            retorno.get(0)
        }
    }

    override fun findAll(): ArrayList<Produto> {
        val queryResult: ResultSet = cqlSession.execute(
            SimpleStatement
                .newInstance("SELECT * FROM mykeyspace.produto")
        )

        val retorno: ArrayList<Produto> = ArrayList<Produto>()
        for (i: Row in queryResult) {
            val nome: String = i.getString("nome") ?: ""
            val emailDono: String = i.getString("emailDono") ?: ""
            val descricao: String = i.getString("descricao") ?: ""
            val preco: BigDecimal = i.getBigDecimal("preco") ?: BigDecimal.ZERO
            retorno.add(Produto(nome = nome, descricao = descricao, preco = preco, emailDono = emailDono))
        }
        return retorno
    }
}