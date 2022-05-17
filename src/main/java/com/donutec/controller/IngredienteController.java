package com.donutec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donutec.model.Fornecedor;
import com.donutec.model.Ingrediente;
import com.donutec.model.Produto;
import com.donutec.repository.FornecedorRepository;
import com.donutec.repository.IngredienteRepository;
import com.donutec.repository.ProdutoRepository;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@Autowired
	FornecedorRepository fornecedorRepo;
	
	@Autowired
	IngredienteRepository ingredienteRepo;
	
	@GetMapping("/cadastrar")
	public String abrirCadastro(Ingrediente ingrediente) {
		return "/ingrediente/cadastro";
	}
	
	/*//Altere o value e method de acordo com o necessário
	@RequestMapping(value="caminhoDoMeuFomulario", method=RequestMethod.POST)
	public String form(@RequestParam("dias") List<String> dias) {
	        //aqui você faz o processamento que quiser
	    return "seujsp";
	}*/
	
	@PostMapping("/salvar")
	public String salvar(Ingrediente ingrediente) {
		
		ingredienteRepo.save(ingrediente);
	
		return "redirect:/ingredientes/cadastrar";
	}
	

	@ModelAttribute("produtosLista")
	public List<Produto> produtosCheckbox(){
		return produtoRepo.findAll();
	}
	
	/*@ModelAttribute("fornecedores")
	public List<Fornecedor> fornecedoresCheckbox(){
		return fornecedorRepo.findAll();
	}*/
	

}
