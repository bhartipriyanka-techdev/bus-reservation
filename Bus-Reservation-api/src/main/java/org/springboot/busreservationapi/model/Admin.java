package org.springboot.busreservationapi.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false, unique = true)
    private String adminEmailId;

    @Column(nullable = false, unique = true)
    private long adminPhoneNo;

    @Column(nullable = false, unique = true)
    private String adminGstNo;

    @Column(nullable = false)
    private String travelName;

    @Column(nullable = false)
    private String adminPassword;

    @OneToMany(mappedBy = "admin")
    private List<Bus> buses;
}
