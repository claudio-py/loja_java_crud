import React, { useState, useEffect } from 'react';
import ControleLivros from './controle/ControleLivros';
import ControleEditoras from './controle/ControleEditora';
import Livro from './modelo/Livro';

const controleLivro = new ControleLivros();
const controleEditora = new ControleEditoras();


interface LinhaLivroProps {
    livro: Livro;
    excluir: (codigo: number) => void;
}

const LinhaLivro: React.FC<LinhaLivroProps> = ({ livro, excluir }) => {
    const nomeEditora = controleEditora.getNomeEditora(livro.codEditora);

    return (
        <tr>
            <td>
                <div>
                  {livro.titulo}
                </div>
                <button onClick={() => excluir(livro.codigo)}>Excluir</button>
            </td>
            <td>
                {livro.resumo}
            </td>
      <td>{nomeEditora}</td>
            <td>
                <ul>
                    {livro.autores.map((autor, index) => (
                        <li key={index}>{autor}</li>
                    ))}
                </ul>
            </td>
        </tr>
    );
};

const LivroLista: React.FC = () => {
    const [livros, setLivros] = useState<Livro[]>([]);
    const [carregado, setCarregado] = useState(false);

    useEffect(() => {
        const fetchLivros = () => {
            const dados = controleLivro.obterLivros();
            setLivros(dados);
            setCarregado(true);
        };
        fetchLivros();
    }, [carregado]);

    const excluir = (codigo: number) => {
        controleLivro.excluir(codigo);
        setCarregado(false);
    };

    return (
        <main className='p-3'>
            <h1>Catálogo de Livros</h1>
            <table className='table table-striped table-bordered '>
                <thead className='bg-body-secondary'>
                    <tr>
            <th>Título</th>
                        <th>Resumo</th>
            <th>Editora</th>
                        <th>Autores</th>
                    </tr>
                </thead>
                <tbody>
                    {livros.map((livro) => (
                        <LinhaLivro key={livro.codigo} livro={livro} excluir={excluir} />
                    ))}
                </tbody>
            </table>
        </main>
    );
};

export default LivroLista;
