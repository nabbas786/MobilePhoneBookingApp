package com.nad.pbs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nad.pbs.dto.FonoApiResponse;

@RestController
@RequestMapping("fonoapi")
public class FonoApiFallback {
	
	@GetMapping("/fallback")
	public FonoApiResponse fallback() {
		return FonoApiResponse.builder()._2GBand("2.2ghz")._3GBand("3.2ghz")._4GBand("4.2ghz").technology("Android")
				.build();
	}

}
