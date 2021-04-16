package org.goforfood.models.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.goforfood.models.data.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 32, nullable = false)
    private Long id;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 32, nullable = false)
    private String password;
    
    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 32, nullable = false)
    private String lastName;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @ManyToOne
    private Address address;
}
