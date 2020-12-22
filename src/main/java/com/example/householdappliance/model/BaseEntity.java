package com.example.householdappliance.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@MappedSuperclass
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class BaseEntity {
	
	@Column(name = "CREATED_BY", nullable= false)
	protected String createdBy;
	@Column(name = "CREATED_TIME", nullable= false)
	protected OffsetDateTime createdTime;
	@Column(name = "MODIFIED_TIME", nullable= false)
	protected OffsetDateTime modifiedTime;
	@Column(name = "MODIFIED_BY", nullable= false)
	protected String modifiedBy;
	
	@PrePersist
	public void preInsert() {
		this.createdBy = "SYSTEM";
		this.modifiedBy = "SYSTEM";
		this.createdTime = OffsetDateTime.now();
		this.modifiedTime = this.createdTime;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.modifiedBy = "SYSTEM";
		this.modifiedTime = OffsetDateTime.now();
	}
	

}
