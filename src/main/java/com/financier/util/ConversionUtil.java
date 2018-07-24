package com.financier.util;

import org.springframework.stereotype.Component;

import com.financier.entity.Financier;
import com.financier.response.FinancierDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Component
public class ConversionUtil {

	public FinancierDto getFinancierDto(Financier financier) {
		FinancierDto dto = new FinancierDto();

		dto.setId(financier.getId());
		dto.setFinancierId(financier.getFinancierId());
		dto.setFinancierName(financier.getFinancierName());
		dto.setFinancierLocation(financier.getFinancierLocation());
		return dto;
	}

	public Financier getFinancier(FinancierDto financierDto) {

		Financier model = new Financier();
		model.setId(financierDto.getId());
		model.setFinancierId(financierDto.getFinancierId());
		model.setFinancierName(financierDto.getFinancierName());
		model.setFinancierLocation(financierDto.getFinancierLocation());

		return model;
	}

}
