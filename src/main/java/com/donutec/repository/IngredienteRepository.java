package com.donutec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donutec.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

}
