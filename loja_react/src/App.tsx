import LivroLista from './LivroLista.tsx';
import LivroDados from './LivroDados.tsx';
import {Routes, Route} from 'react-router-dom'; 

function App() {

  return (
    <Routes>
      <Route index element = {<LivroLista />} />
      <Route path = '/catalogo' element={<LivroLista />} />
      <Route path = '/novo' element={<LivroDados />} />
    </Routes>
  )
}

export default App
