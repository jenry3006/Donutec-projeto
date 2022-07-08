package com.donutec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.donutec.model.Cliente;
import com.donutec.model.Venda;
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.VendaRepository;

@Service
public class ClienteServiceImpl implements ClienteServiceApi {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public Page<Cliente> getAll(Pageable pageable) {
		
		return clienteRepo.findAll(pageable);
	}
	
	
	

}
