package com.donutec.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "ingrediente",cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	@OneToMany(mappedBy = "ingrediente",cascade = CascadeType.ALL)
	private List<Fornecedor> fornecedores;
	
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
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	
	
	

}
