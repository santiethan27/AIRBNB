import React, { useState } from "react";
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import "./Carrusel.css";
import { Pagination } from 'swiper/modules';
const Carrusel = ({ activeCategory }) => {
    const imageData = [
        { title: 'APARTAMENTO', src: '/img/apartamento.png', alt: 'Apartamento' },
        { title: 'CASA', src: '/img/casa-1.png', alt: 'Casa' },
        { title: 'HOTEL', src: '/img/casa-1.png', alt: 'Casa' },
        { title: 'CABAÑA', src: '/img/casa-1.png', alt: 'Casa' },
    ];
    const [activeSlideIndex, setActive] = useState(null);
    const active = (item, index) => {
        if (index == activeSlideIndex) {
            activeCategory(null);
            setActive(null);
            return;
        }
        activeCategory(item.title);
        setActive(index);
    }

    return (
        <>
            <Swiper
                slidesPerView={'auto'}
                spaceBetween={30}
                pagination={{ clickable: true, }}
                modules={[Pagination]}
                className="mySwiper">
                {
                    imageData.map((item, index) => (
                        <SwiperSlide key={index} >
                            <div className={`img-container ${activeSlideIndex === index ? 'active' : ''}`} onClick={() => active(item, index)}>
                                <img src={item.src} alt={item.alt} />
                                <div className="title">
                                    {item.title}
                                </div>
                            </div>
                        </SwiperSlide>
                    ))
                }
            </Swiper>
        </>
    );
};

export default Carrusel;
