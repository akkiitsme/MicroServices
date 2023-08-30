package com.hotelservice.hotels;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @GetMapping
    public ResponseEntity<Map<String,Object>> getStaff(){
        Map map =  new HashMap<String,Object>();
        map.put("Ramu","Manager");
        map.put("Seeta","Receptionist");
        map.put("Raju","HouseKeeper");
        map.put("Baburao","Chef");
        map.put("Shyam","Server");
        map.put("Geeta","HouseKeeper");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
