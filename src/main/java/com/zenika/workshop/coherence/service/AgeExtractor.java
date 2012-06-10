package com.zenika.workshop.coherence.service;

import com.tangosol.util.extractor.AbstractExtractor;
import com.zenika.workshop.coherence.model.Person;

/**
 * @author olivier
 *
 */
public class AgeExtractor extends AbstractExtractor {

	@Override
	public Object extract(Object object) {
		Person person = (Person) object;
		return person.getAge();
	}

}
