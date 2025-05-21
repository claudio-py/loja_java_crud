//a) Importar a classe Editora
import Editora from '../modelo/Editora.ts';
//b) Definir a variável editoras, como Array<Editora>, contendo ao menos três elementos, no formato JSON
const editoras: Array<Editora> = [
    {codEditora: 1, nome: 'Editora A'},
    {codEditora: 2, nome: 'Editora B'},
    {codEditora: 3, nome: 'Editora C'}
];
//c) Criar a classe ControleEditora, contendo os métodos getNomeEditora e getEditoras
class ControleEditora {
  //d) Implementar o método getEditoras, com o retorno do vetor editoras
    getEditoras(): Array<Editora> {
        return editoras;
    }
//e) Implementar o método getNomeEditora, recebendo codEditora, do tipo numérico, e retornando o nome da editora, através de uma operação filter sobre o vetor editoras
    getNomeEditora(codEditora: number): string {
        return editoras.filter(editora => editora.codEditora === codEditora)[0].nome;
    }
}
export default ControleEditora;
