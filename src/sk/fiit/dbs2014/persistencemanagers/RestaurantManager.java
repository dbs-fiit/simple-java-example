package sk.fiit.dbs2014.persistencemanagers;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import sk.fiit.dbs2014.models.Restaurant;
import sk.fiit.dbs2014.utils.HibernateUtil;

public class RestaurantManager {
	public static List<Restaurant> getAllRestaurants(){
		List<Restaurant> result = new LinkedList<Restaurant>();
		return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Restaurant.class).list();
	}
	
	public static List<Restaurant> getAllBigRestaurants(int requested_capacity){
		List<Restaurant> result = new LinkedList<Restaurant>();
		return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Restaurant.class)
				.add(Restrictions.ge("capacity", requested_capacity)).list();
	}
}

