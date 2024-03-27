package dao;

import java.util.List;

import br.com.cesarmontaldi.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoGeneric <Entity> {
	
		private EntityManager entityManager = HibernateUtil.getEntityManager();
		
		
		public void salvar(Entity entity) { /* Salvar */
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			
			transaction.commit();
		}
		
		public Entity updateMerge(Entity entity) { /* Salva ou atualiza */
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Entity entitySalva = entityManager.merge(entity);
			
			transaction.commit();
			
			return entitySalva;
		}
		
		public Entity pesquisar(Entity entity) { /* Consultar */
			
			Object id = HibernateUtil.getprimaryKey(entity);
			
			Entity entityPk = (Entity) entityManager.find(entity.getClass(), id);
			
			return entityPk;
		}
		
		public Entity pesquisarId( Class<Entity> entity, Long id) { /* Consultar por ID*/
			entityManager.clear();
			Entity entityPk = (Entity) entityManager.createQuery("from " + entity.getSimpleName() + " where id = " + id).getSingleResult();
			
			return entityPk;
		}
		
		public List<Entity> listar(Class<Entity> entity) { /* Listar todos */
			
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.clear();
			List<Entity> users = entityManager.createQuery("from " + entity.getSimpleName() + " order by id ASC").getResultList();
			
			transaction.commit();
			
			return users;
		}
		
		
		public void deleteId(Entity entity) throws Exception { /* Deleta por ID */
			
			Object id = HibernateUtil.getprimaryKey(entity);
			
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			entityManager.createNativeQuery("delete from " + entity.getClass().getSimpleName().toLowerCase() + " where id= " + id).executeUpdate(); /* Faz o delete */ 
			
			transaction.commit(); /* grava a alteração */
		}
		
		public EntityManager getEntityManager() {
			return entityManager;
		}
		
}




