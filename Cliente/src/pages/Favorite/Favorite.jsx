import React, { useEffect, useState } from "react";
import './Favorite.css'
import { useAuth } from "../../context/AuthContext";
import { useFavorte } from "../../context/FavoriteContext";
import CardsVenta from "../Home/components/CardsVenta";


function Favorite() {
    const { favorites, _getFavorits } = useFavorte();
    const { user } = useAuth();
    useEffect(() => {
        const fetchFavorites = async () => {
            if (user) {
                try {
                    await _getFavorits(user.user.id);
                } catch (error) {
                    console.error('Error al recuperar favoritos:', error);
                }
            }
        };

        fetchFavorites();
    }, []);
    const property = favorites.map(item => item.property);
    return (
        <div className="container-favorite">
            <h1 className="txt-primary">Tus favoritos</h1>
            <CardsVenta className="property" properties={property} />

        </div>
    );
}

export default Favorite;
