package com.financier.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.financier.dao.IFinancierDao;
import com.financier.entity.Financier;
import com.financier.exception.FinancierNotFoundException;
import com.financier.response.FinancierDto;
import com.financier.service.impl.FinancierServiceImpl;
import com.financier.util.ConversionUtil;
import com.financier.util.SequenceDao;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@RunWith(SpringRunner.class)
public class FinancierServiceImplTest {

	@InjectMocks
	private FinancierServiceImpl financierService;

	@Mock
	private IFinancierDao financierDao;

	@Mock
	private ConversionUtil conversionUtil;

	@Mock
	private Query query;

	@Mock
	private Update update;

	@Mock
	private MongoOperations mongoOperations;

	@Mock
	private MongoTemplate mongoTemplate;

	@Mock
	private Criteria criteria;

	@Mock
	private SequenceDao sequenceDao;

	private String HOSTING_SEQ_KEY = "hosting";

	private Financier financier;

	@Mock
	private FinancierDto dto;
	private List<Financier> financiers = new ArrayList<Financier>();
	List<FinancierDto> dtos = new ArrayList<FinancierDto>();

	@Before
	public void setUp() {
		financier = new Financier("25", "33", "SynechronPune", "Ascendas");
		dto = new FinancierDto("25", "33", "SynechronPune", "Ascendas");
		financiers.add(new Financier("26", "34", "SynechronMumbai", "Mumbai"));
		financiers.add(new Financier("27", "35", "SynechronHyderabad", "Hyderabad"));
		financiers.add(new Financier("28", "36", "SynechronBanglore", "Banglore"));

	}

	@Test
	public void testFindFinancier() throws Exception {

		when(financierDao.findByFinancierId("33")).thenReturn(financier);
		when(conversionUtil.getFinancierDto(financier)).thenReturn(dto);

		FinancierDto response = financierService.findFinancier("33");
		assertTrue("33".equals(response.getFinancierId()));
	}

	@Test(expected = FinancierNotFoundException.class)
	public void testFindFinancier_Fail() throws Exception {
		when(financierDao.findByFinancierId("33")).thenReturn(financier);

		FinancierDto response = financierService.findFinancier("1");
		assertTrue("33".equals(response.getFinancierId()));
	}

	@Test
	public void testDeleteFinancier() {

		when(query.addCriteria(Criteria.where("financierId").is("1"))).thenReturn(query);
		when(mongoOperations.findAndRemove(query, Financier.class)).thenReturn(financier);
		financierService.deleteFinancier("1");
		assertFalse("1".equals(financier.getFinancierId()));
	}

	@Test
	public void testDeleteFinancier_Fail() {

		when(query.addCriteria(Criteria.where("financierId").is("1"))).thenReturn(query);
		when(mongoOperations.findAndRemove(query, Financier.class)).thenReturn(new Financier());
		financierService.deleteFinancier("1");
		assertFalse("null".equals(financier.getFinancierId()));
	}

	@Test
	public void testGetAll() {

		when(financierDao.findAll()).thenReturn(financiers);
		when(conversionUtil.getFinancierDto(financier)).thenReturn(dto);
		dtos.add(dto);

		List<FinancierDto> dtos = financierService.getAll();

		boolean expected = dtos.containsAll(financiers);

		assertEquals(expected, false);

	}

	@Test // (expected = Exception.class)
	public void testUpdateFinancier() throws Exception {

		financier.setFinancierName("Updated_FinancierName");
		financier.setFinancierLocation("Updated_FinancierLocation");

		dto.setFinancierName("Updated_FinancierName");
		dto.setFinancierLocation("Updated_FinancierLocation");
		when(query.addCriteria(Criteria.where("financierId").is(Mockito.anyString()))).thenReturn(query);
		when(mongoTemplate.findAndModify(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(financier);
		when(mongoOperations.findOne(Mockito.any(), Mockito.any())).thenReturn(financier);
		when(conversionUtil.getFinancierDto(financier)).thenReturn(dto);
		dto = financierService.updateFinancier(dto);
		assertTrue("Updated_FinancierName".equals(dto.getFinancierName()));

	}

	@Test(expected = Exception.class)
	public void testUpdateFinancier_Fail() throws Exception {

		financier.setFinancierName("Updated_FinancierName");
		financier.setFinancierLocation("Updated_FinancierLocation");

		dto.setFinancierName("Updated_FinancierName");
		dto.setFinancierLocation("Updated_FinancierLocation");
		when(query.addCriteria(Criteria.where("financierId").is(Mockito.anyString()))).thenReturn(query);
		when(mongoTemplate.findAndModify(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(null);
		dto = financierService.updateFinancier(dto);

	}

	@Test
	public void testCreate() {

		Long id = new Long(1);
		when(conversionUtil.getFinancier(dto)).thenReturn(financier);
		when(sequenceDao.getNextSequenceId(HOSTING_SEQ_KEY)).thenReturn(id);
		when(financierDao.save(financier)).thenReturn(financier);
		when(conversionUtil.getFinancierDto(financier)).thenReturn(dto);

		financierService.create(dto);
		assertTrue("33".equals(financier.getFinancierId()));
	//	verify(financierService.create(dto));
		//assertEquals(isMock(dto), true);
	}

}
