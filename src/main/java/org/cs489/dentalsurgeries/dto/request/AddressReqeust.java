package org.cs489.dentalsurgeries.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record AddressReqeust(

        @Positive(message = "Number must be positive")
        int no,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Zip code is required")
        @Pattern(regexp = "\\d{5}", message = "Zip code must be 5 digits")
        String zip
) {
}
