package com.example.householdappliance.rest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.householdappliance.HouseHoldApplianceApplication;
import com.example.householdappliance.constant.Constant;
import com.example.householdappliance.controller.HouseAppliaanceController;
import com.example.householdappliance.dto.HouseApplicaceDto;
import com.example.householdappliance.enumtype.Status;
import com.example.householdappliance.repository.HouseApplianceRepository;
import com.example.householdappliance.response.ApiResponse;
import com.example.householdappliance.service.HouseApplianceService;
import com.google.gson.Gson;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@ContextConfiguration(classes = { HouseHoldApplianceApplication.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HouseApplianceControllerIntegrationIT {
		
	@InjectMocks
	HouseAppliaanceController houseApplianceController;
	
	@Autowired
	private TestRestTemplate restTemplate;


    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
    @Test
	public void contextLoads() {
	}
	
	@Test
	public void list() {
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("serialNumber", "");
		urlParams.put("brand", "");
		urlParams.put("model", "");
		urlParams.put("status", "");
		urlParams.put("boughtDate", "");
		
        ResponseEntity<ApiResponse> result = restTemplate.getForEntity(getRootUrl() + "/houseappliance/api/v1/list", ApiResponse.class, urlParams );
        assertNotNull(result.getBody());
        List<HouseApplicaceDto> houseApplicaceDtos = (List<HouseApplicaceDto>) result.getBody().getResult();
        assertTrue(houseApplicaceDtos.size() > 0);
	}
	
	@Test
	public void deleteById() {
		int id = 1;
		ResponseEntity<ApiResponse> result = restTemplate.getForEntity(getRootUrl() + "/houseappliance/api/v1/" + id, ApiResponse.class);
        assertNotNull(result.getBody());
        restTemplate.delete(getRootUrl() + "/houseappliance/api/v1/" + id);
        result = restTemplate.getForEntity(getRootUrl() + "/houseappliance/api/v1/" + id, ApiResponse.class);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void createHouseAppliance() throws URISyntaxException {
		HouseApplicaceDto houseApplicaceDto = new HouseApplicaceDto();
		houseApplicaceDto.setBoughtDate(OffsetDateTime.now());
		houseApplicaceDto.setBrand("ABC");
		houseApplicaceDto.setModel("A1");
		houseApplicaceDto.setSerialNumber("A123");
		houseApplicaceDto.setStatus(Status.NEW);
		RequestEntity request = RequestEntity.post(new URI(getRootUrl() + "/houseappliance/api/v1/")).accept(MediaType.APPLICATION_JSON).body(houseApplicaceDto);
		ResponseEntity<ApiResponse> response = restTemplate.exchange(request, ApiResponse.class);
		assertNotNull(response);
		assertEquals(Constant.OK, response.getBody().getCode());
	}
	
	@Test
	public void updateHouseAppliance() throws URISyntaxException {
		HouseApplicaceDto houseApplicaceDto = new HouseApplicaceDto();
		houseApplicaceDto.setBoughtDate(OffsetDateTime.now());
		houseApplicaceDto.setId(1L);
		houseApplicaceDto.setBrand("XYZ");
		houseApplicaceDto.setModel("Z1");
		houseApplicaceDto.setSerialNumber("Z123");
		houseApplicaceDto.setStatus(Status.OLD);
		RequestEntity request = RequestEntity.post(new URI(getRootUrl() + "/houseappliance/api/v1/")).accept(MediaType.APPLICATION_JSON).body(houseApplicaceDto);
		ResponseEntity<ApiResponse> response = restTemplate.exchange(request, ApiResponse.class);
		assertNotNull(response);
		assertEquals(Constant.OK, response.getBody().getCode());
	}

}
