package org.springboot.busreservationapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusRequest {

    @NotBlank (message = "Bus Number is mandatory")
    private String busNumber;

    @NotBlank (message = "Bus Name is mandatory")
    private String busName;

    @NotBlank (message = "Capacity is mandatory")
    private int capacity;

    @NotBlank (message = "Departure City is mandatory")
    private String departureCity;

    @NotBlank (message = "Arrival City is mandatory")
    private String arrivalCity;

    @NotBlank (message = "Departure Time is mandatory")
    private String departureTime;

    @NotBlank (message = "Arrival Time is mandatory")
    private String arrivalTime;

    @NotBlank (message = "Bus Type is mandatory")
    private String busType;

    @NotBlank (message = "availability is mandatory")
    private boolean availability;

    @NotBlank (message = "Price is mandatory")
    private double price;
}
