package com.fiap.techchallenger5.msauthusers.services;

import com.fiap.techchallenger5.msauthusers.domain.consumer.ProdutoConsumerFeign;
import com.fiap.techchallenger5.msauthusers.domain.dto.ProdutoDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoConsumerFeign produtoConsumerFeign;

    public List<ProdutoDtoResponse> listarProdutos() {
        return produtoConsumerFeign.listarProdutos();
    }

    public ProdutoDtoResponse buscarProdutoPorId(int id) {
        return produtoConsumerFeign.buscarProdutoPorId(id);
    }
}
