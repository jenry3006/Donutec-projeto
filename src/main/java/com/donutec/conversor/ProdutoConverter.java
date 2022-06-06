package com.donutec.conversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.donutec.model.Produto;
import com.donutec.repository.ProdutoRepository;

@Component
public class ProdutoConverter implements Converter<String[], List<Produto>> {

	@Autowired
	ProdutoRepository repo;

	@Override
	public List<Produto> convert(String[] source) {
		
		List<Produto> produtos = new ArrayList<>();

		Produto produto = new Produto();

		for (String id : source) {
			
			Long teste = Long.valueOf(id);
			
			produto = repo.findById(teste).get();

			
			produtos.add(produto);
		}

		return produtos;
	}

}
