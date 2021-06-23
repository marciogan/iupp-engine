package br.com.iupp.learn.entrypoint.controller

import br.com.iupp.learn.core.port.ProdutoServicePort
import br.com.iupp.learn.entrypoint.dto.ProdutoDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

@MicronautTest
class ProdutoControllerTest : AnnotationSpec(){

    val service = mockk<ProdutoServicePort>()
    val produtoController = ProdutoController(service)

    companion object{
        val id = UUID.randomUUID()
        val produtoDto = ProdutoDto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com")

    }

    @Test
    fun `deve carregar um produto`(){
        every{service.findById(id)} answers { produtoDto }
        val result = produtoController.findById(id)

        result.body() shouldBe produtoDto
    }

    @Test
    fun `deve carregar todos produtos`(){
        val listProdutoDto: MutableList<ProdutoDto> = ArrayList<ProdutoDto>()
        listProdutoDto.add(ProdutoDto("","produto1", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))
        listProdutoDto.add(ProdutoDto("","produto2", "descricao", BigDecimal.ZERO, emailDono = "teste@teste.com"))

        every{service.findAll()} answers { listProdutoDto }
        val result = produtoController.findAll()

        result.body() shouldBe listProdutoDto
    }



}
