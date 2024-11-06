import { faHeart, faStar } from '@fortawesome/fontawesome-free-solid';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect } from 'react'
import { NavLink } from 'react-router-dom';
import { useProperty } from '../../../context/PropertyContext';
import { useAuth } from '../../../context/AuthContext';
import { useFavorte } from '../../../context/FavoriteContext';

const CardsVenta = ({ properties }) => {

    const { user } = useAuth();
    const { _postFavorite, _deleteFavorite, favorites } = useFavorte();
    const fav = async (property) => {
        const formData = {
            "user": user.user.id,
            "property": property.id
        };

        await _postFavorite(formData);
    }

    const deleteFav = async (favoriteId) => {
        await _deleteFavorite(favoriteId, user.user.id);
    }

    return (
        <div className='container-img-main txt-black'>
            {properties.map((property) => (
                <div to={'/property'} key={property.id} className='card cursor-pointer'>
                    <NavLink to={`/property/${property.id}`} className='txt-black'>
                        <img key={property.id} src={property.images[0]} alt={property.images} />
                        <div className='container-details'>
                            <p className='title'>{property.city.ciudadNombre}, {property.country.paisNombre} </p>
                            <div className='container-price'>
                                <h5><FontAwesomeIcon icon={faStar} size='1x' /></h5>
                                <p className='rating'>{property.rating}</p>
                            </div>
                        </div>
                        <p className='txt-description'>{property.description}</p>
                        <div className='container-price'>
                            <h3>${property.price} COP</h3>
                            <p>Noche</p>
                        </div>
                    </NavLink>
                    {
                        favorites?.find(favorite => favorite.property.id === property.id) ?
                            <FontAwesomeIcon className='favorite' icon={faHeart} onClick={() => {
                                const favoriteToDelete = favorites.find(favorite => favorite.property.id === property.id);
                                deleteFav(favoriteToDelete.id);
                            }} />
                            :
                            <FontAwesomeIcon icon={faHeart} onClick={() => fav(property)} />
                    }
                </div>
            ))}
        </div>
    )
}

export default CardsVenta
