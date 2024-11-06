//Dev Duque
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, String> {
    List<Favorite> findAllByUserId(String userId);
    boolean existsByUserIdAndPropertyId(String userId, String propertyId);
}
