interface Produto {
  id?: number | null; // Optional because it's not needed for POST requests
  estoque: number;
  nome: string;
  preco_venda: number; // Changed from GLfloat to number for compatibility
  custo: number; // Changed from GLfloat to number for compatibility
}

export default Produto;
