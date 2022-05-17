package com.donutec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("venda")
public class VendaController {
	
	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@RequestMapping("ponto_venda")
	public String abrirPontoVenda(Model model) {
		return "venda/ponto_venda";
	}
	
	@RequestMapping("lista_venda")
	public String abrirListaVenda(Model model) {
		return "venda/lista_venda";
	}
	
	/*@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Venda> salvar(@RequestBody Venda venda, Cliente cliente, Produto produto){
		clienteRepo.save(cliente);
		produtoRepo.save(produto);
		vendaRepo.save(produto);
		return ;
		
	}*/
	
	
	//@PostMapping("**/pesquisarCliente")
	/*public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		modelAndView.addObject("clientes", clienteRepo.findClienteByName(nomepesquisa));
		modelAndView.addObject("cienteobj", new Cliente());
		clientes = clienteRepo.findByNomeContaining(nomepesquisa);
		
		return modelAndView;
	}*/
	
}
