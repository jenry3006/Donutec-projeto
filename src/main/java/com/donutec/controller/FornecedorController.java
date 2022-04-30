package com.donutec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		fornecedorRepo.save(fornecedor);
		
		ra.addFlashAttribute("success", "Fornecedor cadastrado com sucesso!");
		
		return "redirect:/fornecedores/cadastrar";
	}

}