package br.com.iupp.learn.entrypoint.controller

import br.com.iupp.learn.core.port.ProdutoServicePort
import br.com.iupp.learn.entrypoint.dto.ProdutoDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import java.util.*

@Controller("/produtos")
class ProdutoController(private val service: ProdutoServicePort) {

    @Get("/{id}")
    fun findById(@QueryValue id: UUID): HttpResponse<ProdutoDto> {
        try {
            return HttpResponse.ok(service.findById(id))
        } catch (e: Exception) {
            throw IllegalArgumentException("Id não encontrado")
        }
    }

    @Get
    fun findAll(): HttpResponse<List<ProdutoDto>> {
        try {
            return HttpResponse.ok(service.findAll())
        } catch (e: Exception) {
            throw IllegalArgumentException("Nada encontrado")
        }
    }
}