package com.example.householdappliance.service.impl;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.householdappliance.constant.Constant;
import com.example.householdappliance.dto.HouseApplicaceDto;
import com.example.householdappliance.dto.SearchHouseApplicaceDto;
import com.example.householdappliance.enumtype.Status;
import com.example.householdappliance.exception.BadRequestException;
import com.example.householdappliance.exception.ResourceNotFoundException;
import com.example.householdappliance.model.HouseAppliance;
import com.example.householdappliance.repository.HouseApplianceRepository;
import com.example.householdappliance.response.ApiResponse;
import com.example.householdappliance.service.HouseApplianceService;
import com.example.householdappliance.util.OffsetDateTimeUtilis;

@Service
public class HouseApplianceServiceImpl implements HouseApplianceService {

	@Autowired
	private HouseApplianceRepository houseApplianceRepository;

	@Override
	public ApiResponse<HouseApplicaceDto> createOrUpdate(HouseApplicaceDto houseApplianceDto) throws BadRequestException {
		HouseAppliance houseApplicance = new HouseAppliance();
		Optional<HouseAppliance> optionalHouseApplicance = Optional.ofNullable(houseApplianceDto.getId()).isPresent() ? houseApplianceRepository.findById(houseApplianceDto.getId()) : Optional.empty();
        if(optionalHouseApplicance.isPresent() ) {
        	houseApplicance = optionalHouseApplicance.get();
            BeanUtils.copyProperties(houseApplianceDto, houseApplicance);
            houseApplicance = houseApplianceRepository.save(houseApplicance);
            
        } else {
        	boolean houseApplianceExist = houseApplianceRepository.existsBySerialNumberOrBrandOrModel(houseApplianceDto.getSerialNumber(), houseApplianceDto.getBrand(), houseApplicance.getModel());
        	if(houseApplianceExist) {
        		throw new BadRequestException(Constant.ITEM_ALREADY_EXISTS);
        	}
        	BeanUtils.copyProperties(houseApplianceDto, houseApplicance);
        	houseApplicance = houseApplianceRepository.save(houseApplicance);
        	BeanUtils.copyProperties(houseApplicance, houseApplianceDto);
        }
        return new ApiResponse<HouseApplicaceDto>(Constant.OK, Constant.SUCCESS, houseApplianceDto);
	}

	@Override
	public ApiResponse<List<HouseApplicaceDto>> findAll(SearchHouseApplicaceDto searchHouseApplicaceDto) {

	List<HouseAppliance> houseApplicaceList = houseApplianceRepository.findAll(new Specification<HouseAppliance>() {
	    @Override
	    public Predicate toPredicate(Root<HouseAppliance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
	    List<Predicate> predicates = new LinkedList<>();
	    
	    if (StringUtils.isNotBlank(searchHouseApplicaceDto.getSerialNumber())) {
            predicates.add(cb.like(cb.lower(root.get("serialNumber")), "%" + searchHouseApplicaceDto.getSerialNumber().toLowerCase() + "%"));
        }
        
        if (StringUtils.isNotBlank(searchHouseApplicaceDto.getModel())) {
            predicates.add(cb.like(cb.lower(root.get("model")), "%" + searchHouseApplicaceDto.getModel().toLowerCase() + "%"));
        }
        
        if (StringUtils.isNotBlank(searchHouseApplicaceDto.getBrand())) {
            predicates.add(cb.like(cb.lower(root.get("brand")), "%" + searchHouseApplicaceDto.getBrand().toLowerCase() + "%"));
        }
        
        if (StringUtils.isNotBlank(searchHouseApplicaceDto.getStatus())) {
            predicates.add(cb.equal(root.get("status"), Status.fromString(searchHouseApplicaceDto.getStatus())));
        }
        
        if (StringUtils.isNotBlank(searchHouseApplicaceDto.getBoughtDate())) {
        	OffsetDateTime boughtDate = OffsetDateTimeUtilis.convertToOffsetDateTime(searchHouseApplicaceDto.getBoughtDate());
            predicates.add(cb.equal(root.get("boughtDate"),boughtDate));
        }
	    
	    return criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0]))).distinct(true).getRestriction();
	    }
	});
	List<HouseApplicaceDto> houseApplicaceDtoList = new ArrayList<HouseApplicaceDto>();
		for(HouseAppliance houseApplicance : houseApplicaceList) {
			HouseApplicaceDto houseApplicaceDto = new HouseApplicaceDto();
			BeanUtils.copyProperties(houseApplicance, houseApplicaceDto);
			houseApplicaceDtoList.add(houseApplicaceDto);
		}
		return new ApiResponse<List<HouseApplicaceDto>>(Constant.OK, Constant.SUCCESS, houseApplicaceDtoList);
	}

	@Override
	public ApiResponse<Boolean> deleteById(Long id) throws ResourceNotFoundException {
		Optional<HouseAppliance> optHouseApplicance = houseApplianceRepository.findById(id);
		if(optHouseApplicance.isPresent()) {
			houseApplianceRepository.delete(optHouseApplicance.get());	
		} else {
			throw new ResourceNotFoundException(Constant.ITEM_NOT_FOUND);
		}
		return new ApiResponse<Boolean>(Constant.OK, Constant.SUCCESS, true);
	}

	@Override
	public ApiResponse<HouseApplicaceDto> findById(Long id) throws ResourceNotFoundException {
		Optional<HouseAppliance> optionalHouseApplicance = houseApplianceRepository.findById(id);
		HouseApplicaceDto houseApplianceDto = new HouseApplicaceDto();
        if(optionalHouseApplicance.isPresent() ) {
            BeanUtils.copyProperties(optionalHouseApplicance.get(), houseApplianceDto);
        }else {
			throw new ResourceNotFoundException(Constant.ITEM_NOT_FOUND);
		}
        return new ApiResponse<HouseApplicaceDto>(Constant.OK, Constant.SUCCESS, houseApplianceDto);
	}
}
