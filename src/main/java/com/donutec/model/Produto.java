package com.donutec.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O nome do donuts não pode estar vazio!")
	private String nomeDonuts;

	@NotNull(message = "Valor não pode ser null!")
	private double valor;
	
	private String obs;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Venda> vendas = new ArrayList<Venda>();
	
	@ManyToMany(mappedBy = "produtos")
	private List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNomeDonuts() {
		return nomeDonuts;
	}
	public void setNomeDonuts(String nomeDonuts) {
		this.nomeDonuts = nomeDonuts;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	
	
	
	
	
	
	

}
