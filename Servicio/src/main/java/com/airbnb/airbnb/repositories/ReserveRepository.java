/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.airbnb.airbnb.repositories;

import com.airbnb.airbnb.entities.Reserve;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, String> {

    @Query("SELECT r FROM Reserve r WHERE r.property.id = :propertyId")
    List<Reserve> findByPropertyId(@Param("propertyId") String propertyId);

    @Query("SELECT r FROM Reserve r WHERE r.client.id = :clientId")
    List<Reserve> findByClientId(@Param("clientId") String clientId);
}
