package com.loja.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_pessoa") // Links to the primary key in `pessoa`
public class PessoaJuridica extends Pessoa {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String cnpj;

    public PessoaJuridica() {}

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    // Getters and Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
