import NovoProduto from './view/NovoProduto.tsx';
import ProdutoList from './view/ProdutoList.tsx';
import {Routes, Route} from 'react-router-dom'; 

function App() {

  return (
    <Routes>
      <Route index element = {<ProdutoList/>} />
      <Route path = '/produtos' element={<ProdutoList/>} />
      <Route path = '/novo-produto' element={<NovoProduto />} />
    </Routes>
  )
}

export default App
