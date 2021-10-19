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


public class Runner5 {

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
	    
	    StudentManager sm = new StudentManager(provider);
		
		

		for (Student student : sm.getAllStudents()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
		
		Runner2.javaSystem.out.println("#####################################");
//		
//		for (Student student : sm.getAllStudentsOld()) {
//			System.out.println(student.getName() + ":" + student.getVsp());
//		}

//		List<Student> students = new LinkedList<Student>();
//		students.add(new Student("Fero Transakcny7", 2.3));
//		students.add(new Student("Jozo Paralelny7", 8.5));

//		List<Integer> keys = sm.insertStudentsWithKeys(students);
		
//		HashMap<Integer, Double> map = new HashMap<Integer,Double>();
//		
//		for(Integer key : keys){
//			Double newVsp = (double)(Math.random()*5);
//			System.out.println("new student id:" + key + " and his new vsp:" + newVsp);
//			map.put(key, newVsp);
//		}
//
//		sm.updateStudents("vsp", map);
//		
//		for (Student student : sm.getAllStudents()) {
//			System.out.println(student.getName() + ":" + student.getVsp());
//		}
//	    
	}

}
