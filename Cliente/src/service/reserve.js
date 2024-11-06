import axios from "./axios";

export const postReserve = async (reserve) => await axios.post("/reserve/createreserve", reserve);
export const putReserve = async (id) => await axios.put(`/reserve/updatereserve/${id}`);
export const deleteReserve = async (id) => await axios.delete(`/reserve/${id}`);
export const getReserve = async (id) => await axios.get(`/reserve/property/${id}`);
export const getReserveUser = async (id) => await axios.get(`/reserve/client/${id}`);
