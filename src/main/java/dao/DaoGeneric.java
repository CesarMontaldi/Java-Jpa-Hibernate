package dao;

import br.com.cesarmontaldi.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoGeneric <E> {
	
		private EntityManager entityManager = HibernateUtil.getEntityManager();
		
		
		public void salvar(E entity) {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			
			transaction.commit();
		}
}
