package com.development.app.BasketballService.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private Long firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private Long lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Long dateOfBirth;

    @Column(name = "EMAIL", nullable = false)
    private Long email;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private Long phoneNumber;

    @Column(name = "USERNAME", nullable = false)
    private String username;



}
