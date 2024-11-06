import axios from "./axios";

export const getProperty = async (propertyId) => await axios.get(`/property/${propertyId}`);

export const getPropertys = async () => await axios.get("/property/getall");

export const postProperty = async (property) => await axios.post(`/property/register`, property);

export const editProperty = async (id, newProperty) => await axios.put(`/property/${id}`, newProperty);

export const deleteProperty = async (id) => await axios.delete(`/property/${id}`);