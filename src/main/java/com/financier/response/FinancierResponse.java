package com.financier.response;

import org.springframework.stereotype.Component;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Component
public class FinancierResponse extends BaseResponse {

	private FinancierDto financierDetails;

	public FinancierDto getFinancierDetails() {
		return financierDetails;
	}

	public void setFinancierDetails(FinancierDto financierDetails) {
		this.financierDetails = financierDetails;
	}

	@Override
	public String toString() {
		return "FinancierResponse [financierDetails=" + financierDetails + "]";
	}

}
