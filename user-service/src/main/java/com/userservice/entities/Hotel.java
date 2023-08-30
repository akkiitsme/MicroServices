package com.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
    private Date CreatedOn = new Date();
    private int status=1;
}
