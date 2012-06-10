package com.zenika.workshop.coherence.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zenika.workshop.coherence.service.PersonService;

/**
 * @author olivier
 *
 */
public class CoherenceAggregatorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersonService personService = PersonService.getInstance();
		
		resp.getOutputStream().println("mean age " + personService.getMeanAge());
	}

}
