package com.financier.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.financier.dao.IFinancierDao;
import com.financier.entity.Financier;
import com.financier.exception.FinancierNotFoundException;
import com.financier.response.FinancierDto;
import com.financier.service.IFinancierService;
import com.financier.util.ConversionUtil;
import com.financier.util.ResultCode;
import com.financier.util.SequenceDao;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Service
public class FinancierServiceImpl implements IFinancierService {

	private final Logger logger = LoggerFactory.getLogger(FinancierServiceImpl.class);

	@Autowired
	IFinancierDao financierDao;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	SequenceDao sequenceDao;

	@Autowired
	ConversionUtil conversionUtil;

	private static final String HOSTING_SEQ_KEY = "hosting";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.service.IFinancierService#create(com.test.entity.Financier)
	 */
	public FinancierDto create(FinancierDto financierDto) {

		Financier model = conversionUtil.getFinancier(financierDto);

		long id = sequenceDao.getNextSequenceId(HOSTING_SEQ_KEY);

		model.setId(String.valueOf(id));

		Financier savedFinancier = financierDao.save(model);

		FinancierDto dto = conversionUtil.getFinancierDto(savedFinancier);

		return dto;

	}

	public List<FinancierDto> getAll() {

		List<Financier> financiers = financierDao.findAll();
		List<FinancierDto> dtos = new ArrayList<FinancierDto>();

		for (Financier financier : financiers) {

			FinancierDto dto = new FinancierDto();

			dto = conversionUtil.getFinancierDto(financier);
			dtos.add(dto);

		}
		return dtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.service.IFinancierService#findFinancier(java.lang.Integer)
	 */
	public FinancierDto findFinancier(String financierId) throws Exception {

		/*
		 * Query query2 = new Query();
		 * query2.addCriteria(Criteria.where("financierId").is(financierId));
		 * 
		 * 
		 * System.out.println("query2 - " + query2.toString());
		 * 
		 * 
		 * Financier financier = mongoOperations.findOne(query2,Financier.class);
		 */

		// Financier financier1 = financierDao.findByFinancierId(financierId);
		FinancierDto dto = new FinancierDto();

		Financier financier = null;
		financier = financierDao.findByFinancierId(financierId);

		if (financier == null) {
			logger.error("Invalid Financier, financierId : {}", financierId);
			ResultCode error = ResultCode.FAILURE_CARDS_NOT_FOUND;
			throw new FinancierNotFoundException(error.getCode(), error.getStatus(), error.getMessage());
		}
		// return customerLoyaltyAccount;

		// if (financier != null)
		dto = conversionUtil.getFinancierDto(financier);
		/*
		 * else throw new Exception("Financier Not Available for Given ID:: " +
		 * financierId);
		 */

		return dto;
	}

	public void deleteFinancier(String financierId) {

		// financierDao.delete(financierId);
		Query query3 = new Query();
		query3.addCriteria(Criteria.where("financierId").is(financierId));
		Financier userTest3 = mongoOperations.findAndRemove(query3, Financier.class);

	}

	public FinancierDto updateFinancier(FinancierDto dto) throws Exception {

		Query query = new Query();
		query.addCriteria(Criteria.where("financierId").is(dto.getFinancierId()));

		Update update = new Update();
		update.set("financierName", dto.getFinancierName());
		update.set("financierLocation", dto.getFinancierLocation());

		/*
		 * Financier financierBeforeUpdate = mongoOperations.findOne(query,
		 * Financier.class); System.out.println("financierBeforeUpdate - " +
		 * financierBeforeUpdate);
		 */

		// Update Document if available else Insert new Document
		// mongoOperations.upsert(query, update, Financier.class);

		// First Search for Document if found then Update else return null
		Financier financier = mongoTemplate.findAndModify(query, update, Financier.class);

		if (financier != null) {
			System.out.println("financierBeforeUpdate - " + financier);

		} else {

			throw new Exception("Requested Financier Not Found with ID ::" + dto.getFinancierId());

		}

		Financier financierAfterUpdate = mongoOperations.findOne(query, Financier.class);
		System.out.println("financierAfterUpdate - " + financierAfterUpdate);
		FinancierDto financierDto = conversionUtil.getFinancierDto(financierAfterUpdate);
		return financierDto;

	}

}
