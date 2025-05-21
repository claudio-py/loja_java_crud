package com.loja.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "fisica")
@PrimaryKeyJoinColumn(name = "pessoa_id") // Links to the primary key in `pessoa`
public class PessoaFisica extends Pessoa {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String cpf;

    public PessoaFisica() {}

    public PessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public PessoaFisica(int id, String nome, String cpf) {
        super(id, nome);
        this.cpf = cpf;
    }

    // Getters and Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
