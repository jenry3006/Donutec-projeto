package com.donutec.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.ProdutoRepository;
import com.donutec.service.ClienteServiceApi;

@Controller
@RequestMapping("/")
public class AreaVendaController {
	
	List<Cliente> clientes = new ArrayList<>();
	
	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	private ClienteServiceApi clienteServiceAPI;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/area-venda")
	public ModelAndView abrirTemplate() {
		ModelAndView mv = new ModelAndView("/area-venda");
		mv.addObject("listaProdutos", produtoRepository.findAll());
		return mv;
	}

}
	
	

