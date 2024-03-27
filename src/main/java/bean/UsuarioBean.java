package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import dao.DaoUsuario;
import model.Usuario;
import utils.AddMessage;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	//private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<>();
	private DaoUsuario daoGeneric = new DaoUsuario<Usuario>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void init() {
		usuarios = daoGeneric.listar(Usuario.class);
	}
	
	public String salvar() {
		daoGeneric.salvar(usuario);
		usuarios.add(usuario);
		AddMessage.message(FacesMessage.SEVERITY_INFO, "Cadastro salvo com sucesso!");
		return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		return "";
	}
	
	
	public String deletar() {
		try {
			daoGeneric.removeUsuario(usuario);
			usuarios.remove(usuario);
			usuario = new Usuario();
			AddMessage.message(FacesMessage.SEVERITY_INFO, "Usuário removido com sucesso!");
			
		} catch (Exception e) {
			if (e.getCause() instanceof org.postgresql.util.PSQLException) {
				AddMessage.message(FacesMessage.SEVERITY_ERROR, usuario.getNome().split(" ")[0] + " Possui telefone cadastrado!");
			}
		}
		return "";
	}
	
	public String home() {
		return "home.jsf";
	}

	
}
