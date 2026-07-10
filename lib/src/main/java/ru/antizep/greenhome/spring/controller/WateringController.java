package ru.antizep.greenhome.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.antizep.greenhome.spring.dto.WateringRequest;
import ru.antizep.greenhome.spring.service.WateringService;

@RestController
@RequestMapping("/api/v1/watering")
public class WateringController {

	private final WateringService wateringService;
	
	@Autowired
	public WateringController(WateringService wateringService) {
		this.wateringService = wateringService;
	}

	@PostMapping
	public void startWatering(@RequestBody WateringRequest request) {
		wateringService.triggerWatering(request);		
	}
}
