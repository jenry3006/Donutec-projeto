package com.donutec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donutec.model.ItensVenda;
import com.donutec.repository.ItensVendaRepository;

@Service
public class ItensVendaService {
	
	@Autowired
	private ItensVendaRepository itensVendaRepository;
	
	public Optional<ItensVenda> buscarItensVendaid(Long id) {
		return itensVendaRepository.findById(id);
	}
	
	public boolean itensVendaContemProduto(Long id) {
		if (buscarItensVendaid(id).get().getProduto().getId().equals(null)){
			System.out.println("teste em itensVendarepository");
			return false;
		}
		
		return true;
	}
	
	

	

}
