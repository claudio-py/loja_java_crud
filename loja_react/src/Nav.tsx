//e) definir tag nav, formatado pelo BootStrap, e utilizar elementos do tipo Link, no lugar das âncoras, para acesso às rotas definidas
import { Link } from 'react-router-dom';
export default function Nav() {
  return (
  <nav className='navbar navbar-expanded-lg bg-body-secondary'>
    <div className="container-fluid">
      <ul className="navbar-nav flex-row gap-5">
        <li className="nav-item">
          <Link to="/catalogo" className="nav-link">Catálogo</Link>
        </li>
        <li className="nav-item">
          <Link to="/novo" className="nav-link">Novo</Link>
        </li> 
      </ul>
    </div>
    </nav>
)
}
