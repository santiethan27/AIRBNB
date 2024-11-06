import React from 'react';
import '../Home/HomePage.css';
import Carrusel from '../Home/components/Carrusel';
import CardsVenta from '../Home/components/CardsVenta';

const PropertySale= ()=>{
    
    return (
        <div>
            <header className='bg-secundary'>
                <section className='txt-white'>
                    <h2>Encuentra el hogar de tus sueños</h2>
                    <h3>Busca ofertas en cabañas, casas y mucho más...</h3>
                    <div className='selector-option'>
                        <input type="text" />
                    </div>
                </section>
                <section>
                    <img src="/img/Headerventa.png" alt="Imagen del header relacionada con viaje" />
                </section>
            </header>
          <main>
                <div>
                    <Carrusel />
                </div>
                <CardsVenta />
            </main>
        </div>
    )


}
export default PropertySale