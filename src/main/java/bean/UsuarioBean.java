package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DaoGeneric;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuarios() {
		usuarios = daoGeneric.listar(Usuario.class);
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String salvar() {
		daoGeneric.salvar(usuario);
		return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		return "";
	}
	
	public List<Usuario> buscarUsuarios() {
		
		usuarios = daoGeneric.listar(Usuario.class);
		return usuarios;
	}
}
