package com.bbdp.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdministratorLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		String state = request.getParameter("state");
		HttpSession session = request.getSession();
		if (state.equals("login")) {
			String account = request.getParameter("account");
			String password = "" + com.bbdp.encryption.BBDPBase64.decode(request.getParameter("password"));
			if (account.equals("bbdp") && password.equals("15192353")) {
				session.setAttribute("login", "yes");
				System.out.println("管理者登入");
				response.getWriter().write("loginSuccess");
			} else {
				response.getWriter().write("loginFail");
			}
		} else if (state.equals("logout")) {
			session.invalidate();
			System.out.println("管理者登出");
		} else if (state.equals("loginVerify")) {
			if (session.getAttribute("login") == null) {
				response.getWriter().write("logout");
			} else {
				response.getWriter().write("login");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}