package ru.antizep.greenhome.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ObjectInputFilter.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
public class WateringWalkingSkeletonTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockitoBean
	private SerialGateway serialGateway;
	
	@Test
	void shouldTriggerWateringThroughEntireSystem(CapturedOutput out) {
		WateringRequest request = new WateringRequest("ZONE_1", 15);
		
		mockMvc.perform( 
				post("/api/v1/watering")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
				)
		.andExpect(status().isOk());
		
		verify(serialGateway).sendComand("START_WATERING:ZONE_1:15");
		assertThat(out.getOut()).contains("Инициализирован полив зоны: ZONE_1 на 15 минут.");
	}
}
