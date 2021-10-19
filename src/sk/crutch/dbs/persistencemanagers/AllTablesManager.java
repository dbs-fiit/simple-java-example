package sk.crutch.dbs.persistencemanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.postgresql.ds.PGPoolingDataSource;

import sk.crutch.dbs.ConnectionProvider;
import sk.crutch.dbs.models.Student;

public abstract class AllTablesManager {
	
	protected ConnectionProvider provider;
	
	public AllTablesManager(ConnectionProvider provider){
		this.provider = provider;
	}
	
	
	protected List selectQuery(String queryString) throws SQLException{
		List result = new LinkedList();
		Statement stmt = null;
		Connection conn = this.provider.getConnection();
	    try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()){
				result.add(processRow(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
			return result;
		}
	}
	
	protected abstract Object processRow(ResultSet rs) throws SQLException;

}
