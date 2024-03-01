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
		
		pessoa.setNome("Barbara Silva");
		pessoa.setEmail("barbara@hotmail.com");
		pessoa.setSenha("963147");
		pessoa.setIdade(26);
		pessoa.setLogin("barbSilva");
		
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
			
			System.out.println(usuario);
			System.out.println("-----------------------------------------------");
		}
	} 
	
	@Test
	public void testDeleteId() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisarId(Usuario.class, -46L);
		
		daoGeneric.deleteId(pessoa);
	}
	
	

}



