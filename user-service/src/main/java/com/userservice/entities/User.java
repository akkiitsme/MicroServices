package com.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "trx_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Date createdOn= new Date();
    private int status=1;
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
