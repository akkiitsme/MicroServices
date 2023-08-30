package com.userservice.externalservice;

import com.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/createRating")
    Rating createRating(Rating rating);

    @PutMapping("/updateRating/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);
}
