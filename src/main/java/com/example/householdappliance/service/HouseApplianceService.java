package com.example.householdappliance.service;

import java.util.List;

import com.example.householdappliance.dto.HouseApplicaceDto;
import com.example.householdappliance.dto.SearchHouseApplicaceDto;
import com.example.householdappliance.exception.BadRequestException;
import com.example.householdappliance.exception.ResourceNotFoundException;
import com.example.householdappliance.response.ApiResponse;

public interface HouseApplianceService {
	

	ApiResponse<HouseApplicaceDto> createOrUpdate(HouseApplicaceDto houseApplicaceDto) throws BadRequestException;
	
	ApiResponse<List<HouseApplicaceDto>> findAll(SearchHouseApplicaceDto searchHouseApplicaceDto);
	
	ApiResponse<Boolean> deleteById(Long id) throws ResourceNotFoundException;

	ApiResponse<HouseApplicaceDto> findById(Long id) throws ResourceNotFoundException;
	

}
