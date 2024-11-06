import { useContext, useState } from "react";
import { createContext } from "react";
import {
    deleteProperty,
    editProperty,
    getProperty,
    getPropertys,
    postProperty,
} from "../service/property";

export const PropertyContext = createContext();

export const useProperty = () => {
    const context = useContext(PropertyContext);
    if (!context) {
        throw new Error("El useProperty debe estar dentro del contexto");
    }
    return context;
};
export const PropertyProvider = ({ children }) => {
    const [propertysAll, setPropertysAll] = useState([]);

    const _getProperty = async (propertyId) => {
        try {
            const property = await getProperty(propertyId);
            return property.data;
        } catch (error) {
            console.log(error);
        }
    };

    const _getPropertys = async () => {
        try {
            const propertys = await getPropertys();
            setPropertysAll(propertys.data);
        } catch (error) {
            console.log(error);
        }
    };

    const _postProperty = async (property) => {
        try {
            const res = await postProperty(property);
            console.log(res.data);
        } catch (error) {
            console.log(error);
        }
    };

    const _putProperty = async (id, newProperty) => {
        try {
            const editPropertyNew = await editProperty(id, newProperty);
            await _getPropertys();
            return editPropertyNew.data;
        } catch (error) {
            console.log(error);
        }
    };

    const _deleteProperty = async (id) => {
        try {
            const delProperty = await deleteProperty(id);
            if (delProperty.status === 200)
                setPropertysAll(
                    propertysAll.filter((property) => property._id !== id)
                );
        } catch (error) {}
    };

    return (
        <PropertyContext.Provider
            value={{
                _getProperty,
                _getPropertys,
                propertysAll,
                _postProperty,
                _putProperty,
                _deleteProperty,
            }}
        >
            {children}
        </PropertyContext.Provider>
    );
};
