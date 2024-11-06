import React, { useEffect } from "react";
import "./YouReservation.css";
import { useAuth } from "../../context/AuthContext";
import { useReserve } from "../../context/ReserveContext";

function YouReservation() {
    const { user } = useAuth();
    const { _getReserveUser, reserves } = useReserve();
    useEffect(() => {
        if (user && user.user && user.user.id) {
            const fetchData = async () => {
                await _getReserveUser(user.user.id);
            }
            fetchData();
        }
    }, [user]);
    return (
        <>
            <header className="bg-primary reservation">
                <p className="txt-white">Tus reservas</p>
            </header>
            {reserves?.map((reserve, key) => (
                <div className="container reservation" key={key}>
                    <div className="container-left">
                        <img src="/img/Lago.png" alt="" />
                    </div>
                    <div className="container-right">
                        <p>Apartamento</p>
                        <p className="txt-primary title">Medellin, Colombia</p>
                        <p>Apartamento, con hermosa vista al mar.</p>
                        <div className="service">
                            <p>Servicios</p>
                            <p>
                                Television, wifi, lavadora, cocina, aire
                                acondicionado, lavadora
                            </p>
                        </div>
                        <div className="double-row">
                            <p className="txt-primary">$ 400.000 COP</p>
                            <p>Por noche.</p>
                        </div>
                        <div className="double-rows">
                            <p>2 de enero al 5 de enero</p>
                            <p>total: $1.200.000 COP</p>
                        </div>
                        <p>4.5</p>
                    </div>
                </div>
            ))}
        </>
    );
}

export default YouReservation;
