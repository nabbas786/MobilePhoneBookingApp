package com.nad.pbs.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Errors {
    MOBILE_IS_NOT_IN_INVENTORY(1,"Mobile is not available in inventory"),
    MOBILE_IS_ALREADY_BOOKED(2,"Mobile is Already Booked"),
    MOBILE_IS_NOT_BEEN_BOOKED(3,"Cannot return as Mobile is not booked"),
    MOBILE_IS_NOT_BOOKED_BY_USER(4,"Mobile is not booked by this user");

    private int id;
    private String message;

    Errors(int i, String message) {
        this.id=id;
        this.message=message;
    }



}
