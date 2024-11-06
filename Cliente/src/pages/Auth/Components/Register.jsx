import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import './Register.css';
import { useAuth } from "../../../context/AuthContext";
import { useCountry } from "../../../context/CountryContext";
import { useNavigate } from "react-router-dom";

function Register({ handleToggle }) {
    const { handleSubmit, formState: { errors }, register } = useForm();
    const { _getCountries, countries } = useCountry();
    const { signUp } = useAuth();
    const navigate = useNavigate();
    useEffect(() => {
        _getCountries();
    }, []);
    const onSubmit = async (data) => {
        try {
            const formData = new FormData();
            formData.append('email', data.email);
            formData.append('password', data.password);
            formData.append('firstName', data.first_name);
            formData.append('lastName', data.last_name);
            formData.append('phone', data.phone);
            formData.append('birthDate', data.birthdate);
            formData.append('country', data.country);
            formData.append('photo', data.photo[0]);

            const res = await signUp(formData);

            if (res == 200) {
                console.log(res);
                navigate("/");
            }
        } catch (error) {
            console.log(error);
        }
    }


    return (
        <form className="register-form" onSubmit={handleSubmit(onSubmit)}>
            <h1 className="txt-primary">Registrarse</h1>
            <div className="g-input">
                <input type="email" placeholder="Email" {...register('email', { required: true })}
                />{errors.email && (<p className="error-message">Este es campo es requerido</p>)}
            </div>
            <div className="g-input">
                <input type="password" placeholder="Contraseña" {...register('password', { required: true })}
                />{errors.password && (<p className="error-message">Este es campo es requerido</p>)}
            </div>
            <div className="nested">
                <div className="g-input">
                    <input type="text" placeholder="Nombre"{...register('first_name', { required: true })}
                    />{errors.name && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
                <div className="g-input">
                    <input type="text" placeholder="Apellido"{...register('last_name', { required: true })}
                    />{errors.lastname && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
            </div>
            <div className="nested">
                <div className="g-input">
                    <input type="text" placeholder="Telefono"{...register('phone', { required: true })}
                    />{errors.phone && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
                <div className="g-input">
                    <input type="date" placeholder="Fecha de nacimiento"{...register('birthdate', { required: true })}
                    />{errors.birthdate && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
            </div>
            <div className="nested">
                <div className="g-input">
                    <label for="fileInput" className="file-label">Pais</label>
                    <select {...register('country', { required: true })}>
                        {countries?.map((opcion, index) => (
                            <option key={index} value={opcion.paisCodigo}>{opcion.paisNombre}</option>
                        ))}
                    </select>
                    {errors.country && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
                <div className="g-input">
                    <label for="fileInput" className="file-label">Foto de perfil</label>
                    <input type="file" {...register('photo', { required: true })} />
                    {errors.photo && (<p className="error-message">Este es campo es requerido</p>)}
                </div>
            </div>
            <div className="options">
                <a href="#" className="txt-primary" onClick={handleToggle}>¿Ya tienes cuenta?</a>
            </div>
            <button className="bg-primary" type="submit">Registrarse</button>
        </form>
    )
}
export default Register