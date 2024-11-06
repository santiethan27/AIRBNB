
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.PropertyTypes;
import com.airbnb.airbnb.repositories.PropertyTpRepository;
import com.airbnb.airbnb.requests.PropertyTpRequest;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTpService {
    
    @Autowired
    private PropertyTpRepository propertyTpRepository;
    
    @Transactional
    public void createPropertyType(String title, String images) throws Exception  {
       
        PropertyTypes propertyType = new PropertyTypes();
        
                propertyType.setTitle(title);
                propertyType.setImages(images);
                
                propertyTpRepository.save(propertyType);
                
    }
    
    @Transactional
    public void updatedPropertyType(String id, PropertyTpRequest request){
        PropertyTypes propertyType = propertyTpRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + id));
    
        if (request.getTitle() != null) {
            propertyType.setTitle(request.getTitle());
        }
        
        propertyTpRepository.save(propertyType);
    }
    
      @Transactional
    public void deletePropertyType(String id) throws Exception {
        PropertyTypes propertyType = propertyTpRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar con el id" + id));
        propertyTpRepository.delete(propertyType);

    }
    
    @Transactional
    public List<PropertyTypes> getAllPropertyType(){
        return propertyTpRepository.findAll();
    }
    
}
