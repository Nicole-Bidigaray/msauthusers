package com.fiap.techchallenger5.msauthusers.controllers;

import com.fiap.techchallenger5.msauthusers.domain.dto.ProdutoDtoResponse;
import com.fiap.techchallenger5.msauthusers.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDtoResponse>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDtoResponse> buscarProdutoPorId(@PathVariable int id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }
}
