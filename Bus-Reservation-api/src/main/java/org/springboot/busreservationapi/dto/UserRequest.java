package org.springboot.busreservationapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Name is mandatory")
    private String userName;

    @NotBlank(message = "Age is mandatory")
    private int userAge;

    @NotBlank(message = "Gender is mandatory")
    private String userGender;

    private long userPhoneNo;

    @NotBlank(message = "Email Id is mandatory")
    @Email
    private String userEmail;

    @NotBlank
    @Size(min = 6, max = 20)
    //@Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Password must contain at least one special character")
    @Pattern(regexp = "^(?=.*[!$%]).+$", message = "Password must contain at least one special character")

    private String userPassword;

}
