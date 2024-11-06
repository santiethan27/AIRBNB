import React from "react";
import './Register.css';
import { useForm } from "react-hook-form";
import { useAuth } from "../../../context/AuthContext";
import { useNavigate } from "react-router-dom";

function Login({ handleToggle }) {
    const { handleSubmit, formState: { errors }, register } = useForm();
    const { login } = useAuth();
    const navigate = useNavigate();
    const onSubmit = async (data) => {
        try {
            const formData = new FormData();
            formData.append("email", data.email);
            formData.append("password", data.password);
            const res = await login(formData);
            if (res == 200) {
                navigate("/");
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <form className="register-form" onSubmit={handleSubmit(onSubmit)}>
            <h1 className="txt-primary">Iniciar sesion</h1>
            <div className="g-input">
                <input type="email" placeholder="Email"{...register('email', { required: true })}
                />{errors.email && (<p className="error-message">Este es campo es requerido</p>)}
            </div>
            <div className="g-input">
                <input type="password" placeholder="Contraseña"{...register('password', { required: true })}
                />{errors.password && (<p className="error-message">Este es campo es requerido</p>)}
            </div>
            <div className="options">
                <a href="#" className="txt-primary" onClick={handleToggle}>¿Ya tienes cuenta?</a><a href="" className="txt-primary">¿Olvidaste tu contraseña?</a>
            </div>
            <button className="bg-primary" type="submit">Iniciar sesion</button>
        </form>
    )

}

export default Login

