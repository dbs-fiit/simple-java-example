package sk.crutch.dbs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


import sk.crutch.dbs.models.Student;
import sk.crutch.dbs.persistencemanagers.StudentManager;


public class Runner2 {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		System.out.println("Started");
		
		Connection conn = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "dbs");
	    connectionProps.put("password", "dbs");
	    String connectionString = "jdbc:postgresql://localhost:5432/postgres";
	    
	    ConnectionProvider provider = new ConnectionProvider(connectionString, connectionProps);
	    conn = provider.getConnection();
	    
	    System.out.println("Successfully connected");
	    List<Student> students = new LinkedList<Student>();
	    
	    Statement stmt = conn.createStatement();
	    
		ResultSet rs = stmt.executeQuery("SELECT * FROM students");
		while(rs.next()){
			students.add(new Student(rs.getString("name"),rs.getDouble("vsp")));
		}
		for (Student student : students) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
	}

}
