package com.nad.pbs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nad.pbs.dto.PhoneBookingResponse;
import com.nad.pbs.service.MobileBookingService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * API to Book or return the Mobile Phone
 * @author Nadeem
 *
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PhoneBookingController {

    private final MobileBookingService mobileBookingService;

    /**
     * API to Book the phone
     * @param brand
     * @param model
     * @param user
     * @return {@link PhoneBookingResponse}
     */
    @PutMapping(value = "/book/{brand}/{model}/{user}")
    @ApiOperation("API to Book Phone")
    public Mono<ResponseEntity<PhoneBookingResponse>> bookPhone(
        @PathVariable(value="brand") String brand,
        @PathVariable(value="model") String model,
        @PathVariable(value="user") String user) {
        
        return mobileBookingService.bookPhone(brand, model, user)
            .map(response -> ResponseEntity.ok(response))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * API to return the booked phone
     * @param brand
     * @param model
     * @param user
     * @return {@link PhoneBookingResponse}
     */
    @PutMapping(value="/return/{brand}/{model}/{user}")
    @ApiOperation("API to Return the booked phone")
    public Mono<ResponseEntity<PhoneBookingResponse>> returnPhone(
        @PathVariable(value="brand") String brand,
        @PathVariable String model,
        @PathVariable String user) {
        
        return mobileBookingService.returnPhone(brand, model, user)
				.map(response -> ResponseEntity.ok(response))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
