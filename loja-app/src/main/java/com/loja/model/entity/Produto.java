package com.loja.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
	@Column(name = "id")
	private int id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "estoque", nullable = false)
	private int estoque;

	@Column(name = "preco_venda", nullable = false)
	private float preco_venda;

	@Column(name = "custo", nullable = false)
	private float custo;

	public Produto() {
	}
	/* public Produto(int id) {
		this.id = id;
	} */
	public Produto(String nome, int estoque, float preco_venda, float custo) {
		this.nome = nome;
		this.estoque = estoque;
		this.preco_venda = preco_venda;
		this.custo = custo;
	}

	public Produto(int id, String nome, int estoque, float preco_venda, float custo) {
		this.id = id;
		this.nome = nome;
		this.estoque = estoque;
		this.preco_venda = preco_venda;
		this.custo = custo;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public float getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(float preco_venda) {
		this.preco_venda = preco_venda;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}
}
