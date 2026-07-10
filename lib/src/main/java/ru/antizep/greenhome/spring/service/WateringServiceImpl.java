package ru.antizep.greenhome.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.antizep.greenhome.spring.dto.WateringRequest;
import ru.antizep.greenhome.spring.infrastructure.SerialGateway;

@Service
public class WateringServiceImpl implements WateringService {

	private static final Logger LOG = LoggerFactory.getLogger(WateringServiceImpl.class);
	private final SerialGateway serialGateway;

	@Autowired
	public WateringServiceImpl(SerialGateway serialGateway) {
		this.serialGateway = serialGateway;
	}

	@Override
	public void triggerWatering(WateringRequest request) {
		String command = "START_WATERING:" + request.zone() + ":" + request.durationMinutes();
		
		serialGateway.sendComand(command);

		LOG.info("Инициализирован полив зоны: " + request.zone() + " на " + request.durationMinutes() + " минут.");
	}

}
