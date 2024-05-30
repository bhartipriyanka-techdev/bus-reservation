package org.springboot.busreservationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int busId;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false, unique = true)
    private String busName;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String arrivalCity;

    @Column(nullable = false)
    private String departureTime;

    @Column(nullable = false)
    private String arrivalTime;

    @Column(nullable = false)
    private String busType;

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;
}
