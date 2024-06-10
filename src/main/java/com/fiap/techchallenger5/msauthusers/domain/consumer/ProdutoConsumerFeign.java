package com.fiap.techchallenger5.msauthusers.domain.consumer;

import com.fiap.techchallenger5.msauthusers.domain.dto.ProdutoDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "msprodutos", url = "${msprodutos.url}")
public interface ProdutoConsumerFeign {

    @GetMapping("/produtos")
    List<ProdutoDtoResponse> listarProdutos();

    @GetMapping("/produtos/{id}")
    ProdutoDtoResponse buscarProdutoPorId(@PathVariable("id") int id);
}
