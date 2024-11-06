import axios from "./axios";

export const getFavorits = async (user) => await axios.get(`/favorite/${user}`);
export const postFavorite = async (createFavorite) => await axios.post("/favorite/create_favorite", createFavorite);
export const editFavorite = async (id,newFavorite) => await axios.put(`/favorite/${id}`,newFavorite);
export const deleteFavorite = async (favoriteId) => await axios.delete(`/favorite/${favoriteId}`);
