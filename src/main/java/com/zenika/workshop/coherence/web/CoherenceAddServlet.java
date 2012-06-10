package com.zenika.workshop.coherence.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.zenika.workshop.coherence.model.Person;
import com.zenika.workshop.coherence.service.PersonService;

/**
 * @author olivier
 *
 */
public class CoherenceAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastName = req.getParameter("lastName");
		String firstName = req.getParameter("firstName");

		if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
			resp.getOutputStream().println("indiquer lastName ou firstName");
		}

		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setModificationDate(new DateTime());
		PersonService.getInstance().save(person);
		resp.getOutputStream().println("added " + person);

		// PersonService personService = PersonService.getInstance();

		// DataFactory df = new DataFactory();
		// for (int i = 0; i < 10; i++) {
		// Person person = new Person();
		// person.setFirstName(df.getFirstName());
		// person.setLastName(df.getLastName());
		// person.setAge(df.getNumberUpTo(99));
		// person.setModificationDate(new DateTime());
		//
		// personService.save(person);
		// resp.getOutputStream().println("added " + person);
		// }

	}

}
