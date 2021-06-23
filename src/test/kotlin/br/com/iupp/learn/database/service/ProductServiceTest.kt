package br.com.iupp.learn.database.service

import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.database.entity.ProdutoEntity
import br.com.iupp.learn.database.repository.ProdutoRepository
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

@MicronautTest
class ProductServiceTest : AnnotationSpec(){

    val service = mockk<ProdutoRepository>()
    val productService = ProductService(service)

    lateinit var produto: Produto
    lateinit var produtoEntity: ProdutoEntity

    companion object{
        val id = UUID.randomUUID()
    }

    @BeforeEach
    fun setUp(){
        produto = Produto(id.toString(),"produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com")
        produtoEntity = ProdutoEntity(id.toString(),"produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com")
    }

    @Test
    fun `deve carregar um produto`(){
        every{service.findById(id) } answers {produtoEntity}
        val result = productService.findById(id)
        result shouldBe produto
    }

    @Test
    fun `deve listar todos produtos`(){

        val retorno: ArrayList<Produto> = ArrayList()
        retorno.add(Produto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))
        retorno.add(Produto("","produto2", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))

        val listProduto: MutableList<Produto> = ArrayList<Produto>()
        listProduto.add(Produto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))
        listProduto.add(Produto("","produto2", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))

        every{service.findAll() } answers {retorno}
        val result = productService.findAll()

        result shouldBe listProduto
    }
}