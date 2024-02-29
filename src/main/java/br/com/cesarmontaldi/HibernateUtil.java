package br.com.cesarmontaldi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		init();
	}
	
	private static void init() {
		
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("Java-Jpa-Hibernate");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager(); /* Prove a parte de persistencia */
	}

}
