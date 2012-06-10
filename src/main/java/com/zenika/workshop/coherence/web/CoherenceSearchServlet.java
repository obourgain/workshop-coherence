package com.zenika.workshop.coherence.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zenika.workshop.coherence.model.Person;
import com.zenika.workshop.coherence.service.PersonService;

/**
 * @author olivier
 *
 */
public class CoherenceSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastName = req.getParameter("lastName");
		String firstName = req.getParameter("firstName");

		if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
			resp.getOutputStream().println("indiquer lastName et firstName");
		}

		List<Person> search = PersonService.getInstance().findByName(lastName, firstName);
		for (Person person : search) {
			resp.getOutputStream().println(person.toString());
		}
	}

}
