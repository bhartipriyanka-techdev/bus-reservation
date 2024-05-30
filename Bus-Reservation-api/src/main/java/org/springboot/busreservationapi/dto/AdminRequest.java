package org.springboot.busreservationapi.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
   @NotBlank(message = "Name is mandatory")
    private String adminName;

   @Email
   @NotBlank(message = "Email Id is mandatory")
   private String adminEmailId;

  //@NotBlank
 // @Pattern(regexp = "\\d{10}", message = "Phone No must be a 10-digit number")
  private long adminPhoneNo;

  @NotBlank(message = "GST No is mandatory")
  @Size(min = 6, max = 20)
  private String adminGstNo;

  @NotBlank(message = "Travel Name is mandatory")
  private String travelName;

  @NotBlank
  @Size(min = 6, max = 20)
  //@Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Password must contain at least one special character")
  @Pattern(regexp = "^(?=.*[!$%]).+$", message = "Password must contain at least one special character")

  private String adminPassword;
}

