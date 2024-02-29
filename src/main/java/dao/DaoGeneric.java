package dao;

import br.com.cesarmontaldi.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoGeneric <Entity> {
	
		private EntityManager entityManager = HibernateUtil.getEntityManager();
		
		
		public void salvar(Entity entity) {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			
			transaction.commit();
		}
		
		public Entity pesquisar(Entity entity) {
			
			Object id = HibernateUtil.getprimaryKey(entity);
			
			Entity entityPk = (Entity) entityManager.find(entity.getClass(), id);
			
			return entityPk;
		}
		
		public Entity pesquisarId( Class<Entity> entity, Long id) {
			
			Entity entityPk = (Entity) entityManager.find(entity, id);
			
			return entityPk;
		}
}
