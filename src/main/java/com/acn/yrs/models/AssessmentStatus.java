package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ASSESSMENTSSTATUS")
public class AssessmentStatus extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@Column(name = "assessmentstatus", nullable = false)
	@Expose
	private String assessmentstatus;

	public AssessmentStatus() {
	}

	public AssessmentStatus(int assessmentStatus) {
		// TODO Auto-generated constructor stub
		this.id = new Integer(assessmentStatus);
	}

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
	 * @return the assessmentstatus
	 */
	public String getAssessmentstatus() {
		return assessmentstatus;
	}

	/**
	 * @param assessmentstatus the assessmentstatus to set
	 */
	public void setAssessmentstatus(String assessmentstatus) {
		this.assessmentstatus = assessmentstatus;
	}
}
