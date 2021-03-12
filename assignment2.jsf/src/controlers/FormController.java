package controlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController {

	// EJB
	@Inject
	OrdersBusinessInterface service;

	public String onSubmit(User user) {

		getAllOrders();
		insertOrder();

		// send the User managed bean to the TestResponse view
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}

	public String onFlash(User user) {

		// send the User managed bean to the TestResponse view
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestResponse2.xhtml?faces-redirect=true";
	}

	public OrdersBusinessInterface getService() {
		return this.service;

	}

	private void getAllOrders() {

		Connection conn = null;

		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "password";

		// sql query to db
		String sql = "SELECT * FROM testapp.ORDERS";

		try {
			// connect to db
			conn = DriverManager.getConnection(url, username, password);

			// if connection successful
			System.out.println("The connection in getAllOrders() was successful!");

			// connection statement
			Statement stmt = conn.createStatement();

			// execute sql query
			ResultSet rs = stmt.executeQuery(sql);

			// use a while loop to go through result set
			while (rs.next()) {

				// add each row in the result set as a new order
				// orders.add(new Order(rs.getString("ORDER_NO"),
				// rs.getString("PRODUCT_NAME"),rs.getFloat("PRICE"),rs.getInt("QUANTITY")));

				// output each order to the console
				System.out.println(
						"Order # " + rs.getString("ORDER_NO") + " Product Name: " + rs.getString("PRODUCT_NAME")
								+ " Price: " + rs.getFloat("PRICE") + " Quantity: " + rs.getInt("QUANTITY"));
			}

			// close the connection
			conn.close();

		} catch (SQLException e) {
			// output if connection was NOT successful
			System.out.println("Failure!!");

			e.printStackTrace();
		}

	}

	private void insertOrder() {

		Connection conn = null;

		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "password";

		// sql query to db
		String sql = "SELECT * FROM testapp.ORDERS";

		// sql query to db
		String sql = "INSERT INTO testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES ('001122334455', 'This was inserted new', 25.00, 100)";

		try {
			// connect to db
			conn = DriverManager.getConnection(url, username, password);

			// output if connection was successful
			System.out.println("The connection in insertOrder() was success!");

			// create the connection statement
			Statement stmt = conn.createStatement();

			// execute sql query
			stmt.executeUpdate(sql);

			// close the connection
			conn.close();

		} catch (SQLException e) {
			// output if connection was NOT successful
			System.out.println("Failure!!");

			e.printStackTrace();
		}

	}

}
