package com.loja.service;

import com.loja.model.entity.PessoaJuridica;
import com.loja.model.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository repository;

    public List<PessoaJuridica> getAll() {
        return repository.findAll();
    }

    public PessoaJuridica save(PessoaJuridica pessoaJuridica) {
        return repository.save(pessoaJuridica);
    }
}
