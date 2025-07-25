package com.globant.swaggerPetstoreAPI.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotations to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with all fields
@JsonIgnoreProperties (ignoreUnknown = true) // Ignore any unknown properties in JSON
@Builder
public class UserDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
