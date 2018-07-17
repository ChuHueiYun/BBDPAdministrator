package com.bbdp.push;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

public class PushServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		DataSource datasource = (DataSource) getServletContext().getAttribute("db");
		String state = request.getParameter("state");
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String hyperlink = request.getParameter("hyperlink");
		if (state.equals("aPatient")) {
			String ID = request.getParameter("ID");
			response.getWriter().write(PushToUser.pushToAPatient(datasource, ID, title, body, hyperlink));
		} else if (state.equals("allPatient")) {
			response.getWriter().write(PushToUser.pushToAllPatient(datasource, title, body, hyperlink));
		} else if (state.equals("aDoctor")) {
			String ID = request.getParameter("ID");
			response.getWriter().write(PushToUser.pushToADoctor(datasource, ID, title, body, hyperlink));
		} else if (state.equals("allDoctor")) {
			response.getWriter().write(PushToUser.pushToAllDoctor(datasource, title, body, hyperlink));
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}