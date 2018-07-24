package com.financier.response;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinancierDto {

	private String id;
	private String financierId;
	private String financierName;
	private String financierLocation;

	public FinancierDto(String id, String financierId, String financierName, String financierLocation) {
		super();
		this.id = id;
		this.financierId = financierId;
		this.financierName = financierName;
		this.financierLocation = financierLocation;
	}

	public FinancierDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFinancierId() {
		return financierId;
	}

	public void setFinancierId(String financierId) {
		this.financierId = financierId;
	}

	public String getFinancierName() {
		return financierName;
	}

	public void setFinancierName(String financierName) {
		this.financierName = financierName;
	}

	public String getFinancierLocation() {
		return financierLocation;
	}

	public void setFinancierLocation(String financierLocation) {
		this.financierLocation = financierLocation;
	}

	@Override
	public String toString() {
		return "FinancierDto [id=" + id + ", financierId=" + financierId + ", financierName=" + financierName
				+ ", financierLocation=" + financierLocation + "]";
	}

}
