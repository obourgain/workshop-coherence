package com.zenika.workshop.coherence.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zenika.workshop.coherence.service.PersonService;

/**
 * @author olivier
 *
 */
public class CoherenceProcessServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastName = req.getParameter("lastName");
		String firstName = req.getParameter("firstName");

		if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
			resp.getOutputStream().println("indiquer lastName et firstName");
		}

		Map<String, Integer> newAges = PersonService.getInstance().incrementAge(lastName, firstName);
		
		for (Map.Entry<String, Integer> entry : newAges.entrySet()) {
			resp.getOutputStream().println(entry.getValue());
		}
	}

}
