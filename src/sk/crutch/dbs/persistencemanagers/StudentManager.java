package sk.crutch.dbs.persistencemanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import sk.crutch.dbs.models.Student;

public class StudentManager extends AllTablesManager {
	
	protected Student processRow(ResultSet rs) throws SQLException{
		return(new Student(rs.getString("name"),rs.getDouble("vsp")));
	}
	
	public List<Student> getAllStudents() throws SQLException
	{
		return(selectQuery("SELECT * FROM students"));
	}
	
	
	public static List<Student> getAllStudentsOld() throws SQLException{
		List<Student> result = new LinkedList<Student>();
		Connection conn = null;
		Statement stmt = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "postgres");
	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2014";
	    try {
			conn = DriverManager.getConnection(connectionString, connectionProps);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			while(rs.next()){
				result.add(new Student(rs.getString("name"),rs.getDouble("vsp")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	    return result;
	}

	public void updateStudents(String attribute, HashMap<Integer,Double> vsps) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "postgres");
	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2014";
	    
		try {
			conn = DriverManager.getConnection(connectionString, connectionProps);
			conn.setAutoCommit(false);
			String updateStatementString = "UPDATE students SET vsp = ? WHERE id = ?";
			stmt  =  conn.prepareStatement(updateStatementString);
			for (Map.Entry<Integer, Double> e : vsps.entrySet()) {
							//stmt.setString(1, attribute);
							
							stmt.setDouble(1, e.getValue().doubleValue());
							stmt.setInt(2, e.getKey());
							//tu dame breakpoint a skusime nieco spravit s tabulkou
							stmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
	            try {
	            	System.err.println(e.getMessage());
	            	System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                
	            }
	        }
		} finally {
			if(stmt != null){
				stmt.close();
			}
		}
	}
	
	public void insertStudents(List<Student> students) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "postgres");
	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2014";
	    try {
			conn = DriverManager.getConnection(connectionString, connectionProps);
			conn.setAutoCommit(false);
			//String createStatementString = "INSERT INTO students(id, name,vsp) VALUES(nextval('students_id_seq'),?,?)";
			String createStatementString = "INSERT INTO students(id, name,vsp) VALUES(DEFAULT,?,?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			for (Student s : students) {
							stmt.setString(1, s.getName());
							stmt.setDouble(2, s.getVsp());
							stmt.executeUpdate();
			}

			
			conn.commit();
//			throw new SQLException("Tuto vynimku sme vyhodili naschval");


		} catch (SQLException e) {
			if (conn != null) {
	            try {
	            	System.err.println(e.getMessage());
	            	System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                
	            }
	        }
		} finally {
			if(stmt != null){
				stmt.close();
			}
		}
	}
	
	public List<Integer> insertStudentsWithKeys(List<Student> students) throws SQLException {
		List<Integer> createdIds = new LinkedList<Integer>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "postgres");
	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2014";
	    try {
			conn = DriverManager.getConnection(connectionString, connectionProps);
			conn.setAutoCommit(false);
			//String createStatementString = "INSERT INTO students(id, name,vsp) VALUES(nextval('students_id_seq'),?,?)";
			String createStatementString = "INSERT INTO students(id, name,vsp) VALUES(DEFAULT,?,?) RETURNING id";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			for (Student s : students) {
							stmt.setString(1, s.getName());
							stmt.setDouble(2, s.getVsp());
							boolean result = stmt.execute();
							if (result == true) {
								ResultSet keys = stmt.getResultSet();
								while(keys.next()){
									createdIds.add(keys.getInt("id"));
								}
							}
			}
			conn.commit();
			
//			throw new SQLException("Tuto vynimku sme vyhodili naschval");


		} catch (SQLException e) {
			if (conn != null) {
	            try {
	            	System.err.println(e.getMessage());
	            	System.err.print("Transaction is being rolled back");
	            	createdIds.clear();
	                conn.rollback();
	            } catch(SQLException excep) {
	                
	            }
	        }
		} finally {
			if(stmt != null){
				stmt.close();
			}
		}
		return createdIds;
	}
}
