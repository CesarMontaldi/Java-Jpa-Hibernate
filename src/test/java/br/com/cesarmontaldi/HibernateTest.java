package br.com.cesarmontaldi;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.Usuario;

public class HibernateTest {
	
	@Test 
	public void testInit() { /* Roda o projeto e cria/atualiza as tabelas */
		HibernateUtil.getEntityManager();
	}
	 
	@Test
	public void testSalvar() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		
		pessoa.setNome("Paulo Moreira");
		pessoa.setEmail("paulo@gmail.com");
		pessoa.setSenha("943671");
		pessoa.setIdade(39);
		pessoa.setLogin("paulo123");
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testUpdate() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisarId(Usuario.class, 1L);
		
		pessoa.setNome("César Augusto Montaldi");
		pessoa.setLogin("forcalivre");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testBuscar() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		pessoa.setId(1L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testBuscarId() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisarId(Usuario.class, 1L);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void findAll() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> users = daoGeneric.listar(Usuario.class);
		
		for (Usuario usuario : users) {
			
			System.out.println("-----------------------------------------------");
			System.out.println(usuario);
		}
	}
	
	@Test
	public void testQueryList() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> users = daoGeneric.getEntityManager().createQuery("from Usuario where nome like '%C%'").getResultList();
		
		for (Usuario usuario : users) {
			
			System.out.println("-----------------------------------------------");
			System.out.println(usuario);
			
		}
	}
	
	@Test
	public void testQueryListMaxResult() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> users = daoGeneric.getEntityManager()
				.createQuery("from Usuario where order by nome")
				.setMaxResults(5)
				.getResultList();
		
		for (Usuario usuario : users) {
			
			System.out.println("-----------------------------------------------");
			System.out.println(usuario);
			
		}
	}
	
	@Test
	public void testQueryListParameter() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		List<Usuario> users = daoGeneric.getEntityManager()
				.createQuery("from Usuario where nome = :nome")
				.setParameter("nome", "Barbara Silva").getResultList();
		
		for (Usuario usuario : users) {
			
			System.out.println("-----------------------------------------------");
			System.out.println(usuario);
			
		}
	}
	
	@Test
	public void testQuerySomaIdade() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from Usuario u ").getSingleResult();
		Double mediaIdade = (Double) daoGeneric.getEntityManager().createQuery("select avg(u.idade) from Usuario u ").getSingleResult();
		
		System.out.println("Soma das idades: " + somaIdade);
		System.out.println("Média das idades: " + mediaIdade);
	}
	
	@Test
	public void testDeleteId() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisarId(Usuario.class, -46L);
		
		daoGeneric.deleteId(pessoa);
	}
	
}



