package com.driver.model;

import com.driver.services.impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tripbooking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripBookingId;
    private String fromlocation;
    private int distanceInKm;
    private String toLocation;
    private TripStatus status;
    private double bill;

    @ManyToOne
   public Customer customer;

    @ManyToOne
    private Driver driver;


    public TripBooking(String fromLocation, String toLocation, int distanceInKm) {
        this.fromlocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
    }
}