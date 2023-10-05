package com.nad.pbs.fonoapi;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nad.pbs.dto.FonoApiResponse;
import com.nad.pbs.entity.MobilePhone;

import lombok.RequiredArgsConstructor;

/**
 * Facade to Get the data from FONO API and populate default response if the API
 * is not present
 * 
 * @author Nadeem
 *
 */
@Component
@RequiredArgsConstructor

public class FonoApiFacade {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	@Value("${fono.api.url}")
	private String FONO_API_URL;
	@Value("${fono.api.token}")
	private String API_TOKEN;

	/**
	 * Method to get the Fono API response if api throws exception or fails to get response Circuit Breaker will get default response
	 * 
	 * @param model
	 * @param brand
	 * @return
	 */
	private FonoApiResponse getPhoneDetails(String model, String brand) {

		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(
				() -> restTemplate.getForObject(FONO_API_URL, FonoApiResponse.class, model, brand, API_TOKEN),
				throwable -> FonoApiResponse.builder()._2GBand("2.2ghz")._3GBand("3.2ghz")._4GBand("4.2ghz")
						.technology("Android").build());

	}

	/**
	 * Method to Enrich the MobilePhone with Fono API
	 * 
	 * @param mobilePhone
	 */

	public void getPhoneDetails(MobilePhone mobilePhone) {
		FonoApiResponse fonoApiResponse = this.getPhoneDetails(mobilePhone.getModel(), mobilePhone.getBrand());
		if (Objects.nonNull(fonoApiResponse)) {
			mobilePhone.setTechnology(fonoApiResponse.getTechnology());
			mobilePhone.set_2GBand(fonoApiResponse.get_2GBand());
			mobilePhone.set_3GBand(fonoApiResponse.get_3GBand());
			mobilePhone.set_4GBand(fonoApiResponse.get_4GBand());
		}
	}

}
