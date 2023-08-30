package com.hotelservice.hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "lu_hotel")
public class Hotel {
    @Id
    private String hotelId;
    private String hotelName;
    private String location;
    private String about;

    private Date CreatedOn = new Date();
    private int status=1;


}
