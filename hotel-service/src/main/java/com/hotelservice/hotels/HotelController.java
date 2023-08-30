package com.hotelservice.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/createHotel")
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        hotel = hotelRepository.save(hotel);
        System.out.println("Hotel :"+hotel);
        return new ResponseEntity<Hotel>(hotel , HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{Id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String Id){
        return new ResponseEntity<Hotel>(hotelRepository.findByHotelId(Id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelList(){
        return new ResponseEntity<List<Hotel>>(hotelRepository.findAll(),HttpStatus.OK);
    }

}
