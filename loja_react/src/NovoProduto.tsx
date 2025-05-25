import React, { useState } from "react";
import Produto from "./modelo/Produto";
import ProdutoRepository from "./repository/ProdutoRepository";

export default function NovoProduto() {
  const [nome, setNome] = useState<string>("");
  const [estoque, setEstoque] = useState<number>(0);
  const [precoVenda, setPrecoVenda] = useState<number>(0);
  const [custo, setCusto] = useState<number>(0);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const incluir = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setSuccess(null);

    const novoProduto: Produto = {
      nome,
      estoque,
      preco_venda: precoVenda,
      custo,
    };

    try {
      await ProdutoRepository.create(novoProduto);
      setSuccess("Produto incluído com sucesso!");
      setNome("");
      setEstoque(0);
      setPrecoVenda(0);
      setCusto(0);
    } catch (err) {
      setError("Erro ao incluir o produto. Tente novamente.");
      console.error(err);
    }
  };

  return (
    <main className="p-3">
      <h2>Incluir Produto</h2>
      <form role="form" onSubmit={incluir}>
        <div className="form-group">
          <label>Nome:</label>
          <input
            className="form-control"
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Estoque:</label>
          <input
            className="form-control"
            type="number"
            value={estoque}
            onChange={(e) => setEstoque(Number(e.target.value))}
            required
          />
        </div>
        <div className="form-group">
          <label>Preço de Venda:</label>
          <input
            className="form-control"
            type="number"
            step="0.01"
            value={precoVenda}
            onChange={(e) => setPrecoVenda(Number(e.target.value))}
            required
          />
        </div>
        <div className="form-group">
          <label>Valor de Custo:</label>
          <input
            className="form-control"
            type="number"
            step="0.01"
            value={custo}
            onChange={(e) => setCusto(Number(e.target.value))}
            required
          />
        </div>
        <button className="mt-3 btn btn-primary" type="submit">
          Incluir
        </button>
      </form>
      {success && <div className="alert alert-success mt-3">{success}</div>}
      {error && <div className="alert alert-danger mt-3">{error}</div>}
    </main>
  );
}


