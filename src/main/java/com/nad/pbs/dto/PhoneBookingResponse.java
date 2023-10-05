package com.nad.pbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhoneBookingResponse {
    private ErrorResponse errorResponse;
    private MobilePhone mobilePhone;
}
