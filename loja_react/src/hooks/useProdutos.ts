// hooks/useProdutos.ts
import { useEffect, useState } from "react";
import Produto from "../modelo/Produto";
import ProdutoRepository from "../repository/ProdutoRepository";

export default function useProdutos() {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    ProdutoRepository.getAll()
      .then(setProdutos)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  const refreshProdutos = () => {
    setLoading(true);
    ProdutoRepository.getAll()
      .then(setProdutos)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  };

  return { produtos, loading, error, refreshProdutos };
}
