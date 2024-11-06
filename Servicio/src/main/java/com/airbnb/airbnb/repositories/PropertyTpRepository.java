//Dev Duque
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.PropertyTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTpRepository extends JpaRepository <PropertyTypes , String> {
    
}
