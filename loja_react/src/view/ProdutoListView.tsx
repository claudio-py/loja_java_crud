import React, { useState, useEffect } from "react";
import useProdutos from "../hooks/useProdutos";
import ProdutoRepository from "../repository/ProdutoRepository";
import Produto from "../modelo/Produto";

interface ProdutoRowProps {
  produto: Produto;
  excluir: (id: number) => void;
  atualizar: (produto: Produto) => void;
}

const ProdutoRow: React.FC<ProdutoRowProps> = ({ produto, excluir, atualizar }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedProduto, setEditedProduto] = useState<Produto>(produto);

  const handleInputChange = (field: keyof Produto, value: string | number) => {
    setEditedProduto({ ...editedProduto, [field]: value });
  };

  const handleSubmit = () => {
    atualizar(editedProduto);
    setIsEditing(false);

  };

  return (
    <tr>
      <td>{produto.id}</td>
      {isEditing ? (
        <>
          <td>
            <input
              type="text"
              value={editedProduto.nome}
              onChange={(e) => handleInputChange("nome", e.target.value)}
            />
          </td>
          <td>
            <input
              type="number"
              value={editedProduto.estoque}
              onChange={(e) => handleInputChange("estoque", Number(e.target.value))}
            />
          </td>
          <td>
            <input
              type="number"
              step="0.01"
              value={editedProduto.preco_venda}
              onChange={(e) => handleInputChange("preco_venda", Number(e.target.value))}
            />
          </td>
          <td>
            <input
              type="number"
              step="0.01"
              value={editedProduto.custo}
              onChange={(e) => handleInputChange("custo", Number(e.target.value))}
            />
          </td>
        </>
      ) : (
        <>
          <td>{produto.nome}</td>
          <td>{produto.estoque}</td>
          <td>{produto.preco_venda}</td>
          <td>{produto.custo}</td>
        </>
      )}
      <td>
        {isEditing ? (
          <button onClick={handleSubmit}>Submit</button>
        ) : (
          <button onClick={() => setIsEditing(true)}>Alterar</button>
        )}
        <button onClick={() => excluir(produto.id!)}>Excluir</button>
      </td>
    </tr>
  );
};

const ProdutoListView: React.FC = () => {
  const { produtos, loading, error, refreshProdutos } = useProdutos();
  const [searchId, setSearchId] = useState<string>(""); // Input value
  const [filteredProdutos, setFilteredProdutos] = useState<Produto[]>(produtos); // Filtered products
  const [isExactSearch, setIsExactSearch] = useState(false); // Track if exact search is active

  const excluir = async (id: number) => {
    try {
      await ProdutoRepository.delete(id);
      refreshProdutos(); // Refresh the list after deletion
    } catch (err) {
      console.error("Failed to delete produto:", err);
    }
  };

  const atualizar = async (produto: Produto) => {
    try {
      await ProdutoRepository.update(produto);
      refreshProdutos(); // Refresh the list after update
    } catch (err) {
      console.error("Failed to update produto:", err);
    }
  };

  // Real-time filtering as the user types
  useEffect(() => {
    if (!isExactSearch) {
      if (searchId.trim() === "") {
        setFilteredProdutos(produtos); // Show all products if search is empty
      } else {
        const likelyMatches = produtos.filter((produto) =>
          produto.id?.toString().includes(searchId.trim())
        );
        setFilteredProdutos(likelyMatches);
      }
    }
  }, [searchId, produtos, isExactSearch]);

  // Exact match filtering when "Buscar" is clicked
  const buscar = () => {
    setIsExactSearch(true); // Activate exact search
    if (searchId.trim() === "") {
      setFilteredProdutos(produtos); // Show all products if search is empty
    } else {
      const exactMatch = produtos.filter((produto) => produto.id?.toString() === searchId.trim());
      setFilteredProdutos(exactMatch);
    }
  };

  // Reset exact search when the search term changes
  useEffect(() => {
    if (isExactSearch) {
      setIsExactSearch(false);
    }
  }, [searchId]);

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (error) {
    return <div>Erro: {error}</div>;
  }

  return (
    <main className="p-3">
      <h1>Lista de Produtos</h1>
      <div className="form-group mb-3 d-flex">
        <input
          type="text"
          className="form-control me-2"
          placeholder="Buscar por ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
        />
        <button className="btn btn-primary" onClick={buscar}>
          Buscar
        </button>
      </div>
      <table className="table table-striped table-bordered">
        <thead className="bg-body-secondary">
          <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Estoque</th>
            <th>Valor de Venda</th>
            <th>Valor de Custo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {filteredProdutos.map((produto) => (
            <ProdutoRow
              key={produto.id}
              produto={produto}
              excluir={excluir}
              atualizar={atualizar}
            />
          ))}
        </tbody>
      </table>
    </main>
  );
};

export default ProdutoListView;
