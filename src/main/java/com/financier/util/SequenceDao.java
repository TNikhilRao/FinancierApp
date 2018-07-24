package com.financier.util;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public interface SequenceDao {
	
	long getNextSequenceId(String key) throws SequenceException;

}
