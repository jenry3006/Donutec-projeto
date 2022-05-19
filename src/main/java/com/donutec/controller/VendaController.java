package com.donutec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donutec.model.Cliente;
import com.donutec.model.Produto;
import com.donutec.model.Venda;
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.ProdutoRepository;
import com.donutec.repository.VendaRepository;
import com.donutec.service.ClienteServiceApi;
import com.donutec.service.VendaService;

@Controller
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	VendaService vendaService;
	
	@RequestMapping("/ponto_venda")
	public String abrirPontoVenda(Model model, Venda venda) {
		return "venda/ponto_venda";
	}
	
	@RequestMapping("/lista_venda")
	public String abrirListaVenda(Model model) {
		return "venda/lista_venda";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid @ModelAttribute("venda") Venda venda,Produto produto ,BindingResult br) {
		if(br.hasErrors()) {
			return "cliente/cadastro";
		}
		double total =  vendaService.calculoTotal(venda,produto);
		venda.setTotal(total);
		System.out.println(vendaService.calculoTotal(venda, produto));
		vendaRepo.save(venda);
		return "redirect:/venda/ponto_venda";
	}
	
	@ModelAttribute("clientes")
	public List<Cliente> clientes(){
		return clienteRepo.findAll();
	}
	
	@ModelAttribute("produtos")
	public List<Produto> produtos(){
		return produtoRepo.findAll();
	}
	
}
