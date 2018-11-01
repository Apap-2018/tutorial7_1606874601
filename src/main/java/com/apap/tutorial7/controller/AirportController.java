package com.apap.tutorial7.controller;

import java.util.Optional;

import com.apap.tutorial7.rest.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AirportController
 */
@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    RestTemplate restTemplate;
    
    @Bean
    public RestTemplate restAirport() {
    	return new RestTemplate();
    }
    
	@GetMapping(value = "/{city}")
    public String getStatus(@PathVariable("city") String city) throws Exception {
    	String path = Setting.airportUrl + city + "&country=ID";
    	return restTemplate.getForEntity(path, String.class).getBody();
    }
    
}