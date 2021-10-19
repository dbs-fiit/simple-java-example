package sk.crutch.dbs.persistencemanagers;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import sk.crutch.dbs.models.Restaurant;
import sk.crutch.dbs.utils.HibernateUtil;

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

