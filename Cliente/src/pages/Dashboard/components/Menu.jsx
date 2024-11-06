import React, { useState } from "react";
import './Menu.css';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBuilding, faClipboardList, faUsers, faBlog, faTools } from '@fortawesome/free-solid-svg-icons';

function Menu() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    return (
        <nav className={`nav-menu ${isOpen ? 'open' : ''}`}>
            <button className="toggle-btn" onClick={toggleMenu}>
                {isOpen ? 'Cerrar' : 'Abrir'}
            </button>
            <ul>
                <li>
                    <Link to="/ruta-1" className="logo">
                        <span className="nav-item"> Admin Info</span>
                    </Link>
                </li>
                <li><Link to="/crud-property">
                    <FontAwesomeIcon icon={faBuilding} />
                    <span className="nav-item"> Propiedades</span>
                </Link>
                </li>
                <li>
                    <Link to="/crud-reservas">
                        <FontAwesomeIcon icon={faClipboardList} />
                        <span className="nav-item"> Reservas</span>
                    </Link>
                </li>
                <li>
                    <Link to="/crud-usuarios">
                        <FontAwesomeIcon icon={faUsers} />
                        <span className="nav-item"> Usuarios</span>
                    </Link>
                </li>
                <li>
                    <Link to="/blog">
                        <FontAwesomeIcon icon={faBlog} />
                        <span className="nav-item"> Blog</span>
                    </Link>
                </li>
                <li>
                    <Link to="/servicios">
                        <FontAwesomeIcon icon={faTools} />
                        <span className="nav-item"> Servicios</span>
                    </Link>
                </li>
            </ul>
        </nav>
    );
}

export default Menu;
