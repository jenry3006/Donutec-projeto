package com.donutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donutec.model.ItensVenda;

public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long>{
	
	@Query("select v from ItensVenda v where v.venda.id = ?1")
	List<ItensVenda> buscarVendaId(Long id);
	

}
