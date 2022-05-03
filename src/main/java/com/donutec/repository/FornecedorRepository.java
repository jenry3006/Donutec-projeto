package com.donutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donutec.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	
	List<Fornecedor> findByNomeContaining(String nome);
	
	

}
