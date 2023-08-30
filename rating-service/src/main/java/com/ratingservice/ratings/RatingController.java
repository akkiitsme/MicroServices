package com.ratingservice.ratings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        rating.setRatingId(UUID.randomUUID().toString());
        return new ResponseEntity<>(ratingRepository.save(rating), HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String Id){
        return new ResponseEntity<>(ratingRepository.findByRatingId(Id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return new ResponseEntity<>(ratingRepository.findAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingRepository.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingRepository.findByHotelId(hotelId),HttpStatus.OK);
    }

}
