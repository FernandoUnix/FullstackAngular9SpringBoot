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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "faturas")
public class Fatura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String observacao;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@JsonIgnoreProperties(value = {"faturas", "hibernateLazyInitializer","handle"}, allowSetters= true)
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fatura_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handle"})
	private List<ItemFatura> itens;

	@ManyToOne(fetch = FetchType.LAZY)
	public Cliente getCliente() {
		return cliente;
	}

	public Fatura() {
		this.itens = new ArrayList<ItemFatura>();
	}

	@PrePersist
	public void prePersist() {
		dataCadastro = new Date();
	}

	public Double getTotal() {

		Double total = 0.0;

		for (ItemFatura item : itens) {
			total += item.getValorTotal();
		}

		return total;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<ItemFatura> getItens() {
		return itens;
	}

	public void setItens(List<ItemFatura> itens) {
		this.itens = itens;
	}
}
