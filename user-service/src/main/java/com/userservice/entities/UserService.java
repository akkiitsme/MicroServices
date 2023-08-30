package com.userservice.entities;

import com.userservice.externalservice.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;


    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User getUser(String Id) {
        User user  = userRepository.findByUserId(Id);
        //defining the Urls
        String ratingUrl = "http://RATING-SERVICE/ratings/users/"+user.getUserId();
        String hotelUrl = "http://HOTEL-SERVICE/hotel/";

        //getting data from Rating-Service
        List<Rating> ratings = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(ratingUrl , Rating[].class)));
        logger.info("API Rating: ",ratings.size());

        ratings.forEach(x->{
          //Getting for each rating hotel Info From Hotel-Service
          //Hotel hotel = restTemplate.getForEntity(hotelUrl+x.getHotelId(),Hotel.class).getBody();
          Hotel hotel = hotelService.getHotel(x.getHotelId());
          logger.info("hotel name :"+hotel.getHotelName());
          x.setHotel(hotel);
        });
        user.setRatings(ratings);
        return user;
    }
}
