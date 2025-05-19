package com.loja.service;

import com.loja.model.entity.PessoaFisica;
import com.loja.model.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository repository;

    public List<PessoaFisica> getAll() {
        return repository.findAll();
    }

    public PessoaFisica save(PessoaFisica pessoaFisica) {
        return repository.save(pessoaFisica);
    }
}
