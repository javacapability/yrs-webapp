package com.acn.yrs.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIABILITIES")
public class Liability {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "assessmentid")
	private Assessment assessment;

	@ManyToOne
	@JoinColumn(name = "liabilitytype")
	private LiabilityType liabilityType;

	@Column(name = "liabilityamount", nullable = false)
	private BigDecimal liabilityamount;

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
	 * @return the assessment
	 */
	public Assessment getAssessment() {
		return assessment;
	}

	/**
	 * @param assessment the assessment to set
	 */
	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	/**
	 * @return the liabilityType
	 */
	public LiabilityType getLiabilityType() {
		return liabilityType;
	}

	/**
	 * @param liabilityType the liabilityType to set
	 */
	public void setLiabilityType(LiabilityType liabilityType) {
		this.liabilityType = liabilityType;
	}

	/**
	 * @return the liabilityamount
	 */
	public BigDecimal getLiabilityamount() {
		return liabilityamount;
	}

	/**
	 * @param liabilityamount the liabilityamount to set
	 */
	public void setLiabilityamount(BigDecimal liabilityamount) {
		this.liabilityamount = liabilityamount;
	}

}
