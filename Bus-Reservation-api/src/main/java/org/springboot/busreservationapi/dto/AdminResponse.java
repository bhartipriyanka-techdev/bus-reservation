package org.springboot.busreservationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
    private int adminId;

    private String adminName;

    private String adminEmailId;

    private long adminPhoneNo;

    private String adminGstNo;

    private String travelName;

    private String adminPassword;

}
