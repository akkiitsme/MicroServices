package com.hotelservice.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired HotelRepository hotelRepository;
}
