package com.example.householdappliance.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.householdappliance.enumtype.Status;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "HOUSE_APPLIANCE")
public class HouseAppliance extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5476482464399669287L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	@Column(name = "BARND")
	private String brand;
	@Column(name = "MODEL")
	private String model;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;
	@Column(name = "BOUGHT_DATE")
	private OffsetDateTime boughtDate;

}