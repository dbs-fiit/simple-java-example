package sk.fiit.dbs;


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

import org.postgresql.ds.PGPoolingDataSource;

import sk.fiit.dbs.models.Student;
import sk.fiit.dbs.persistencemanagers.StudentManager;


public class Runner {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		PGPoolingDataSource source = new PGPoolingDataSource();
		source.setDataSourceName("A Data Source");
		source.setServerName("localhost");
		source.setDatabaseName("DBS2017");
		source.setUser("dbsfiit");
		source.setPassword("dbsfiit");
		source.setMaxConnections(10);
		
//		Connection conn = null;
//		Properties connectionProps = new Properties();
//	    connectionProps.put("user", "dbsfiit");
//	    connectionProps.put("password", "dbsfiit");
//	    String connectionString = "jdbc:postgresql://localhost:5432/DBS2017";
		
		StudentManager sm = new StudentManager(source);

		for (Student student : sm.getAllStudents()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
		
		System.out.println("#####################################");
//		
//		for (Student student : sm.getAllStudentsOld()) {
//			System.out.println(student.getName() + ":" + student.getVsp());
//		}

		List<Student> students = new LinkedList<Student>();
		students.add(new Student("Fero Transakcny7", 2.3));
		students.add(new Student("Jozo Paralelny7", 8.5));

		List<Integer> keys = sm.insertStudentsWithKeys(students);
		
		HashMap<Integer, Double> map = new HashMap<Integer,Double>();
		
		for(Integer key : keys){
			Double newVsp = (double)(Math.random()*5);
			System.out.println("new student id:" + key + " and his new vsp:" + newVsp);
			map.put(key, newVsp);
		}

		sm.updateStudents("vsp", map);
		
		for (Student student : sm.getAllStudents()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
	    
	}

}
