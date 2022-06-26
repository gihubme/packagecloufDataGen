package org.nnn4eu.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MAdress {

    private Long addressId;

    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String fullName;

    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String street;

    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String city;

    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String zipCode;

    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String country;

    @Size(message = "Max 255 characters", max = 255)
    private String additional;

}
