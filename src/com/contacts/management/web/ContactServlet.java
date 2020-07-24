package com.contacts.management.web;
import com.contacts.management.dao.ContactDAO;

import com.contacts.management.model.Contact;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@WebServlet("/")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;
	
	public void init() {
		contactDAO = new ContactDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertContact(request, response);
				break;
			case "/delete":
				deleteContact(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateContact(request, response);
				break;
			case "/search":
				searchContact(request, response);
				break;
			case "/filter":
				filterContact(request, response);
				break;
			default:
				listContact(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contact> contactList = contactDAO.selectAllContacts();
		request.setAttribute("listContact", contactList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Contact existingUser = contactDAO.selectContact(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact-form.jsp");
		request.setAttribute("contact", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		Contact newContact = new Contact(name, email, address, mobile);
		contactDAO.insertContact(newContact);
		response.sendRedirect("list");
	}

	private void updateContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		System.out.println(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		System.out.println(name);
		String email = request.getParameter("email");
		System.out.println(email);
		String address = request.getParameter("address");
		System.out.println(address);
		String mobile = request.getParameter("mobile");
		System.out.println(mobile);
		Contact book = new Contact(id, name, email, address, mobile);
		System.out.println(name + email + mobile + address + id);
		contactDAO.updateContact(book);
		response.sendRedirect("list");
	}

	private void searchContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		/**
		 * API END POINTS /search?name=ajay
		 */
		System.out.println(request.getParameter("name"));
		List<Contact> contactList = contactDAO.searchContacts(request.getParameter("name"));
		request.setAttribute("listContact", contactList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact-list.jsp");
		dispatcher.forward(request, response);
	}

	private void filterContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		/**
		 * API END POINTS /filter?filter_by=mobile&sort_order=asc
		 */

		String filter_by = request.getParameter("filter_by");
		String sort_order = request.getParameter("sort_order");
		System.out.println(filter_by + " => " + sort_order);
		List<Contact> contactList = contactDAO.filterContacts(filter_by, sort_order);
		request.setAttribute("listContact", contactList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contact-list.jsp");
		dispatcher.forward(request, response);
	}


	private void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		contactDAO.deleteContact(id);
		response.sendRedirect("list");
	}

}
