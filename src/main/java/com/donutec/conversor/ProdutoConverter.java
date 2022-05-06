package com.donutec.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.donutec.model.Produto;
import com.donutec.repository.ProdutoRepository;

@Component
public class ProdutoConverter implements Converter<String, Produto>{

	@Autowired
	ProdutoRepository repo;
	
	@Override
	public Produto convert(String source) {
		Long id = Long.valueOf(source);
		return repo.getById(id);
	}
	
}
