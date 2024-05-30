package org.springboot.busreservationapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int userId;
    private String userName;
    private int userAge;
    private String userGender;
    private long userPhoneNo;
    private String userEmail;
    private String userPassword;
}
