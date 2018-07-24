package com.financier.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.financier.entity.Financier;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Repository
public interface IFinancierDao extends MongoRepository<Financier, String>, CrudRepository<Financier, String>
		//,QueryByExampleExecutor<Financier>
{

	public Financier findByFinancierId(String financierId);

}
