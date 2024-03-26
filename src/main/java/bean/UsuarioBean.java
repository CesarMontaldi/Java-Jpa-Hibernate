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
		message(FacesMessage.SEVERITY_INFO, "Cadastro salvo com sucesso!");
		return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		return "";
	}
	
	
	public String deletar() {
		try {
			daoGeneric.deleteId(usuario);
			usuarios.remove(usuario);
			usuario = new Usuario();
			message(FacesMessage.SEVERITY_INFO, "Removido com sucesso!");
			
		} catch (Exception e) {
			if (e.getCause() instanceof org.postgresql.util.PSQLException) {
				message(FacesMessage.SEVERITY_ERROR, usuario.getNome().split(" ")[0] + " Possui telefone cadastrado!");
			}
		}
		return "";
	}
	
	public String home() {
		return "home.jsf";
	}
	
	public void message(Severity severityInfo, String msg) {
		FacesMessage message = new FacesMessage(severityInfo, msg, null);
		FacesContext.getCurrentInstance().addMessage(null,message);
	}
	
}
