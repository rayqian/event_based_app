package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema. 
	public static void main(String[] args) {
		try {
			// This is java.sql.Connection. Not com.mysql.jdbc.Connection.
			Connection conn = null;

			// Step 1 Connect to MySQL.
			try {
				System.out.println("Connecting to " + MySQLDBUtil.URL);
				//register the driver
				Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
				//use the driver to get the connection
				conn = DriverManager.getConnection(MySQLDBUtil.URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}


			if (conn == null) {
				return;
			}
			
			// Step 2 Drop tables in case they exist
			Statement s1 = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS categories";
			s1.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS history";
			s1.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS items";
			s1.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS users";
			s1.executeUpdate(sql);
			
			// Step 3 create new tables
			sql = "CREATE TABLE items ("
					+ "item_id VARCHAR(255) NOT NULL,"
					+ "name VARCHAR(255),"
					+ "rating FLOAT,"
					+ "address VARCHAR(255),"
					+ "image_url VARCHAR(255),"
					+ "url VARCHAR(255),"
					+ "distance FLOAT,"
					+ "PRIMARY KEY (item_id))";
			s1.executeUpdate(sql);
			//create categories table seperately, because one item may have different categories
			//primary key : item_id and category
			sql = "CREATE TABLE categories ("
					+ "item_id VARCHAR(255) NOT NULL,"
					+ "category VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (item_id, category),"
					+ "FOREIGN KEY (item_id) REFERENCES items(item_id))";
			s1.executeUpdate(sql);

			sql = "CREATE TABLE users ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "password VARCHAR(255) NOT NULL,"
					+ "first_name VARCHAR(255),"
					+ "last_name VARCHAR(255),"
					+ "PRIMARY KEY (user_id))";
			s1.executeUpdate(sql);
			
			sql = "CREATE TABLE history ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "item_id VARCHAR(255) NOT NULL,"
					+ "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (user_id, item_id),"
					+ "FOREIGN KEY (item_id) REFERENCES items(item_id),"
			 		+ "FOREIGN KEY (user_id) REFERENCES users(user_id))";
			s1.executeUpdate(sql);
			
			// Step 4: insert sample data
			sql = "INSERT INTO users VALUES ("
					+ "'1122', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
			System.out.println("Executing query: " + sql);
			s1.executeUpdate(sql); 

			System.out.println("Import is done successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
