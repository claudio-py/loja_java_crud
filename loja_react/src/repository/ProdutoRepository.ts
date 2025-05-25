import Produto from "../model/Produto";

// const API_URL = "http://localhost:8081/api/produtos"; // Replace with your backend URL

// Use environment variable defined in `.env` or `.env.production`
const API_URL = import.meta.env.VITE_API_URL;

class ProdutoRepository {
  async getAll(): Promise<Produto[]> {
    const response = await fetch(API_URL);
    if (!response.ok) {
      throw new Error("Failed to fetch produtos");
    }
    return response.json();
  }

  async create(produto: Produto): Promise<Produto> {
    const { id, ...produtoData } = produto; // Exclude `id` from the request body
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produtoData),
    });
    if (!response.ok) {
      throw new Error("Failed to create produto");
    }
    return response.json();
  }

  async update(produto: Produto): Promise<Produto> {
    if (!produto.id) {
      throw new Error("Produto ID is required for update");
    }
    const response = await fetch(API_URL, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto), // Send the entire Produto object in the request body
    });
    if (!response.ok) {
      throw new Error("Failed to update produto");
    }
    return response.json();
  }

  async delete(id: number): Promise<void> {
    const response = await fetch(`${API_URL}?id=${id}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete produto");
    }
  }
}

export default new ProdutoRepository();
