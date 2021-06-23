package br.com.iupp.learn.core.mapper

import br.com.iupp.learn.core.model.Produto
import br.com.iupp.learn.database.entity.ProdutoEntity
import br.com.iupp.learn.entrypoint.dto.ProdutoDto

class ProdutoConverter {
    companion object{

        fun produtoEntityToProduto(produtoEntity: ProdutoEntity) =
            Produto(produtoEntity.id, produtoEntity.nome, produtoEntity.descricao, produtoEntity.preco, produtoEntity.emailDono)

        fun produtoToProdutoDto(produto: Produto) =
            ProdutoDto(produto.id, produto.nome,produto.descricao,produto.preco,produto.emailDono)
    }
}