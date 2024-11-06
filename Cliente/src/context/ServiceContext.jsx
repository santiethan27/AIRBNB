import { useContext, useState } from "react";
import { createContext } from "react";
import { deleteServices, getService } from "../service/service";

export const ServiceContext = createContext();

export const useService = () => {
    const context = useContext(ServiceContext);
    if (!context) {
        throw new Error("El useService debe estar dentro del contexto");
    }
    return context;
}
export const ServiceProvider = ({ children }) => {

    const [serviceAll, setServiceAll] = useState([]);

    const _getService = async (propertyId) =>{
        try{
            const service = await getService(propertyId);
            return service.data;
        } catch (error){
            console.log(error);
        }
    }

    const _deleteService = async (id) => {
        try{
            const delService = await deleteServices(id);
            if(delService.status === 200) setServiceAll(serviceAll.filter((service)=> service._id !== id))
        } catch (error){
    
        }
    }

    return(
        <ServiceContext.Provider value={{_getService, _deleteService, serviceAll}}>
            {children}
        </ServiceContext.Provider>
    )
}