//a) Importar a classe Livro
import Livro from "../modelo/Livro.ts";
//b) Definir a variável livros, como Array<Livro>, contendo ao menos três elementos, no formato JSON
const livros: Array<Livro> = [
    { codigo: 1, codEditora: 1, titulo: "Use a cabeça Java", resumo: "Resumo 1", autores: ["Autor 1", "Autor 2"] },
    { codigo: 2, codEditora: 2, titulo: "Java, como programar", resumo: "Resumo 2", autores: ["Autor 3", "Autor 4"] },
    { codigo: 3, codEditora: 3, titulo: "aprenda a vender cursos", resumo: "Resumo 3", autores: ["Autor 5", "Autor 6"] }
];
//c) Criar a classe ControleLivro, contendo os métodos obterLivros, incluir e excluir
class ControleLivros {
//d) Implementar o método obterLivros, com o retorno do vetor livros
    obterLivros():Livro[] {
        return livros;
    }
//e) Implementar o método incluir, recebendo um objeto do tipo Livro, que terá o código trocado pelo código mais alto do vetor, incrementado de um, e depois será adicionado ao vetor
    incluir(livro: Livro) {
        livro.codigo = livros[livros.length - 1].codigo + 1;
        livros.push(livro);
    }
//f) Implementar o método excluir, recebendo um código numérico, que irá encontrar o índice do livro com o código fornecido, através de findIndex, e removê-lo com o uso de splice
    excluir(codigo: number) {
        const index = livros.findIndex(livro => livro.codigo === codigo);
        livros.splice(index, 1);
    }
}
export default ControleLivros;
