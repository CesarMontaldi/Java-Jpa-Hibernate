package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", allocationSize = 1, initialValue = 1)
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
	private Long id;
	
	@Column(nullable = false)
	private String tipo;
	
	@Column(nullable = false)
	private String numero;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
