import React, { useState } from "react";
import { useForm } from 'react-hook-form'; //Importacion de formulario
import './LoginSecurity.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLock, faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons"; //Iconos utilizados en la aplicacion

function LoginSecurity() {
    const [showPassword, setShowPassword] = useState(false);  //Controla si la contraseña debe mostrarse o no en texto claro
    const { handleSubmit, formState: { errors }, register } = useForm();

    const handleTogglePassword = () => {
        setShowPassword(!showPassword); //Esta función se activa cuando el usuario hace clic en el icono de ojo para mostrar/ocultar la contraseña.
    };

    const handleInputChange = (e) => {  //Esta función se activa cuando hay un cambio en un campo de entrada del formulario, en este caso, especialmente en el campo de contraseña.
        const { name, value } = e.target;
        if (name === 'password' && value !== '') {
            setShowPassword(true);
        } else {
            setShowPassword(false);
        }
    };

    const onSubmit = (data) => {
        console.log(data);
    };

    return (
        <div className="login-container">
            <h2 className="txt-primary">
                <FontAwesomeIcon icon={faLock} className="icon-blue" /> Inicio de Sesión
            </h2>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="password-container">
                    <div className="password-content">
                        <label htmlFor="password" className="label-password">Contraseña:</label>
                        <div className="password-input">
                            <input type={showPassword ? "text" : "password"}
                                id="password"
                                name="password"
                                className="input-password"
                                placeholder="Contraseña modificada hace 18 horas"
                                {...register('password', { required: true })}
                                onChange={handleInputChange}
                            />
                            {showPassword &&
                                <FontAwesomeIcon
                                    icon={showPassword ? faEyeSlash : faEye}
                                    className="eye-icon"
                                    onClick={handleTogglePassword}
                                />
                            }
                        </div>
                        {errors.password && <p className="error-message">Este campo es requerido</p>}
                    </div>
                </div>
                <div className="password-container">
                    <div className="password-content">
                        <label htmlFor="currentPassword" className="label-password">Contraseña Actual:</label>
                        <div className="password-input">
                            <input
                                type={showPassword ? "text" : "password"}
                                id="currentPassword"
                                name="currentPassword"
                                className="input-password"
                                {...register('currentPassword', { required: true })}
                                onChange={handleInputChange}
                            />
                            {showPassword &&
                                <FontAwesomeIcon
                                    icon={showPassword ? faEyeSlash : faEye}
                                    className="eye-icon"
                                    onClick={handleTogglePassword}
                                />
                            }
                        </div>
                        {errors.currentPassword && <p className="error-message">Este campo es requerido</p>}
                    </div>
                </div>
                <div className="password-container">
                    <div className="password-content">
                        <label htmlFor="newPassword" className="label-password">Nueva Contraseña:</label>
                        <div className="password-input">
                            <input
                                type={showPassword ? "text" : "password"}
                                id="newPassword"
                                name="newPassword"
                                className="input-password"
                                {...register('newPassword', { required: true })}
                                onChange={handleInputChange}
                            />
                            {showPassword &&
                                <FontAwesomeIcon
                                    icon={showPassword ? faEyeSlash : faEye}
                                    className="eye-icon"
                                    onClick={handleTogglePassword}
                                />
                            }
                        </div>
                        {errors.newPassword && <p className="error-message">Este campo es requerido</p>}
                    </div>
                </div>
                <div className="password-container">
                    <div className="password-content">
                        <label htmlFor="confirmPassword" className="label-password">Confirmar Contraseña:</label>
                        <div className="password-input">
                            <input
                                type={showPassword ? "text" : "password"}
                                id="confirmPassword"
                                name="confirmPassword"
                                className="input-password"
                                {...register('confirmPassword', { required: true })}
                                onChange={handleInputChange}
                            />
                            {showPassword &&
                                <FontAwesomeIcon
                                    icon={showPassword ? faEyeSlash : faEye}
                                    className="eye-icon"
                                    onClick={handleTogglePassword}
                                />
                            }
                        </div>
                        {errors.confirmPassword && <p className="error-message">Este campo es requerido</p>}
                    </div>
                </div>
                <div className="button-container">
                    <button className="bg-primary" type="submit">Actualizar</button>
                </div>
            </form>
        </div>
    );
}

export default LoginSecurity;
