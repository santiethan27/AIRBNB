import React, { useEffect, useState } from "react";
import './CrudProperty.css'
import FormProperty from "../../FormProperty/FormProperty"
import Menu from "../components/Menu"
import { useAuth } from "../../../context/AuthContext";
import { useProperty } from "../../../context/PropertyContext";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDeleteLeft, faPenToSquare, faTrash } from "@fortawesome/free-solid-svg-icons";

function CrudProperty() {
  const { propertysAll, _getPropertys } = useProperty();
  const [filter, setFilter] = useState("");
  const [showForm, setShowForm] = useState(false);
  const [showFormUpdate, setShowFormUpdate] = useState(false);
  useEffect(() => {
    _getPropertys();
  }, []);

  const handleCreateProperty = (newProperty) => {

  };

  const handleDeleteProperty = (index) => {
    const updateProperties = propertysAll.filter((_, i) => i !== index);
  };

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const filteredProperties = propertysAll.filter((property) =>
    property.description.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <div className="crud-property">
      <Menu />
      <div className="container-table">
        <div className="header">
          <h2 className="txt-primary">Tabla de Propiedades</h2>
          <input
            type="text"
            placeholder="Buscar propiedad"
            value={filter}
            onChange={handleFilterChange}
          />
          <button className="create" onClick={() => setShowForm(true)}>Crear Propiedad</button>
        </div>
        <table className="table-property">
          <thead className="txt-white bg-secundary">
            <tr>
              <th>Nombre Propiedad</th>
              <th>Descripción</th>
              <th>Rating</th>
              <th>Código Postal</th>
              <th>Tipo de Propiedad</th>
              <th>Tamaño</th>
              <th>Tipo de Renta</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {filteredProperties.map((property, index) => (
              <tr key={index}>
                {console.log(property)}
                <td>{property.title}</td>
                <td>{property.description}</td>
                <td>{property.rating}</td>
                <td>{property.postalCode}</td>
                <td>{property.propertyTypes}</td>
                <td>{property.size}</td>
                <td>{property.priceType}</td>
                <td>{property.state}</td>
                <td>
                  <div className="actions">
                    <button className="actions" onClick={() => setShowFormUpdate(true)}>
                      <FontAwesomeIcon icon={faPenToSquare} />
                    </button>
                    <button className="actions" onClick={() => setShowFormUpdate(true)}>
                      <FontAwesomeIcon icon={faTrash} />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {showForm && (
        <div className="modal">
          <div className="modal-content">
            <span className="close-icon" onClick={() => setShowForm(false)}>
              &times;
            </span>
            <FormProperty onCreateProperty={handleCreateProperty} />
          </div>
          <div className="modal-backdrop" onClick={() => setShowForm(false)}></div>
        </div>
      )}
      {showFormUpdate && (
        <div className="modal">
          <div className="modal-content">
            <span className="close-icon" onClick={() => setShowFormUpdate(false)}>
              &times;
            </span>
            <FormProperty onCreateProperty={handleCreateProperty} />
          </div>
          <div className="modal-backdrop" onClick={() => setShowFormUpdate(false)}></div>
        </div>
      )}
    </div>
  );
}

export default CrudProperty;