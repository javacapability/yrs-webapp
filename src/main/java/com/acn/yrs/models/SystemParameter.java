package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "SYSTEMPARAMETERS")
public class SystemParameter extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private int id;

	@Column(name = "dscp", nullable = false)
	@Expose
	private String dscp;

	@Column(name = "val", nullable = false)
	@Expose
	private String val;

	@Column(name = "datatype", nullable = false)
	@Expose
	private String datatype;

	@Column(name = "active", nullable = false)
	@Expose
	private int active;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dscp
	 */
	public String getDscp() {
		return dscp;
	}

	/**
	 * @param dscp the dscp to set
	 */
	public void setDscp(String dscp) {
		this.dscp = dscp;
	}

	/**
	 * @return the val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
}
