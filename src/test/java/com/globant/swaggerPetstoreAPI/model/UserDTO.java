package com.globant.swaggerPetstoreAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotations to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with all fields
@JsonIgnoreProperties (ignoreUnknown = true) // Ignore any unknown properties in JSON
public class UserDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
