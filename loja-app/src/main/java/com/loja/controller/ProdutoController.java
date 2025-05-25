package com.loja.controller;

import com.loja.model.entity.Produto;
import com.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService service;

  @GetMapping
  public List<Produto> getAll() {
    return service.getAll();
  }
  
  @GetMapping("/getby")
  public ResponseEntity<Produto> getProdutoById(@RequestParam(name = "id") int id) {
    Optional<Produto> produto = service.getById(id);
    return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
    Produto savedProduto = service.save(produto);
    return ResponseEntity.ok(savedProduto);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteProduto(@RequestParam(name = "id") int id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto) {
    Produto updatedProduto = service.save(produto);
    return ResponseEntity.ok(updatedProduto);
  }
}
