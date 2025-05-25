import { Link } from 'react-router-dom';
export default function Nav() {
  return (
  <nav className='navbar navbar-expanded-lg bg-body-secondary'>
    <div className="container-fluid">
      <ul className="navbar-nav flex-row gap-5">
        <li className="nav-item ">
          <Link to="/Produtos" className="nav-link">|Listar Produtos|</Link>
        </li>
        <li className="nav-item">
          <Link to="/novo-produto" className="nav-link">|Novo| </Link>
        </li> 
      </ul>
    </div>
    </nav>
)
}
