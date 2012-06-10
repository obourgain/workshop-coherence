package com.zenika.workshop.coherence.service;

import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;
import com.zenika.workshop.coherence.model.Person;

/**
 * @author olivier
 *
 */
public class OldGuyProcessor extends AbstractProcessor {

	public Object process(Entry entry) {
		System.out.println(entry.getKey());
		Person person = (Person) entry.getValue();
		System.out.println(person);
		if (person != null) {
			person.setAge(person.getAge() + 1);
			entry.setValue(person);
			return person.getAge();
		}
		return null;
	}
}
