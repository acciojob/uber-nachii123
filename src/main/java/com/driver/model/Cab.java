package com.driver.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.annotation.HttpConstraint;

@Entity
@Table(name = "cab")
//@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cab{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private double perKmRate;
    private boolean available;

    public Integer getId() {
        return Id;
    }

    public double getPerKmRate() {
        return perKmRate;
    }

    public boolean getAvailable() {
        return available;
    }

    public Driver getDriver() {
        return driver;
    }

    @OneToOne
@JoinColumn(name = "driverId")
private Driver driver;
}