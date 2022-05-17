package com.donutec.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private BigDecimal valor;
	
	private String obs;
	
	@OneToOne
	private Venda venda;
	
	/*@ManyToOne
	@JoinColumn(name = "ingrediente_id_fk")
	private Ingrediente ingrediente;*/
	
	
	
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	/*public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}*/
	
	

}
