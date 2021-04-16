package org.goforfood.models.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 32, nullable = false)
    private Long id;

    @Column(length = 64)
    private String street;

    @Column(length = 8)
    private Integer doorNumber;

    @Column(length = 4)
    private String box;

    @Column(length = 16)
    private String postalCode;

    @Column(length = 32)
    private String city;

    @Column(length = 32)
    private String country;
}
