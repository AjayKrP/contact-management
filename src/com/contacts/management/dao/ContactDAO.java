package com.contacts.management.dao;

import com.contacts.management.model.Contact;
import static com.contacts.management.config.DB.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ContactDAO {

	private static final String INSERT_CONTACTS_SQL = "INSERT INTO contacts" + "  (name, email, address, mobile) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_CONTACT_BY_ID = "select id,name,email,address,mobile from contacts where id =?";
	private static final String SELECT_ALL_CONTACTS = "select * from contacts";
	private static final String SEARCH_CONTACTS = "select * from contacts where name like ?;";
	private static final String DELETE_CONTACTS_SQL = "delete from contacts where id = ?;";
	private static final String UPDATE_CONTACTS_SQL = "update contacts set name = ?,email= ?, address =?, mobile=? where id = ?;";
    private static final String FILTER_CONTACTS_SQL = "select * from contacts  order by ";

	public ContactDAO() { }

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertContact(Contact user) throws SQLException {
		System.out.println(INSERT_CONTACTS_SQL);
		try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACTS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getMobile());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Contact selectContact(int id) {
		Contact contact = null;
		try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTACT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("address");
				String mobile = rs.getString("mobile");
				contact = new Contact(id, name, email, country, mobile);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contact;
	}

	public List<Contact> selectAllContacts() {

	List<Contact> contacts = new ArrayList<>();
		try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTACTS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				//System.out.println(name + email + address + mobile);
				contacts.add(new Contact(id, name, email, address, mobile));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contacts;
	}

	public boolean deleteContact(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CONTACTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateContact(Contact contact) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACTS_SQL);) {
			System.out.println("First: mobile");

			System.out.println("First: " + contact.getEmail() + contact.getMobile());

			statement.setString(1, contact.getName());
			statement.setString(2, contact.getEmail());
			statement.setString(3, contact.getAddress());
			statement.setString(4, contact.getMobile());
			statement.setInt(5, contact.getId());
			System.out.println(contact.getEmail() + contact.getMobile());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public List<Contact> searchContacts(String username) {
		System.out.println(username);
		List<Contact> contacts = new ArrayList<>();
		try (Connection connection = getConnection();

			 PreparedStatement statement = connection.prepareStatement(SEARCH_CONTACTS);) {
				statement.setString(1, "%" + username + "%");
				ResultSet rs = statement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("address");
				String mobile = rs.getString("mobile");
				contacts.add(new Contact(id, name, email, country, mobile));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contacts;
	}

	public List<Contact> filterContacts(String filter_by, String sort_order) {
		System.out.println(sort_order + " => " + filter_by);
		List<Contact> contacts = new ArrayList<>();
		try (Connection connection = getConnection();

			 PreparedStatement statement = connection.prepareStatement(FILTER_CONTACTS_SQL + filter_by + " "+ sort_order +';');) {
			System.out.println(statement);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				System.out.println(name + email + address + mobile);
				contacts.add(new Contact(id, name, email, address, mobile));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contacts;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
