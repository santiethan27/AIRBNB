import { createContext, useContext, useState } from "react";
import { deleteFavorite, editFavorite, getFavorits, postFavorite } from "../service/favorite";

export const FavoriteContext = createContext();

export const useFavorte = () => {
    const context = useContext(FavoriteContext);
    if (!context) {
        throw new Error("El useFavorite debe estar dentro del contexto");
    }
    return context;
}
export const FavoriteProvider = ({ children }) => {

    const [favorites, setFavorites] = useState([]);

    const _getFavorits = async (user) => {
        try {
            const favorite = await getFavorits(user);
            setFavorites(favorite.data);
        } catch (error) {
            console.log(error)
        }
    }

    const _postFavorite = async (createFavorite) => {
        try {
            const formData = new FormData();
            formData.append("user", createFavorite.user);
            formData.append("property", createFavorite.property);
            await postFavorite(formData);
            await _getFavorits(createFavorite.user);
        } catch (error) {
            console.log(error)
        }
    }

    const _editFavorite = async (id, newFavorite) => {
        try {
            const editFavoriteNew = await editFavorite(id, newFavorite);
            await _getFavorits();
            return editFavoriteNew.data;
        } catch (error) {
            console.log(error)
        }
    }

    const _deleteFavorite = async (favoriteId, userId) => {
        try {
            await deleteFavorite(favoriteId);
            await _getFavorits(userId);
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <FavoriteContext.Provider value={{ _getFavorits, _postFavorite, _editFavorite, _deleteFavorite, favorites }}>
            {children}
        </FavoriteContext.Provider>
    )
}