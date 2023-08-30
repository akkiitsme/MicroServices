package com.userservice.entities;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.userservice.exception.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    int retryCount = 1;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser  = userService.saveUser(user);
        return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
    }
    @GetMapping("/{Id}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    //@Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "ratingHotelLimiter",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<?> getUser(@PathVariable String Id){
        logger.info("RetryCount: "+retryCount);
        retryCount++;
        User user = userService.getUser(Id);
        if(user!=null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.ok(ApiResponse.builder().message("User Not found")
                    .success(false).status(HttpStatus.NOT_FOUND).build());
    }
    public ResponseEntity<?> ratingHotelFallBack(String Id,Exception ex){
        logger.info("Service is Down");
        User user  = User.builder().username("dummy@abc.com")
                .userId("1234").firstName("dummy").lastName("singh")
                .password("*****").ratings(new ArrayList<>()).build();
        return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public List<User> getAllUser(){
      return userRepository.findAll();
    }
}
