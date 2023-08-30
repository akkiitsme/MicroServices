package com.ratingservice.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

    Rating findByRatingId(String id);

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
