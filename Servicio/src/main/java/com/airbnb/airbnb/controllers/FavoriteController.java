package com.airbnb.airbnb.controllers;

import com.airbnb.airbnb.entities.Favorite;
import com.airbnb.airbnb.requests.FavoriteRequest;
import com.airbnb.airbnb.servicies.FavoriteService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/create_favorite")
    public ResponseEntity<?> registerFavorite(@ModelAttribute FavoriteRequest request) throws Exception {
        try {

            if (request.getUser() == null || request.getUser().isEmpty()
                    || request.getProperty() == null || request.getProperty().isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
            }
            favoriteService.createFavorite(request.getUser(), request.getProperty());
            return ResponseEntity.ok("Servicio registrado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", request));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @ModelAttribute FavoriteRequest request) {
        try {
            favoriteService.updatedFavorite(id, request);
            return ResponseEntity.ok("Favorito actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<?> deleteUser(@PathVariable String favoriteId) {
        try {
            favoriteService.deleteFavorite(favoriteId);
            return ResponseEntity.ok("Favorito eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable String userId) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }
}
