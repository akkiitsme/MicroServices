package com.hotelservice.hotels;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {

    Hotel findByHotelId(String id);
}
