//Dev Duque
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.Property;
import com.airbnb.airbnb.entities.ServiceProperty;
import com.airbnb.airbnb.repositories.RepositoryProperty;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airbnb.airbnb.repositories.ServicepPropertyRepository;
import com.airbnb.airbnb.requests.ServicePropertyRequest;
import java.io.IOException;
import java.util.List;

@Service
public class ServicePropertyService {
    
    @Autowired
    private ServicepPropertyRepository servicePropertyRepository;
    
    @Autowired
    private RepositoryProperty repositoryProperty;
     
    //Method
    @Transactional
    public void createServiceProperty(String name, String property) throws Exception {
          try {
            ServiceProperty serviceProperty = new ServiceProperty();
            
             Optional<Property> optionalProperty = repositoryProperty.findById(property);
        if (optionalProperty.isPresent()) {
            serviceProperty.setProperty(optionalProperty.get());
        } else {
            throw new IllegalArgumentException("Propiedad  no encontrada");
        }
            serviceProperty.setName(name);
            servicePropertyRepository.save(serviceProperty);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
     
    @Transactional
    public void updatedServiceProperty(String servicePropertyId, ServicePropertyRequest request ) throws IOException {
        Optional<ServiceProperty> answer = servicePropertyRepository.findById(servicePropertyId);
            if(answer.isPresent()){
                ServiceProperty serviceProperty = answer.get();
                
                if (request.getName() != null) {
                     serviceProperty.setName(request.getName());
                }
                
            servicePropertyRepository.save(serviceProperty);
           
        }else{
                
        }
    }

    @Transactional
    public void deleteServiceProperty(String servicePropertyId){
        servicePropertyRepository.deleteById(servicePropertyId);
    }
    
    @Transactional
    public List<ServiceProperty> findByPropertyId(String property){
        return servicePropertyRepository.findByPropertyId(property);
    }
    
}
