package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "PRODUCTASSIGNMENT")
public class ProductAssignment extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@Column(name = "productname", nullable = false)
	@Expose
	private String productName;


	@Column(name = "productdscp", nullable = false)
	@Expose
	private String productDscp;

	@Column(name = "riskassignment", nullable = false)
	@Expose
	private String riskAssignment;

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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDscp
	 */
	public String getProductDscp() {
		return productDscp;
	}

	/**
	 * @param productDscp the productDscp to set
	 */
	public void setProductDscp(String productDscp) {
		this.productDscp = productDscp;
	}

	/**
	 * @return the riskAssignment
	 */
	public String getRiskAssignment() {
		return riskAssignment;
	}

	/**
	 * @param riskAssignment the riskAssignment to set
	 */
	public void setRiskAssignment(String riskAssignment) {
		this.riskAssignment = riskAssignment;
	}
}
