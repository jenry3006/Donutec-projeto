package com.donutec.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@Entity
public class Ingrediente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	

	@ManyToMany
	@JoinTable(name = "ingrediente_produto",
	joinColumns = {@JoinColumn(name="ingrediente_id")},
	inverseJoinColumns = {@JoinColumn(name="produto_id")})
	@Column(nullable = false)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	/**/
	
	/*@OneToMany(mappedBy = "ingrediente",cascade = CascadeType.ALL)
	private List<Fornecedor> fornecedores;*/
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal precoCompra;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	
	/*public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}*/
	
	
	
	

}
