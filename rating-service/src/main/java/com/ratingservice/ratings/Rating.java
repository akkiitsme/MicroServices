package com.ratingservice.ratings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lu_rating")
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private String ratings;
    private String feedback;
    private Date createdOn = new Date();
    private int status =1;

}