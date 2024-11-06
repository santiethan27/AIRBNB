import axios from "./axios";

export const getService = async (propertyId) => await axios.get(`/serviceproperty/${propertyId}`);
export const deleteServices = async (id)  => await axios.delete(`/serviceproperty/${id}`);
export const putService = async (id) => await axios.put(`/serviceproperty/${id}`);