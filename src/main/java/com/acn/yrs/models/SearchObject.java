package com.acn.yrs.models;

public class SearchObject {

	private int id;
	private String listType;
	private String filter;



	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the searchFilter
	 */
	public String getFilter() {
		return filter;
	}
	/**
	 * @param searchFilter the searchFilter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
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

}
