import React from 'react'
import './Footer.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTwitter, faFacebook, faYoutube } from '@fortawesome/free-brands-svg-icons';



const Footer = () => {
  return (
    <div className='Footer bg-secundary txt-white'>
      <div className='footer-left'>
        <p>Centro de Ayuda</p>
        <p>Preguntas frecuentes</p>
        <p>Información importante</p>
        <p>Guias</p>
        <p> 2024 Airbnb, Inc.</p>
      </div>

      <div className="footer-right">
        <p>Actualizaciones</p>
        <p>Estado del servidor</p>
        <div className='icons'>
          <a href="#" className="social-icon txt-white"><FontAwesomeIcon icon={faTwitter} /></a>
          <a href="#" className="social-icon txt-white"><FontAwesomeIcon icon={faFacebook} /></a>
          <a href="#" className="social-icon txt-white"><FontAwesomeIcon icon={faYoutube} /></a>
        </div>
      </div>
    </div>
  )
}

export default Footer
