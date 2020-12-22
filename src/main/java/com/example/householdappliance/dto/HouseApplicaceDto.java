package com.example.householdappliance.dto;


import java.time.OffsetDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.householdappliance.custom.validator.EnumStatusLimit;
import com.example.householdappliance.enumtype.Status;
import com.example.householdappliance.util.OffsetDataTimeSerializer;
import com.example.householdappliance.util.OffsetDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class HouseApplicaceDto {

	private Long id;

	@NotEmpty(message = "SerialNumber can't be empty")
	@Size( min = 1, max = 20, message = "SerialNumber should be of 20 characters or less")
	private String serialNumber;
	

	@NotEmpty(message = "Brand can't be empty")
	@Size( min = 1, max = 20, message = "Brand should be of 20 characters or less")
	private String brand;

	@NotEmpty(message = "Model can't be empty")
	@Size( min = 1, max = 20, message = "Model should be of 20 characters or less")
	private String model;
	
	@NotNull
	@EnumStatusLimit(regexp = "OLD|NEW|SOLD")
	private Status status;
	
	@JsonSerialize(using = OffsetDataTimeSerializer.class)
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	@NotNull(message = "BoughtDate can't be empty")
	private OffsetDateTime boughtDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber.trim();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand.trim();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model.trim();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OffsetDateTime getBoughtDate() {
		return boughtDate;
	}

	public void setBoughtDate(OffsetDateTime boughtDate) {
		this.boughtDate = boughtDate;
	}
	
	
}
