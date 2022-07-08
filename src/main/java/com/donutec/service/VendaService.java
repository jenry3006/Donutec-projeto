package com.donutec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donutec.model.Venda;
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Venda> buscarVendaid(Long id) {
		return vendaRepository.findById(id);
	}
	
	/*public boolean vendaContemCliente(Long id) {
		if (buscarVendaid(id).get().getCliente().getId()){
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean cargoContemFuncionario(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}*/

}
