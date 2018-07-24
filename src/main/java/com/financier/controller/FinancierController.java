package com.financier.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financier.response.FinancierDto;
import com.financier.response.FinancierResponse;
import com.financier.response.FinanciersResponse;
import com.financier.service.IFinancierService;
import com.financier.util.FinancierApiResponses;
import com.financier.util.ResultCode;

import io.swagger.annotations.ApiOperation;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@RestController // (value="/financier")
@RequestMapping(value = "/financier")
public class FinancierController {

	private static final Logger logger = LoggerFactory.getLogger(FinancierController.class);

	@Autowired
	IFinancierService financierService;

	@FinancierApiResponses
	@ApiOperation(value = "Responsible for Creating Financier", notes = "Id will be auto generated", nickname = "financierRegistration")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FinancierResponse> createFinancier(@RequestBody FinancierDto dto) {

		logger.debug("createFinancier Start ,  \n Request is : ", dto);

		FinancierDto responseDto = financierService.create(dto);

		FinancierResponse response = new FinancierResponse();

		if (responseDto != null) {
			response.setStatus(ResultCode.SUCCESS_ADD_FINANCIIER.getStatus());
			response.setMessage(ResultCode.SUCCESS_ADD_FINANCIIER.getMessage());
			response.setCode(ResultCode.SUCCESS_ADD_FINANCIIER.getCode());
			response.setFinancierDetails(responseDto);
			return new ResponseEntity<FinancierResponse>(response, HttpStatus.CREATED);
		} else {
			response.setStatus(ResultCode.FAILURE.getStatus());
			response.setMessage(ResultCode.FAILURE.getMessage());
			response.setCode(ResultCode.FAILURE.getCode());
			response.setFinancierDetails(responseDto);
			return new ResponseEntity<FinancierResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@FinancierApiResponses
	@ApiOperation(value = "Responsible for retrieving All Financiers")
	@GetMapping(value = "/getAll")
	public ResponseEntity<FinanciersResponse> getAll() {

		logger.debug("getAllFinanciers Start ::");

		List<FinancierDto> responseDtos = financierService.getAll();
		FinanciersResponse response = new FinanciersResponse();

		if (!responseDtos.isEmpty()) {
			response.setStatus(ResultCode.SUCCESS_GET_FINANCIERS.getStatus());
			response.setMessage(ResultCode.SUCCESS_GET_FINANCIERS.getMessage());
			response.setCode(ResultCode.SUCCESS_GET_FINANCIERS.getCode());
			response.setFinanciersDetails(responseDtos);
			return new ResponseEntity<FinanciersResponse>(response, HttpStatus.OK);
		} else {
			response.setStatus(ResultCode.FAILURE.getStatus());
			response.setMessage(ResultCode.FAILURE.getMessage());
			response.setCode(ResultCode.FAILURE.getCode());
			response.setFinanciersDetails(responseDtos);
			return new ResponseEntity<FinanciersResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@FinancierApiResponses
	@ApiOperation(value = "Responsible to retrieve Specifeed Financier Details")
	@GetMapping(value = "/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FinancierDto> financierById(@PathVariable("id") String financierId) throws Exception {

		System.out.println("getFinancier Started...");
		logger.debug("getFinancier Start :: for financierId \t {} \n", financierId);
		FinancierDto response = new FinancierDto();
		response = financierService.findFinancier(financierId);
		System.out.println("getFinancier Dto..." + response.toString());
		return new ResponseEntity<FinancierDto>(response, HttpStatus.CREATED);
	}

	@FinancierApiResponses
	@ApiOperation(value = "Responsible to delete Specified Financier")
	@DeleteMapping(value = "/{id}/")
	public void deleteFinancierById(@PathVariable("id") String financierId) {

		logger.debug("deleteFinancier Start :: for financierId{} \n", financierId);
		financierService.deleteFinancier(financierId);

	}

	@FinancierApiResponses
	@ApiOperation(value = "Responsible update Financier")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FinancierDto> updateFinancier(@RequestBody FinancierDto dto) throws Exception {

		logger.debug("updateFinancier Start ,  \n Request is : \n", dto);

		FinancierDto updatedFinancier = financierService.updateFinancier(dto);

		return new ResponseEntity<FinancierDto>(updatedFinancier, HttpStatus.OK);

	}
}
