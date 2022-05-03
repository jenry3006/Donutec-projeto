package com.donutec.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donutec.model.Fornecedor;
import com.donutec.repository.FornecedorRepository;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	FornecedorRepository fornecedorRepo;
	
	@GetMapping("/cadastrar")
	public String abrirCadastro(Fornecedor fornecedor) {
		return "/fornecedor/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Fornecedor fornecedor, BindingResult br, RedirectAttributes ra) {
		
		if(br.hasErrors()) {
			return "/fornecedor/cadastro";
		}
		
		fornecedorRepo.save(fornecedor);
		
		ra.addFlashAttribute("success", "Fornecedor cadastrado com sucesso!");
		
		return "redirect:/fornecedores/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(Fornecedor fornecedor, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorRepo.findAll());
		return "/fornecedor/lista";
	}
	
	@GetMapping(value = "deletar")
	public String deletar(@PathParam(value="id") Long id, ModelMap model) {
		
		fornecedorRepo.deleteById(id);
		return "redirect:/fornecedores/listar";
	}
	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("fornecedor", fornecedorRepo.getById(id));
		return "/fornecedor/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Fornecedor fornecedor, BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			return "/fornecedor/cadastro";
		}
		
		fornecedorRepo.saveAndFlush(fornecedor);
		
		ra.addFlashAttribute("success", "Fornecedor editado com sucesso.");
		return "redirect:/fornecedores/cadastrar";
	}
	
	@PostMapping("/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorRepo.findByNomeContaining(nome));
		return "fornecedor/lista";
	}
	

}