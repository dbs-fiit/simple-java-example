package sk.crutch.dbs.persistencemanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import sk.crutch.dbs.models.Student;

public abstract class AllTablesManager {
	
	protected List selectQuery(String queryString) throws SQLException{
		List result = new LinkedList();
		Connection conn = null;
		Statement stmt = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "postgres");
	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2014";
	    try {
			conn = DriverManager.getConnection(connectionString, connectionProps);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()){
				result.add(processRow(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			return result;
		}
	}
	
	protected abstract Object processRow(ResultSet rs) throws SQLException;

}
