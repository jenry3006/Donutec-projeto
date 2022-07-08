package com.donutec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donutec.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	
	@Query("select c from Venda c where c.cliente.nome like %?1%")
	List<Venda> findClienteByName(String nome);

}
