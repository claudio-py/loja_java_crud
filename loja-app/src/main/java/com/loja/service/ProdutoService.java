package com.loja.service;

import com.loja.model.entity.Produto;
import com.loja.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository repository;

  public List<Produto> getAll() {
    return repository.findAll();
  }

  public Produto save(Produto produto) {
    return repository.save(produto);
  }

  public Produto update(Produto produto) {
    return repository.save(produto);
  }

  public void delete(int id) {
    repository.deleteById(id);
  }

  public Optional<Produto> getById(int id) {
    return repository.findById(id);
  }
}
