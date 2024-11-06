import authHeader from "./auth-header";
import axios from "./axios";

export const getUserId = async (userId) => await axios.get(`/users${userId}`);
export const getUsers = async () => await axios.get("/users");
export const postRegisterUser = async (user) =>
  await axios.post("/users/register", user);
export const postLoginUser = async (user) =>
  await axios.post("/users/login", user);
export const editUserId = async (userId, data) =>
  await axios.put(`/users/${userId}`, data);
export const deleteUserId = async (userId) =>
  await axios.delete(`/users/${userId}`);
export const verityTokenRequest = async () =>
  await axios.get("/users/verify", { headers: authHeader() });
