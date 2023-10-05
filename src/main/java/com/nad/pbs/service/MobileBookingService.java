package com.nad.pbs.service;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nad.pbs.dto.ErrorResponse;
import com.nad.pbs.dto.PhoneBookingResponse;
import com.nad.pbs.entity.MobilePhone;
import com.nad.pbs.enums.Errors;
import com.nad.pbs.fonoapi.FonoApiFacade;
import com.nad.pbs.repository.MobilePhoneRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Business class for booking or returning Mobile Phones
 * @author Nadeem
 *
 */
@Service
@AllArgsConstructor
public class MobileBookingService {

	private final MobilePhoneRepository mobilePhoneRepository;
	private final FonoApiFacade fonoApiFacade;

	/**
	 * Method to book th phone
	 * @param brand
	 * @param model
	 * @param user
	 * @return {@link PhoneBookingResponse}
	 */
	public Mono<PhoneBookingResponse> bookPhone(String brand, String model, String user) {
		return Mono.defer(() -> {
			MobilePhone phone = mobilePhoneRepository.findMobilePhoneByBrandAndModel(brand, model).stream()
					.filter(MobilePhone::isAvailable).findFirst().orElse(null);

			if (phone == null) {
				phone = mobilePhoneRepository.findMobilePhoneByBrandAndModel(brand, model).stream()
						.filter(ph -> !ph.isAvailable()).findFirst().orElse(null);
				if (phone == null) {
					return Mono
							.just(PhoneBookingResponse.builder()
									.errorResponse(ErrorResponse.builder()
											.errorMessage(Errors.MOBILE_IS_NOT_IN_INVENTORY.getMessage())
											.errorCode(Errors.MOBILE_IS_NOT_IN_INVENTORY.getId()).build())
									.build());
				} else {
					return Mono
							.just(PhoneBookingResponse.builder()
									.errorResponse(ErrorResponse.builder()
											.errorMessage(Errors.MOBILE_IS_ALREADY_BOOKED.getMessage())
											.errorCode(Errors.MOBILE_IS_ALREADY_BOOKED.getId()).build())
									.build());
				}
			}

			fonoApiFacade.getPhoneDetails(phone);
			phone.setAvailable(false);
			phone.setBookedBy(user);
			phone.setBookingDate(LocalDate.now());
			saveMobilePhone(phone);
			return Mono.just(PhoneBookingResponse.builder().mobilePhone(mapMobilePhoneEntityToDTO(phone)).build());

		});
	}

	/**
	 * Method to return the booked mobile 
	 * @param brand
	 * @param model
	 * @param user
	 * @return {@link PhoneBookingResponse} 
	 */
	public Mono<PhoneBookingResponse> returnPhone(String brand, String model, String user) {
		return Mono.defer(() -> {
			MobilePhone phone = mobilePhoneRepository.findMobilePhoneByBrandAndModel(brand, model).stream()
					.filter(ph -> !ph.isAvailable()).findFirst().orElse(null);

			if (phone == null) {
				phone = mobilePhoneRepository.findMobilePhoneByBrandAndModel(brand, model).stream()
						.filter(MobilePhone::isAvailable).findFirst().orElse(null);
				if (phone == null) {
					return Mono
							.just(PhoneBookingResponse.builder()
									.errorResponse(ErrorResponse.builder()
											.errorMessage(Errors.MOBILE_IS_NOT_IN_INVENTORY.getMessage())
											.errorCode(Errors.MOBILE_IS_NOT_IN_INVENTORY.getId()).build())
									.build());
				} else {
					return Mono
							.just(PhoneBookingResponse.builder()
									.errorResponse(ErrorResponse.builder()
											.errorMessage(Errors.MOBILE_IS_NOT_BEEN_BOOKED.getMessage())
											.errorCode(Errors.MOBILE_IS_NOT_BEEN_BOOKED.getId()).build())
									.build());
				}
			}

			if (!phone.isAvailable() && !Objects.equals(phone.getBookedBy(), user)) {
				return Mono
						.just(PhoneBookingResponse.builder()
								.errorResponse(ErrorResponse.builder()
										.errorMessage(Errors.MOBILE_IS_NOT_BOOKED_BY_USER.getMessage())
										.errorCode(Errors.MOBILE_IS_NOT_BOOKED_BY_USER.getId()).build())
								.build());
			}

			fonoApiFacade.getPhoneDetails(phone);
			phone.setAvailable(true);
			phone.setBookedBy(user);
			phone.setBookingDate(LocalDate.now());
			saveMobilePhone(phone);
			return Mono.just(PhoneBookingResponse.builder().mobilePhone(mapMobilePhoneEntityToDTO(phone)).build());
		});
	}

	private void saveMobilePhone(MobilePhone mobilePhone) {
		mobilePhoneRepository.save(mobilePhone);
	}

	private com.nad.pbs.dto.MobilePhone mapMobilePhoneEntityToDTO(MobilePhone mobilePhone) {
		com.nad.pbs.dto.MobilePhone phone = new com.nad.pbs.dto.MobilePhone();
		BeanUtils.copyProperties(mobilePhone, phone);
		return phone;
	}
}
