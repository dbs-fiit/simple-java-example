package sk.fiit.dbs2014;


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

import sk.fiit.dbs2014.models.Student;
import sk.fiit.dbs2014.persistencemanagers.StudentManager;


public class Runner {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		StudentManager sm = new StudentManager();

		for (Student student : sm.getAllStudents()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
		
		for (Student student : StudentManager.getAllStudentsOld()) {
			System.out.println(student.getName() + ":" + student.getVsp());
		}
	    
		
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
