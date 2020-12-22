package com.example.householdappliance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdappliance.dto.HouseApplicaceDto;
import com.example.householdappliance.dto.SearchHouseApplicaceDto;
import com.example.householdappliance.exception.BadRequestException;
import com.example.householdappliance.exception.ResourceNotFoundException;
import com.example.householdappliance.response.ApiResponse;
import com.example.householdappliance.service.HouseApplianceService;


@RestController
@RequestMapping("/houseappliance/api/v1")
public class HouseAppliaanceController {
	
	@Autowired
	private HouseApplianceService houseApplianceService;
	
	@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HouseApplicaceDto>> getHouseApplianceById(@PathVariable(value = "id") Long id)
      throws ResourceNotFoundException {
		return new ResponseEntity<ApiResponse<HouseApplicaceDto>>(houseApplianceService.findById(id), HttpStatus.OK);
    }
	
	@GetMapping("/list")
	ResponseEntity<ApiResponse<List<HouseApplicaceDto>>> filter(SearchHouseApplicaceDto searchHouseApplicaceDto) {
	    return new ResponseEntity<ApiResponse<List<HouseApplicaceDto>>>(houseApplianceService.findAll(searchHouseApplicaceDto), HttpStatus.OK);
	}

	@PostMapping
    public ResponseEntity<ApiResponse<HouseApplicaceDto>> createOrUpdateHouseAppliance(@RequestBody @Validated HouseApplicaceDto houseApplicaceDto) throws BadRequestException {
        return new ResponseEntity<ApiResponse<HouseApplicaceDto>>(houseApplianceService.createOrUpdate(houseApplicaceDto), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteHouseApplianceById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<ApiResponse<Boolean>>(houseApplianceService.deleteById(id), HttpStatus.OK);
    }

}