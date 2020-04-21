package com.example.angular.oito.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@NotEmpty(message="não pode ser vazio")
	@Size(min = 4, max = 12, message="o tamanho tem que estar entre 4 e 12 caracteres")
	private String nombre;
	
	@NotEmpty(message="não pode ser vazio")
	private String apellido;

	@Column(nullable = false, unique = false)
	@NotEmpty(message="não pode ser vazio")
	@Email(message="não é um email válido")
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@NotNull(message="não pode ser vazio")
	private Date createAt;
	
	private String foto;

	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="não pode ser vazio")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handle"})
	private Region region;
	
	@JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer","handle"}, allowSetters  =true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade = CascadeType.ALL)
	private List<Fatura> faturas;
	
	public Cliente() {
		this.faturas = new ArrayList<Fatura>();
	}
	
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	//@PrePersist
	public void prePresist() {
		createAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}
	
	
}
