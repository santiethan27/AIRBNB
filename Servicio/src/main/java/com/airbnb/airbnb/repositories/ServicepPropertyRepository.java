//Dev Duque
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.ServiceProperty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicepPropertyRepository extends JpaRepository<ServiceProperty,String> {
    List<ServiceProperty> findByPropertyId(String property);
}
