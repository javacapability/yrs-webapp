package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIABILITYTYPES")
public class LiabilityType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "liabilitytype", nullable = false)
	private String liabilityType;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the liabilityType
	 */
	public String getLiabilityType() {
		return liabilityType;
	}

	/**
	 * @param liabilityType the liabilityType to set
	 */
	public void setLiabilityType(String liabilityType) {
		this.liabilityType = liabilityType;
	}

}
