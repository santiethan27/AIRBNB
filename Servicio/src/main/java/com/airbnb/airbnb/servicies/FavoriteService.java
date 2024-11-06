//Dev Duque
package com.airbnb.airbnb.servicies;

import com.airbnb.airbnb.entities.Favorite;
import com.airbnb.airbnb.entities.Property;
import com.airbnb.airbnb.entities.User;
import com.airbnb.airbnb.repositories.FavoriteRepository;
import com.airbnb.airbnb.repositories.RepositoryProperty;
import com.airbnb.airbnb.repositories.UserRepository;
import com.airbnb.airbnb.requests.FavoriteRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private RepositoryProperty repositoryProperty;

    @Autowired
    private UserRepository userRepository;

    public List<Favorite> getFavoritesByUserId(String userId) {
        return favoriteRepository.findAllByUserId(userId);
    }

    @Transactional
    public void createFavorite(String user, String property) throws Exception {
        try {
            Favorite favorite = new Favorite();
            if (favoriteRepository.existsByUserIdAndPropertyId(user, property)) {
                throw new IllegalArgumentException("Favorite already exists for this property.");
            }
            Optional<Property> answer = repositoryProperty.findById(property);
            if (answer.isPresent()) {
                favorite.setProperty(answer.get());
            } else {
                throw new IllegalArgumentException("Propiedad  no encontrada");
            }

            Optional<User> answerr = userRepository.findById(user);
            if (answer.isPresent()) {
                favorite.setUser(answerr.get());
            } else {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            favoriteRepository.save(favorite);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void updatedFavorite(String favoriteId, FavoriteRequest request) throws IOException {
        Optional<Favorite> answer = favoriteRepository.findById(favoriteId);
        if (answer.isPresent()) {
            Favorite favorite = answer.get();

            if (request.getProperty() != null) {
                Optional<Property> answerProperty = repositoryProperty.findById(request.getProperty());
                if (answerProperty.isPresent()) {
                    favorite.setProperty(answerProperty.get());
                } else {
                    throw new IllegalArgumentException("Propiedad no encontrado");
                }
            }

            if (request.getUser() != null) {
                Optional<User> answerUser = userRepository.findById(request.getUser());
                if (answerUser.isPresent()) {
                    favorite.setUser(answerUser.get());
                } else {
                    throw new IllegalArgumentException("Propiedad no encontrado");
                }
            }

            favoriteRepository.save(favorite);

        } else {
            throw new IllegalArgumentException("Error al actualizar");
        }
    }

    public void deleteFavorite(String favoriteId) throws Exception {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new IllegalArgumentException("No se puedo encontrar el Id" + favoriteId));
        favoriteRepository.delete(favorite);
    }

    public List<Favorite> getAllFavorite() {
        return favoriteRepository.findAll();
    }

}
