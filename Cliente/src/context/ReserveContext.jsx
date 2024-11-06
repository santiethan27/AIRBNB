import { createContext, useContext, useState } from "react";
import { deleteReserve, getReserve, getReserveUser, postReserve, putReserve } from "../service/reserve";

export const ReserveContext = createContext();

export const useReserve = () => {
    const context = useContext(ReserveContext);
    if (!context) {
        throw new Error("El useReserve debe estar dentro del contexto");
    }
    return context;
}

export const ReserveProvider = ({ children }) => {

    const [reserves, setReserves] = useState();

    const _postReserve = async (reserve) => {
        try {
            const createReserve = await postReserve(reserve);
        } catch (error) {
            console.log(error)
        }
    }

    const _getReserve = async (property) => {
        try {
            const res = await getReserve(property);
            setReserves(res.data);
        } catch (error) {
            console.log(error);
        }
    }
    const _getReserveUser = async (user) => {
        try {
            const res = await getReserveUser(user);
            setReserves(res.data);
        } catch (error) {
            console.log(error);
        }
    }

    const _putReserve = async (id) => {
        try {
            const ediReserve = await putReserve(id)
        } catch (error) {
            console.log(error)
        }
    }

    const _deleteReserve = async (id) => {
        try {
            await deleteReserve(id)
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <ReserveContext.Provider value={{ _postReserve, _getReserve, reserves, _getReserveUser }}>
            {children}
        </ReserveContext.Provider>
    )
}