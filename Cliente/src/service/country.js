import axios from "./axios";

export const getCountry = async () => await axios.get(`/country/country`);