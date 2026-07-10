package ru.antizep.greenhome.spring.service;

import ru.antizep.greenhome.spring.dto.WateringRequest;

public interface WateringService {

	public void triggerWatering(WateringRequest request);

}
