package com.donutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donutec.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

	List<Ingrediente> findByNomeContaining(String nome);
}
