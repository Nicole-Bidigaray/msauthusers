package com.fiap.techchallenger5.msauthusers.domain.dto;

public record ProdutoDtoResponse(
        int id,
        String nome,
        String descricao,
        int quantidadeEstoque,
        double preco
) {
}
