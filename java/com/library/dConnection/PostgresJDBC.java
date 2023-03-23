package com.library.dConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresJDBC {
	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
		}catch(Exception  e) {
			e.printStackTrace();
		} return con;
	}
		public void closeConnection(Statement smt,Connection con) {
			try {
				smt.close();
				con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					con.close();
				}catch (SQLException  se)
				{
					se.printStackTrace();}
				}
	}

}
