package br.com.cesarmontaldi;

import org.junit.Test;

import dao.DaoGeneric;
import model.Usuario;

public class HibernateTest {
	
	@Test
	public void tesHibernateSalvar() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		
		pessoa.setNome("César Montaldi");
		pessoa.setEmail("guto_montaldi@yahoo.com.br");
		pessoa.setSenha("Forc@livre12345");
		pessoa.setIdade(37);
		pessoa.setLogin("guto123");
		
		daoGeneric.salvar(pessoa);
		
	}
	
	@Test
	public void testHibernateBuscar() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		pessoa.setId(1L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testHibernateBuscarId() {
		
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisarId(Usuario.class, 1L);
		
		System.out.println(pessoa);
		
	}

}



