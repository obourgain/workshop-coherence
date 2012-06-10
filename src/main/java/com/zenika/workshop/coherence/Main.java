package com.zenika.workshop.coherence;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.zenika.workshop.coherence.web.CoherenceAddServlet;
import com.zenika.workshop.coherence.web.CoherenceAggregatorServlet;
import com.zenika.workshop.coherence.web.CoherenceProcessServlet;
import com.zenika.workshop.coherence.web.CoherenceSearchServlet;

/**
 * @author olivier
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new CoherenceAddServlet()), "/add/*");
		context.addServlet(new ServletHolder(new CoherenceSearchServlet()), "/search/*");
		context.addServlet(new ServletHolder(new CoherenceAggregatorServlet()), "/aggregate/*");
		context.addServlet(new ServletHolder(new CoherenceProcessServlet()), "/process/*");

		server.start();
		server.join();

	}

}
