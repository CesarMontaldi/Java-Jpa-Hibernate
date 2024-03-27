package bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoTelefone;
import dao.DaoUsuario;
import model.Telefone;
import model.Usuario;
import utils.AddMessage;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean {
	
	private Telefone telefone = new Telefone();
	private DaoTelefone<Telefone> daoTelefone = new DaoTelefone<Telefone>();
	private Usuario usuario = new Usuario();
	private DaoUsuario<Usuario> daoUsuario = new DaoUsuario<Usuario>();

	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public DaoTelefone<Telefone> getDaoTelefone() {
		return daoTelefone;
	}

	public void setDaoTelefone(DaoTelefone<Telefone> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@PostConstruct
	public void init() {
		
		String idUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser");
		usuario = daoUsuario.pesquisarId(Usuario.class, Long.parseLong(idUser));
	}
	
	public String salvar() {
		if (telefone.getId() != null) {
			daoTelefone.updateMerge(telefone);
			usuario = daoUsuario.pesquisarId(Usuario.class, usuario.getId());
			telefone = new Telefone();
			AddMessage.message(FacesMessage.SEVERITY_INFO, "Telefone atualizado com sucesso!");
		
		} else {
			telefone.setUsuario(usuario);
			daoTelefone.salvar(telefone);
			usuario = daoUsuario.pesquisarId(Usuario.class, usuario.getId());
			telefone = new Telefone();
			AddMessage.message(FacesMessage.SEVERITY_INFO, "Telefone salvo com sucesso!");
		}
		return "";
	}
	
	public String deletar() throws Exception {
		daoTelefone.deleteId(telefone);
		telefone = new Telefone();
		usuario = daoUsuario.pesquisarId(Usuario.class, usuario.getId());
		AddMessage.message(FacesMessage.SEVERITY_INFO, "Telefone removido com sucesso!");
		
		return "";
	}
}
