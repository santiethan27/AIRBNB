import { createContext, useContext, useState } from "react";
import { getCountry } from "../service/country";

export const CountryContext = createContext();

export const useCountry = () => {
    const context = useContext(CountryContext);
    if (!context) {
        throw new Error("El useCountry debe estar dentro del contexto");
    }
    return context;
}
export const CountryProvider = ({ children }) => {

    const [countries, setCountries] = useState([]);

    const _getCountries = async () => {
        try {
            const country = await getCountry();
            setCountries(country.data);
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <CountryContext.Provider value={{ _getCountries, countries }}>
            {children}
        </CountryContext.Provider>
    )
}