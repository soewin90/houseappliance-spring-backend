package com.example.householdappliance.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchHouseApplicaceDto {

	private String serialNumber;
	
	private String brand;

	private String model;
	
	private String status;

	private String boughtDate;
}
