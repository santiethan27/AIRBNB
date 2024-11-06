import React from "react";
import "./FormProperty.css";
import { useForm } from "react-hook-form";
import { useProperty } from "../../context/PropertyContext";

function FormProperty() {
    const {
        handleSubmit,
        formState: { errors },
        register,
    } = useForm();

    const { _postProperty } = useProperty();

    const onSubmit = async (data) => {
        try {
            const formData = new FormData();
            formData.append("description", data.description);
            formData.append("rating", data.rating);
            formData.append("postalCode", data.postalCode);
            formData.append("propertyType", data.propertyType);
            formData.append("size", data.size);
            formData.append("priceType", data.priceType);
            for (let i = 0; i < data.images.length; i++) {
                formData.append("images", data.images[i]);
            }
            const res = await _postProperty(formData);
            console.log(res);
        } catch (error) {
            console.log(error.message);
        }
    };

    return (
        <form className="property-form" onSubmit={handleSubmit(onSubmit)}>
            <h1 className="txt-primary">Registro de propiedad</h1>
            <div className="f-input">
                <input
                    type="text"
                    placeholder="Descripcion"
                    {...register("description", { required: true })}
                />
                {errors.text && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div>
            <div className="f-input">
                <input
                    type="number"
                    placeholder="Rating"
                    {...register("rating", { required: true })}
                />
                {errors.text && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div>
            <div className="t-input">
                {/* <input
                    type="name"
                    placeholder="Nombre de propiedad"
                    {...register("name", { required: true })}
                />
                {errors.name && (
                    <p className="error-message">Este campo es requerido</p>
                )} */}

                <select {...register("propertyType", { required: true })}>
                    <option value="">Seleccione el tipo de propiedad</option>
                    <option value="Cabaña">Cabaña</option>
                    <option value="Apartamento">Apartamento</option>
                    <option value="Mansión">Mansión</option>
                </select>
                {errors.propertyType && (
                    <p className="error-message">Este campo es requerido</p>
                )}

                <input
                    type="number"
                    placeholder="Codigo postal"
                    {...register("postalCode", { required: true })}
                />
                {errors.number && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div>
            <div className="t-input">
                <select {...register("priceType", { required: true })}>
                    <option value="">Seleccione el tipo de renta</option>
                    <option value="Rentar">Rentar</option>
                    <option value="Comprar">Comprar</option>
                </select>
                {errors.propertyType && (
                    <p className="error-message">Este campo es requerido</p>
                )}

                <input
                    type="number"
                    placeholder="Tamaño de propiedad"
                    {...register("size", { required: true })}
                />
                {errors.text && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div>

            <div className="f-input"></div>
            <div className="f-input">
                <input
                    type="file"
                    placeholder="Imagenes"
                    {...register("images", { required: true })}
                />
                {errors.text && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div>

            {/* <div className="m-input">
                <input
                    type="text"
                    placeholder="Direccion"
                    {...register("address", { required: true })}
                />
                {errors.text && (
                    <p className="error-message">Este campo es requerido</p>
                )}

                <select {...register("city", { required: true })}>
                    <option value="">Seleccione la ciudad</option>
                    <option value="Ciudad de México">Ciudad de México</option>
                    <option value="Guadalajara">Guadalajara</option>
                    <option value="Monterrey">Monterrey</option>
                    <option value="Puebla">Puebla</option>
                </select>
                {errors.city && (
                    <p className="error-message">Este campo es requerido</p>
                )}
            </div> */}
            {/* <h2 className="txt-primary">Selecciona los servicios</h2>
            <div className="checkbox-container">
                <label>
                    <input type="checkbox" {...register("wifi")} />
                    <p> Wifi </p>
                </label>
                <label>
                    <input type="checkbox" {...register("cocina")} />
                    Cocina
                </label>
                <label>
                    <input type="checkbox" {...register("refrigeracion")} />
                    Refrigeración
                </label>

                <label>
                    <input type="checkbox" {...register("television")} />
                    Televisión
                </label>
                <label>
                    <input type="checkbox" {...register("lavadora")} />
                    Lavadora
                </label>
                <label>
                    <input type="checkbox" {...register("aire ")} />
                    Aire
                </label>
            </div> */}

            <div className="button-container">
                <button className="bg-primary" type="submit">
                    Registrar Propiedad
                </button>
            </div>
        </form>
    );
}
export default FormProperty;