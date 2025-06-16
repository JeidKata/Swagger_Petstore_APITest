package com.globant.swaggerPetstoreAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class GetUserResponseDTO {

    @JsonProperty(value = "data")
    private UserDTO userData;

    @JsonProperty(value = "pets")
    private PetDTO petsData;
}
