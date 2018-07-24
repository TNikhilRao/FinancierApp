package com.financier.response;

import java.util.List;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinanciersResponse extends BaseResponse {

	private List<FinancierDto> financiersDetails;

	public List<FinancierDto> getFinanciersDetails() {
		return financiersDetails;
	}

	public void setFinanciersDetails(List<FinancierDto> financiersDetails) {
		this.financiersDetails = financiersDetails;
	}

	@Override
	public String toString() {
		return "FinanciersResponse [financiersDetails=" + financiersDetails + "]";
	}
}