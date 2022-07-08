package com.donutec.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donutec.model.ItensVenda;
import com.donutec.model.Produto;
import com.donutec.model.Venda;
import com.donutec.repository.ItensVendaRepository;
import com.donutec.repository.ProdutoRepository;
import com.donutec.service.ItensVendaService;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	List<Produto> produtos = new ArrayList<>();
	List<ItensVenda> itensVendaList = new ArrayList<>();

	@Autowired
	ProdutoRepository produtoRepo;
	
	@Autowired
	ItensVendaService itensVendaService;
	
	private static String caminhoImagens = "/Users/Usuario/Documents/Homer Donuts - Estagio 2/images/";
	
	@RequestMapping("cadastro")
	private String abrirCadastro(Produto produto, Model model) {
		return"produto/cadastro";
	}

	@RequestMapping("lista")
	private String lista(Produto produto, Model model){
		produtos = produtoRepo.findAll();
		model.addAttribute("produtos", produtos);
		return "produto/lista";
	}

	@PostMapping("salvar")
	private String salvar(@Valid Produto produto, BindingResult bindingResult, 
			RedirectAttributes ra,Model model,@RequestParam("file") MultipartFile arquivo){
		if (bindingResult.hasErrors()) {
			//model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(produto, model);
		}
		
		produtoRepo.save(produto);
		
		try {
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+String.valueOf(produto.getId())+ arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				//Aqui salva a imagem dentro da pasta imagens que foi criada anteriormente
				produto.setNomeImagem(String.valueOf(produto.getId())+ arquivo.getOriginalFilename());
				produtoRepo.saveAndFlush(produto);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		produtoRepo.save(produto);
		ra.addFlashAttribute("success", "Produto cadastrado com sucesso. teste2");
		
		return "redirect:/produto/cadastro";
	}
	
	@GetMapping("mostrarImagem/{imagem}")
	@ResponseBody
	private byte[] retornarImagem(@PathVariable("imagem") String imagem, Model model) throws IOException {
		File imagemArquivo = new File(caminhoImagens+imagem);
		
		if(imagem!=null || imagem.trim().length() > 0) {
				return Files.readAllBytes(imagemArquivo.toPath());		
		}
		
		return null;
	}
	
	@GetMapping("deletar")
	private String deletar(@PathParam (value="id") Long id, Model model) {
		
		//for(ItensVenda v : itensVendaList) {
		//	if(){
		//		System.out.println("esse produto ja tem venda, teste delete prod id: " + id);
			//} else {
				produtoRepo.deleteById(id);
			//}
		//}
			
		
		
		return"redirect:/produto/lista";
	}
	
	@GetMapping(value="editar")
	private String editarCadastro(@PathParam(value="id") Long id, Model model) {
		
		
		model.addAttribute("produto", produtoRepo.getById(id));
		return"produto/cadastro";
	}
	
	@PostMapping("editar/salvar")
	private String atualizar(@Valid Produto produto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("erros", bindingResult.getAllErrors());
			return abrirCadastro(produto, model);
		}
		
		produtoRepo.save(produto);
		
		return"redirect:/index";
	}
	
	@PostMapping("**/pesquisarProduto")
	private ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomePesquisa) {
		ModelAndView modelAndView = new ModelAndView("produto/lista");
		modelAndView.addObject("produtos", produtoRepo.findByNomeDonutsContaining(nomePesquisa));
		produtos = produtoRepo.findByNomeDonutsContaining(nomePesquisa);
		return modelAndView;
	}

	@GetMapping("relatorio")
	public String abrirRelatorio(Model model){
		model.addAttribute("produtos", produtos);
		return "produto/relatorio";
	}
}
