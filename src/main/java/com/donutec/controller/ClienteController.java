package com.donutec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donutec.model.Cliente;
import com.donutec.model.Venda;
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.VendaRepository;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	List<Cliente> clientes = new ArrayList<>();
	
	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	private VendaRepository vendaRepository;
	
	List<Venda> vendas = new ArrayList<>();
	
	/*@Autowired
	private ClienteServiceApi clienteServiceAPI;
	*/

	@RequestMapping("cadastro")
	public String abrirCadastro(Cliente cliente, Model model) {
		model.addAttribute("cliente", cliente);
		return"cliente/cadastro";
	}

	@RequestMapping("lista")
	public String lista(Cliente cliente, Model model) {
		clientes = clienteRepo.findAll();
 		model.addAttribute("clientes", clientes);
		return"cliente/lista";
	}
	
	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult bindingResult,RedirectAttributes ra ,Model model) {
		if (bindingResult.hasErrors()) {
			return "/cliente/cadastro";
		} 
		
		/*if (cliente.getDataNascimento()==null) {
			clienteRepo.save(cliente);
		} else {
			if(cliente.getDataNascimento().isAfter(LocalDate.now())) {
				cliente.setDataNascimento(ra);
				model.addAttribute("mensagem", "Informe uma data válida para nascimento.");
				 return abrirCadastro(cliente, model);
			}
		}	*/ 
		
		clienteRepo.save(cliente);
		
		ra.addFlashAttribute("success", "Cadastro realizado com sucesso!");
		return "redirect:/cliente/cadastro";
	}
	
	@GetMapping(value="editar")
	private String editarCadastro(@PathParam(value="id") Long id,Model model) {
		model.addAttribute("cliente", clienteRepo.getById(id));
		return "cliente/cadastro";
	}
	
	@PostMapping("editar/salvar")
	private String atualizar(@Valid Cliente cliente,BindingResult bindingResult ,Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(cliente, model);
		}
		
		clienteRepo.save(cliente);
		
		return "redirect:/index";
	}
	
	/*@GetMapping("/removercliente/{idcliente}")
	private ModelAndView excluir(@PathParam("idcliente")Long idcliente) {
		clienteRepo.deleteById(idcliente);
		ModelAndView modelAndView = new ModelAndView("cliente/cadastro");
		modelAndView.addObject("clientes", clienteRepo.findAll());
		
		return modelAndView;
	}*/
	
	@GetMapping(value="deletar")
	public String deletar(@PathParam(value = "id") Long id, Model model){
		
		for(Venda v : vendas) {
			if(v.getCliente().getId()==id) {
				System.out.println("esse cliente ja tem venda");
			} else {
				clienteRepo.deleteById(id);
			}
		}
		
		
		System.out.println("teste delete");
		
		
		return "redirect:/cliente/lista";
	}
	
	@PostMapping("**/pesquisarCliente")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		modelAndView.addObject("clientes", clienteRepo.findClienteByName(nomepesquisa));
		
		clientes = clienteRepo.findClienteByName(nomepesquisa);
		
		return modelAndView;
	}
	/*@PostMapping("consultar")
	public String consultar(String consulta, Model model) {
		clientes = clienteRepo.findByNome(consulta);
		clientes = clienteRepo.findByNomeContaining(consulta);
		model.addAttribute("clientes", clientes);
		return"cliente/lista";
	}*/

	@GetMapping("relatorio")
	public String abrirRelatorio(Model model){
		//clientes = clienteRepo.findAll();
		model.addAttribute("clientes", clientes);

		return "cliente/relatorio";
	}
	
	/*@GetMapping("lista")
	public String findAll(@RequestParam Map<String, Object> params, Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<Cliente> pageCliente = clienteServiceAPI.getAll(pageRequest);
		
		int totalPage = pageCliente.getTotalPages();
		
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		
		}
		
		model.addAttribute("clientes", pageCliente.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next",page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
	
		
		return "cliente/lista";
		
	}*/
	
	
}	
