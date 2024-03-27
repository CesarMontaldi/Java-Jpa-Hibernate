package dao;

import model.Usuario;

public class DaoUsuario<Entity> extends DaoGeneric<Usuario> {

	public void removeUsuario(Usuario user) throws Exception {
		
		String sql = "delete from telefone where usuario_id = " + user.getId();
		getEntityManager().getTransaction().begin();
		getEntityManager().createNativeQuery(sql).executeUpdate();
		getEntityManager().getTransaction().commit();
		
		super.deleteId(user);
	}
}
