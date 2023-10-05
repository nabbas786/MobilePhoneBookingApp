package com.nad.pbs.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity Class for Mobile phones
 * @author Nadeem
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MOBILE_PHONE")
public class MobilePhone {
	@Id
	private int phoneId;
    private String brand;
    private String model;
    private boolean available;
    private String bookedBy;
    private LocalDate bookingDate;
    private String technology;
    @Column(name = "band_2")
    private String _2GBand;
    @Column(name = "band_3")
    private String _3GBand;
    @Column(name = "band_4")
    private String _4GBand;

}
