import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Livro from './modelo/Livro.ts';
import Editora from './modelo/Editora.ts';
import ControleEditora from './controle/ControleEditora.ts';
import ControleLivro from './controle/ControleLivros.ts';

//a) Definir o componente LivroDados , devendo ser exportado por padrão  Instanciar um controlador de livros, com o nome controleLivro, e um de editoras, com o nome controleEditora
export default function LivroDados() {
    const controleLivro = new ControleLivro();
    const controleEditora = new ControleEditora();
//b) Em LivroDados, definir o vetor opcoes, invocando o método getEditoras, com o mapeamento de codEditora para value e nome para text
    const opcoes = controleEditora.getEditoras().map((editora: Editora) => {
        return { value: editora.codEditora, text: editora.nome };
    });
//c) Definir as propriedades titulo, resumo e autores, todas de texto, através de useState, além de codEditora, iniciada com a posição zero de opcoes
    const [titulo, setTitulo] = useState('');
    const [resumo, setResumo] = useState('');
    const [autores, setAutores] = useState('');
    const [codEditora, setCodEditora] = useState(opcoes[0].value);
//d) Acrescentar o atributo navigate, recebendo o Hook useNavigate
  const navigate = useNavigate();


//e) Definir o método tratarCombo, tendo o evento como parâmetro, onde deve ocorrer a chamada para setCodEditora, com a passagem do value de uma combo de seleção (target do evento) convertido para number
    function tratarCombo(event: any) {
        setCodEditora(Number(event.target.value));
    }
//f) Definir o método incluir, com a recepção de um evento, que primeiro deve eliminar o comportamento padrão com preventDefault, em seguida instanciar um livro com código valendo zero, o valor das propriedades de estado, e autores separados por linha (split), invocar o método incluir do controlador de livros, e navegar para a página de listagem, na raiz
    function incluir(event: any) {
        event.preventDefault();
        const livro = new Livro(0, codEditora, titulo, resumo, autores.split('\n'));
        controleLivro.incluir(livro);
        navigate('/');
    }

    return (
//g) No retorno do componente deve ser fornecida a área principal (main), contendo o título da página e um formulário para inclusão do livro, sendo composto dos campos referentes às propriedades de estado, com ligação através de value e onChange
        <main className='p-3'>
            <h2>Inclusão de Livro</h2>
            <form role="form" onSubmit={incluir}>
                <div className="form-group">
                  <label>Título:</label>
                  <input className='form-control' type="text" value={titulo} onChange={(e) => setTitulo(e.target.value)} />
                </div>
                <div className="form-group">
                  <label>Resumo:</label>
                  <textarea className='form-control'value={resumo} onChange={(e) => setResumo(e.target.value)} />
                </div>
                <div className="form-group">
                  <label>Autores:</label>
                  <textarea className='form-control'value={autores} onChange={(e) => setAutores(e.target.value)} />
                </div>
{/* h) Utilizar uma lista de seleção (combo) para as editoras, com as opções geradas pelo método map, de opcoes, e associando onChange ao método tratarCombo */}
                <div className="form-group">
                  <label>Editora:</label>
                  <select className='form-select'value={codEditora} onChange={tratarCombo}>
                      {opcoes.map((opcao, i) => (
                          <option key={i} value={opcao.value}>{opcao.text}</option>
                      ))}
                  </select>
                </div>
{/* i) Relacionar o evento onSubmit, do formulário, ao método incluir, além de adicionar um botão de submissão ao final */}
                <button className='mt-3' type="submit">Incluir</button>
            </form>
        </main>
  );
}


