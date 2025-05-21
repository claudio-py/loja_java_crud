interface Editora {
  codEditora: number;
  nome: string;
}
class Editora {
  constructor(codEditora: number, nome: string) {
    this.codEditora = codEditora;
    this.nome = nome;
  }
}
export default Editora;
