package com.donutec.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.donutec.model.Produto;
import com.donutec.repository.ProdutoRepository;

@Component
public class ProdutoConverter implements Converter<String[], List<Produto>>{

	@Autowired
	ProdutoRepository repo;
	
	@Override
	public List<Produto> convert(String[] source) {
		
		List<Produto> produtos = new ArrayList<>();
		
		for (String id : source) {
			
			Produto teste =  repo.getById(Long.valueOf(id));
		
			produtos.add(teste);
			System.out.println(id);
			System.out.println(source);
			System.out.println(produtos);
			convert(source);
		}
		return produtos;
	}
	
	
}

