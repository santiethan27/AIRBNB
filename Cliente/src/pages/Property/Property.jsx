import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";;
import "./Property.css";
import { useProperty } from "../../context/PropertyContext";
import { useParams } from "react-router-dom";
import { useService } from "../../context/ServiceContext";
import MyCalendar from './Components/MyCalendar';
import { useForm } from "react-hook-form";
import { useAuth } from "../../context/AuthContext";
import { useReserve } from "../../context/ReserveContext";

function Property() {
  let { id } = useParams();
  const [showImageList, setShowImageList] = useState(false);
  const { user } = useAuth();
  const { _postReserve } = useReserve();
  const { _getProperty } = useProperty();
  const { _getService } = useService();
  const [propertyId, setPropertyId] = useState(id);
  const handleLastImageClick = () => {
    setShowImageList(!showImageList);
  };

  const { handleSubmit, formState: { errors }, register } = useForm();

  const [Data, setData] = useState([]);
  const [Service, setService] = useState([]);
  const [images, setImages] = useState([]);
  const [dates, setDates] = useState(null);
  const [reserveTrue, setReserveTrue] = useState(false);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await _getProperty(id);
        const servi = await _getService(id);
        setPropertyId(id);
        setData(response);
        if (response.images) {
          setImages(response.images);
        }
        setService(servi);
      } catch (error) {
        console.error("Error al obtener la data:", error);
      }
    };
    fetchData();
  }, [id, _getProperty]);
  

  const onSubmit = async (data) => {
    try {
      const formData = new FormData();
      if (user) {
        formData.append("client", user.user.id);
      }
      formData.append("property", Data.id);
      if (data.detail) {
        formData.append("detail", data.detail);
      }
      dates?.map((reservedDate, index) => {
        formData.append("startDate", reservedDate.startDate);
        formData.append("endDate", reservedDate.endDate);
        const price = Data.price * reservedDate.totalDays;
        formData.append("total_quatity", price);
      });
      await _postReserve(formData);
      setReserveTrue(true);
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <div className="container_property">
      <h1 className="txt-black">
        Apartamentos con hermosas vistas al bosques.
      </h1>
      <div className="Img">
        <img src={images[0]} className="img-firts" alt="Frist images" />
        <div className="img_F">
          {images.slice(1, 5).map((image, index) => (
            <img
              key={index}
              src={image}
              alt={`Image ${index + 2}`}
              className={`image-${index}`}
              onClick={index === 3 ? handleLastImageClick : null}
            />
          ))}
        </div>
      </div>
      {showImageList && (
        <div className="img_F">
          <h2 className="txt-white">Otras imágenes</h2>
          <div className="item">
            {images.slice(5).map((image, index) => (
              <img key={index} src={image} alt={`Image ${index + 5}`} />
            ))}
          </div>
        </div>
      )}

      <div className="title_two">
        <h2 className="txt-primary">{Data.adress}</h2>
      </div>
      <div className="start">
        <FontAwesomeIcon
          icon={faStar}
          size="2x"
          className="cursor-pointer txt-black"
        />
        <p className="txt-black">4.5 (54 RESEÑAS)</p>
      </div>
      <div className="userPhone">
        <img
          src="/img/userPhone.jpg"
          alt="Imagen de usuario"
          className="userP"
        />
        <div className="sas">
          <p className="txt-black">{Data.owner?.first_name}</p>
          <p className="txt-black">PROPIETARIO</p>
        </div>
      </div>
      <div className="Comentari">
        <p className="txt-black">{Data.description}</p>
      </div>
      {Service.map((service) => (
        <div key={service.id} className="servi">
          <h2 className="txt-black">Servicios</h2>
          <p className="txt-black">{service.name}</p>
        </div>
      ))}
      {
        user ?
          (
            <>
              {reserveTrue ?
                <p>Reserva realizada</p>
                :
                <div className="options">
                  <div className="Reserva">
                    <h1 className="txt-primary">Haz tu reserva</h1>
                    <MyCalendar setDates={setDates} datesReserve={dates} propertyId={propertyId} />
                  </div>
                  <div className="commit">
                    <p className="txt-primary">${Data.price} COP|noche</p>
                    <div className="price">
                      {
                        dates?.map((reservedDate, index) => (
                          <>
                            <p className="txt-black">{reservedDate.startDate.toLocaleDateString()} AL {reservedDate.endDate.toLocaleDateString()} - ({reservedDate.totalDays}) Noches</p>
                            <p className="txt-black">TOTAL: ${Data.price * reservedDate.totalDays}</p>
                          </>
                        ))
                      }
                    </div>

                    <form onSubmit={handleSubmit(onSubmit)}>
                      <label className="txt-secundary">¿ALGUN DETALLE PARA El PROPIETARIO?</label>
                      <textarea {...register("detail")}></textarea>
                      <button type="submit" className="bg-primary txt-white" disabled={dates ? false : true}>
                        ENVIAR RESERVA
                      </button>
                    </form>
                  </div>
                </div>
              }
            </>
          )
          :
          <>
            <h2>Inicia sesion para reservar</h2>
          </>
      }
    </div>
  );
}

export default Property;
