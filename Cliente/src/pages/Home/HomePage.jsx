import React, { useEffect, useState } from "react";
import "./HomePage.css";
import Carrusel from "./components/Carrusel";
import CardsVenta from "./components/CardsVenta";
import { useProperty } from "../../context/PropertyContext";

const HomPage = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredPropertys, setFilteredPropertys] = useState([]);
  const { propertysAll, _getPropertys } = useProperty();
  const [categoryFilter, setCategory] = useState(null);
  useEffect(() => {
    _getPropertys();
  }, []);
  useEffect(() => {
    setFilteredPropertys(propertysAll);
  }, [propertysAll]);
  const handleSearch = () => {
    const result = propertysAll.filter((property) =>
      property.city.ciudadNombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
      property.country.paisNombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
      property.adress.toLowerCase().includes(searchTerm.toLowerCase()) ||
      property.size.toString().includes(searchTerm.toLowerCase())
    );
    if (categoryFilter) {
      const categoryResult = result.filter(property => property.propertyTypes.toLowerCase() === categoryFilter.toLowerCase());
      setFilteredPropertys(categoryResult);
    } else {
      setFilteredPropertys(result);
    }
  };

  useEffect(() => {
    handleSearch();
    console.log(categoryFilter)
  }, [categoryFilter]);

  const activeCategory = (category) => {
    setCategory(category);
  }

  return (
    <div>
      <header className="bg-secundary">
        <section className="txt-white">
          <h2>Encuentra tu próxima estancia</h2>
          <h3>Busca ofertas en hoteles, casas y mucho más...</h3>
          <div className="selector-option">
            <input
              type="text"
              placeholder="Ciudad, Pais, Direccion, Tamaño..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button className="selector txt-white" onClick={handleSearch}>
              Buscar
            </button>
          </div>
        </section>
        <section>
          <img
            src="/img/HeaderHome.png"
            alt="Imagen del header relacionada con viaje"
          />
        </section>
      </header>
      <main>
        <div>
          <Carrusel activeCategory={activeCategory} />
        </div>
        {
          filteredPropertys.length > 0 ?
            <CardsVenta properties={filteredPropertys} />
            :
            <div className="error">No se encontraron resultados</div>
        }
      </main>
    </div>
  );
};

export default HomPage;
