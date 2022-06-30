package com.donutec.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/listar")
	public String abrirLista(Ingrediente ingrediente, ModelMap model, Produto produto) {
		model.addAttribute("ingredientes", ingredienteRepo.findAll());
		model.addAttribute("produtos", produtoRepo.findAll());
		model.addAttribute("ingrediente", ingrediente);
		return "/ingrediente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Ingrediente ingrediente, BindingResult br, Produto produto, RedirectAttributes ra) {
		
		if(br.hasErrors()) {
			return "/ingrediente/cadastro";
		}
		
		ingredienteRepo.save(ingrediente);
		ra.addFlashAttribute("success", "Ingrediente cadastrado com sucesso!");
	
		return "redirect:/ingredientes/cadastrar";
	}
	
	@GetMapping("/deletar")
	public String deletar(@PathParam(value = "id") Long id, Model model){
		ingredienteRepo.deleteById(id);
		return "redirect:/ingredientes/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("ingrediente", ingredienteRepo.getById(id));
		return "/ingrediente/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Ingrediente ingrediente, BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			return "/ingrediente/cadastro";
		}
		
		ingredienteRepo.saveAndFlush(ingrediente);
		
		ra.addFlashAttribute("success", "Ingrediente editado com sucesso.");
		return "redirect:/ingredientes/cadastrar";
	}
	
	@PostMapping("/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("ingredientes", ingredienteRepo.findByNomeContaining(nome));
		return "ingrediente/lista";
	}
	
	@GetMapping("relatorio")
	public String abrirRelatorio(Model model){
		
		model.addAttribute("ingredientes", ingredienteRepo.findAll());

		return "ingrediente/relatorio";
	}
	
	@ModelAttribute("produtosLista")
	public List<Produto> produtosCheckbox(){
		return produtoRepo.findAll();
	}
	
}
