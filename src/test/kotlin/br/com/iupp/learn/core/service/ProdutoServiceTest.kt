package br.com.iupp.learn.core.service

import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.core.port.ProdutoRepositoryPort
import br.com.iupp.learn.entrypoint.dto.ProdutoDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

@MicronautTest
class ProdutoServiceTest : AnnotationSpec(){

    val service = mockk<ProdutoRepositoryPort>()
    val produtoService = ProdutoService(service)

    lateinit var produtoDto: ProdutoDto
    lateinit var produto: Produto

    @BeforeEach
    fun setUp(){
        produtoDto = ProdutoDto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com")
        produto = Produto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com")
    }

    @Test
    fun `deve carregar um produto`(){
        every{service.findById(any()) } answers {produto}
        val result = produtoService.findById(UUID.randomUUID())

        result shouldBe produtoDto
    }

    @Test
    fun `deve listar todos produtos`(){
        val retorno: MutableList<Produto> = ArrayList<Produto>()
        retorno.add(Produto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))
        retorno.add(Produto("","produto2", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))

        val listProdutoDto: MutableList<ProdutoDto> = ArrayList<ProdutoDto>()
        listProdutoDto.add(ProdutoDto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))
        listProdutoDto.add(ProdutoDto("","produto2", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))

        every{service.findAll() } answers {retorno}
        val result = produtoService.findAll()

        result shouldBe listProdutoDto
    }

}
