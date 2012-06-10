package com.zenika.workshop.coherence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.filter.AlwaysFilter;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.LikeFilter;
import com.zenika.workshop.coherence.model.Person;
import com.zenika.workshop.coherence.util.CoherenceHiLoGenerator;

/**
 * @author olivier
 */
public class PersonService {

	private static PersonService instance = new PersonService();

	private NamedCache cache;

	private PersonService() {
		cache = CacheFactory.getCache("person-cache");
	}

	public Person get(String id) {
		return (Person) cache.get(id);
	}

	public float getMeanAge() {
		return (Float) cache.aggregate(AlwaysFilter.INSTANCE, new MeanAgeAggregator(new AgeExtractor()));
	}

	public List<Person> findByName(String lastName, String firstName) {
		Filter filter;
		if(StringUtils.isNotEmpty(lastName) && StringUtils.isNotEmpty(firstName)) {
			Filter left = new LikeFilter("getLastName", "%" + lastName + "%");
			Filter right = new LikeFilter("getFirstName", "%" + firstName + "%");
			filter = new AndFilter(left, right);
		} else if (StringUtils.isNotEmpty(lastName)) {
			filter = new LikeFilter("getLastName", "%" + lastName + "%");
		} else {
			filter = new LikeFilter("getFirstName", "%" + firstName + "%");
		}
		@SuppressWarnings("unchecked")
		Set<Entry<Integer, Person>> entrySet = cache.entrySet(filter);
		List<Person> result = new ArrayList<Person>();
		for (Entry<Integer, Person> entry : entrySet) {
			result.add(entry.getValue());
		}
		return result;
	}

	public void save(Person person) {
		if (person.getId() == null) {
			person.setId(CoherenceHiLoGenerator.generate());
		}
		cache.put(person.getId(), person);
	}

	public static PersonService getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> incrementAge(String lastName, String firstName) {
		Filter filter;
		if (StringUtils.isNotEmpty(lastName) && StringUtils.isNotEmpty(firstName)) {
			Filter left = new LikeFilter("getLastName", "%" + lastName + "%");
			Filter right = new LikeFilter("getFirstName", "%" + firstName + "%");
			filter = new AndFilter(left, right);
		} else if (StringUtils.isNotEmpty(lastName)) {
			filter = new LikeFilter("getLastName", "%" + lastName + "%");
		} else {
			filter = new LikeFilter("getFirstName", "%" + firstName + "%");
		}

		return (Map<String, Integer>) cache.invokeAll(filter, new OldGuyProcessor());
	}

}
