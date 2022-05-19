package com.donutec.service;

import org.springframework.stereotype.Service;

import com.donutec.model.Produto;
import com.donutec.model.Venda;

@Service
public class VendaService {
	
	
	public double calculoTotal(Venda venda ,Produto produto) {
		double total;
		total = venda.getQuantidade() * produto.getValor();
		
		return total;
	}

}
