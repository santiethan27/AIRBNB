import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import './EditProfile.css';
import { useAuth } from '../../context/AuthContext';
import { useCountry } from '../../context/CountryContext';

function EditProfile() {
    const { handleSubmit, formState: { errors }, register } = useForm();
    const { _putUserId } = useAuth();
    const { _getCountries, countries } = useCountry();

    useEffect(() => {
        _getCountries();
    }, []);

    const onSubmit = async (data) => {
        try {
            const formData = new FormData();
            formData.append('firstName', data.name);
            formData.append('lastName', data.lastname);
            formData.append('phone', data.phone);
            formData.append('country', data.country);
            formData.append('photo', data.photo[0]);

            const res = await _putUserId("a97dc843-6221-4e69-897c-e97a966f32fc", formData);

            if (res === 200) {
                console.log(res);
                navigate("/");
            }
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <form className="profile" onSubmit={handleSubmit(onSubmit)}>
            <div className="profile-container">
            <div className="image-container">
                      <input type="file" {...register('photo', { required: true })} />
                      <img src="../../../public/img/profile.png" alt="Imagen prestablecida" />
                        </div>
                <div className="fields-container">
                    <h1 className="txt-primary">Modifica tus datos personales</h1>
                    <div className="g-input">
                    
                        <div className="f-input">
                            <input type="name" placeholder="Nombre" {...register('name', { required: true })} />
                            {errors.name && (<p className="error-message">Este campo es requerido</p>)}
                        </div>
                        <div className="f-input">
                            <input type="text" placeholder="Apellido" {...register('lastname', { required: true })} />
                            {errors.lastname && (<p className="error-message">Este campo es requerido</p>)}
                        </div>
                    </div>
                    <div className="l-input">
                        <div className="f-input">
                            <input type="text" placeholder="Telefono" {...register('phone', { required: true })} />
                            {errors.phone && (<p className="error-message">Este campo es requerido</p>)}
                        </div>
                        <select {...register('country', { required: true })}>
                            {countries?.map((opcion, index) => (
                                <option key={index} value={opcion.paisCodigo}>{opcion.paisNombre}</option>
                            ))}
                        </select>
                    </div>

                </div>
            </div>
            <div className="button-container">
                <button className="bg-primary" type="submit">Guardar Cambios</button>
            </div>
        </form>
    );
}

export default EditProfile;
