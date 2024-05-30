package org.springboot.busreservationapi.model;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private int userAge;
    @Column(nullable = false)
    private String userGender;
    @Column(nullable = false,unique = true)
    private long userPhoneNo;
    @Column(nullable = false,unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String userPassword;


}
