package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RISKASSESSMENTS")
public class RiskAssessment extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "riskassessment")
	private String riskAssessment;

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
	 * @return the riskAssessment
	 */
	public String getRiskAssessment() {
		return riskAssessment;
	}

	/**
	 * @param riskAssessment the riskAssessment to set
	 */
	public void setRiskAssessment(String riskAssessment) {
		this.riskAssessment = riskAssessment;
	}

}
