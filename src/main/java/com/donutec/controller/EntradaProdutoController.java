package com.donutec.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donutec.model.EntradaItens;
import com.donutec.model.EntradaProduto;
import com.donutec.model.Produto;
import com.donutec.repository.EntradaItensRepository;
import com.donutec.repository.EntradaProdutoRepository;
import com.donutec.repository.ProdutoRepository;

@Controller
@RequestMapping("estoque")
public class EntradaProdutoController {

	@Autowired
	private EntradaProdutoRepository entradaProdutoRepository;

	@Autowired
	private EntradaItensRepository entradaItensRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();
	
	List<Produto> produtos = new ArrayList<>();
	
	private static String caminhoImagens = "/Users/Usuario/Documents/Homer Donuts - Estagio 2/images/";

	@GetMapping("cadastro")
	public ModelAndView cadastrar(EntradaProduto entrada,
			EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("estoque/entrada");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", this.listaEntrada);
		mv.addObject("entradaItens", entradaItens);
		
		mv.addObject("listaProdutos", produtoRepository.findAll());
		return mv;
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
	
	@GetMapping("produtos")
	public ModelAndView listaEstoque(EntradaProduto entrada,
			EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("estoque/lista");
		
		mv.addObject("produtos", produtoRepository.findAll());
		return mv;
	}
	
	@RequestMapping("lista")
	private String listaEstoque(Produto produto, Model model){
		produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		return "produto/lista";
	}
	
	@PostMapping("salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {
		
		if(acao.equals("itens")) { 
			this.listaEntrada.add(entradaItens);
		} else if(acao.equals("salvar")) {
			entradaProdutoRepository.saveAndFlush(entrada);
			
			for(EntradaItens it:listaEntrada) {
				it.setEntrada(entrada);
				entradaItensRepository.saveAndFlush(it);
				Optional<Produto> prod = produtoRepository.findById(it.getProduto().getId());
				Produto produto = prod.get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				
				produtoRepository.saveAndFlush(produto);
				this.listaEntrada = new ArrayList<EntradaItens>();
				
			}
			return cadastrar(new EntradaProduto(), new EntradaItens());
		}
		
		System.out.println(listaEntrada.size());
		
		return cadastrar(entrada, new EntradaItens());
	}

	@RequestMapping("/lista_venda")
	public String abrirListaVenda(Model model) {
		return "venda/lista_venda";
	}

}
