package com.example.householdappliance.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.householdappliance.model.HouseAppliance;

@Repository
public interface HouseApplianceRepository extends CrudRepository<HouseAppliance, Long> {
	
	 boolean existsBySerialNumber(String serialNumber);
	 
	 boolean existsByBrand(String brand);
	 
	 boolean existsByModel(String model);
	 
	 boolean existsBySerialNumberOrBrandOrModel(String serialNumber, String brand, String model);

	List<HouseAppliance> findAll(Specification<HouseAppliance> specification);
	
}
