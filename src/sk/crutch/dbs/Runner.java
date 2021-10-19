package sk.crutch.dbs;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sk.crutch.dbs.models.Restaurant;
import sk.crutch.dbs.models.Student;
import sk.crutch.dbs.persistencemanagers.RestaurantManager;
import sk.crutch.dbs.persistencemanagers.StudentManager;
import sk.crutch.dbs.utils.HibernateUtil;


public class Runner {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		/** Getting the Session Factory and session */
	    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = sessionFactory.getCurrentSession();
	    /** Starting the Transaction */
	    Transaction tx = session.beginTransaction();
	    /** Creating Pojo */
//	    Student student = new Student();
//	    student.setName("Peter Citrónový");
//	    student.setVsp(2.0);
	    /** Saving POJO */
//	    session.save(student);
	    
	    List<Restaurant> restaurants = RestaurantManager.getAllRestaurants();
	    for(Iterator<Restaurant> it = restaurants.iterator();it.hasNext();) {
	    	Restaurant res = it.next();
	    	System.out.println(res.getName() + ":" + res.getCapacity());
	    }
	    System.out.println("Big restaurants:");
	    restaurants = RestaurantManager.getAllBigRestaurants(100);
	    for(Iterator<Restaurant> it = restaurants.iterator();it.hasNext();) {
	    	Restaurant res = it.next();
	    	System.out.println(res.getName() + ":" + res.getCapacity());
	    }
	    
	    Student mrkvicka = (Student) session.get(Student.class, 1);
	    System.out.println("Mrkvicka bol na obede " + mrkvicka.getLunches().size() + " krat");
	    
	    
	    Criteria crit = session.createCriteria(Student.class);
	    crit.add(Restrictions.like("name", "Jozko Mrkvicka"));
	    List<Student> students = crit.list();
	    
	    for(Iterator<Student> it = students.iterator(); it.hasNext();){
	    	Student s = it.next();
	    	System.out.println(s.getName() + ":" + s.getVsp() + ", obedov:" + s.getLunches().size());
	    }

	    /** Commiting the changes */
	    tx.commit();
	    /** Closing Session */
	    if(session.isOpen()){
	    	session.close();
	    }
//	    	
//
	}
}
