package org.springboot.busreservationapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusResponse {
    private int busId;

    private String busNumber;

    private String busName;

    @Column(nullable = false)
    private int capacity;

    private String departureCity;

    private String arrivalCity;

    private String departureTime;

    private String arrivalTime;

    private String busType;

    private boolean availability;

    private double price;
}
