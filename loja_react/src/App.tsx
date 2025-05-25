import NovoProduto from './NovoProduto.tsx';
import ProdutoListView from './view/ProdutoListView.tsx';
import {Routes, Route} from 'react-router-dom'; 

function App() {

  return (
    <Routes>
      <Route index element = {<ProdutoListView />} />
      <Route path = '/produtos' element={<ProdutoListView />} />
      <Route path = '/novo-produto' element={<NovoProduto />} />
    </Routes>
  )
}

export default App
