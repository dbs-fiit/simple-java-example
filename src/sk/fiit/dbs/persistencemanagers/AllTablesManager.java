package sk.fiit.dbs.persistencemanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.postgresql.ds.PGPoolingDataSource;

import sk.fiit.dbs.models.Student;

public abstract class AllTablesManager {
	protected PGPoolingDataSource source;
	
	public AllTablesManager(PGPoolingDataSource source){
		this.source = source;
	}
	
	protected List selectQuery(String queryString) throws SQLException{
		List result = new LinkedList();
		Statement stmt = null;
		Connection conn = this.source.getConnection();
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
