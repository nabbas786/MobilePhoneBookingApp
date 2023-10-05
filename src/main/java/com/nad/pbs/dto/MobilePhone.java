package com.nad.pbs.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobilePhone {
    private String brand;
    private String model;
    private boolean available;
    private String bookedBy;
    private LocalDate bookingDate;
    private String technology;
    private String _2GBand;
    private String _3GBand;
    private String _4GBand;

    public MobilePhone(String model,String brand) {
        this.model = model;
        this.available = true;
        this.bookedBy = null;
        this.bookingDate = null;
    }

}
