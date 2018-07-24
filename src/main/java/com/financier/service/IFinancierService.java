package com.financier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financier.response.FinancierDto;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public interface IFinancierService {

	/**
	 * Method to create Financier
	 * 
	 * @param dto
	 * @return
	 */
	FinancierDto create(FinancierDto dto);

	/**
	 * @return
	 */
	List<FinancierDto> getAll();

	/**
	 * @param financierId
	 * @return
	 * @throws Exception
	 */
	FinancierDto findFinancier(String financierId) throws Exception;

	void deleteFinancier(String financierId);
	
	public FinancierDto updateFinancier(FinancierDto dto) throws Exception;
}
