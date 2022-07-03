package com.donutec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.donutec.model.Cliente;
import com.donutec.model.Venda;
import com.donutec.model.ItensVenda;
import com.donutec.model.Produto;
import com.donutec.repository.ClienteRepository;
import com.donutec.repository.VendaRepository;
import com.donutec.repository.ItensVendaRepository;
import com.donutec.repository.ProdutoRepository;

@Controller
public class VendaController {

	private List<ItensVenda> itensVenda = new ArrayList<ItensVenda>();
	private Venda venda = new Venda();

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItensVendaRepository itensVendaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/venda")
	public ModelAndView chamarVenda() {
		ModelAndView mv = new ModelAndView("venda/ponto-venda");
		mv.addObject("listaProdutos", produtoRepository.findAll());
		calcularTotal();
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);

		return mv;
	}

	@GetMapping("/registro-venda")
	public ModelAndView registroVendas() {
		ModelAndView mv = new ModelAndView("venda/registro-venda");
		mv.addObject("vendas", vendaRepository.findAll());
		return mv;
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizar() {
		ModelAndView mv = new ModelAndView("venda/finalizar");
		calcularTotal();
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);
		mv.addObject("listaCliente", clienteRepository.findAll());
		return mv;
	}

	@PostMapping("/finalizar/confirmar")
	public ModelAndView confirmarVenda(String formaPagamento, Cliente cliente, String enderecoAdicional, String observacao) {
		ModelAndView mv = new ModelAndView("/dashboard");
		venda.setCliente(cliente);
		venda.setFormaPagamento(formaPagamento);
		venda.setEnderecoAdicional(enderecoAdicional);
		venda.setObservacao(observacao);
		vendaRepository.save(venda);

		for (ItensVenda c : itensVenda) {
			c.setVenda(venda);
			itensVendaRepository.saveAndFlush(c);
		}

		itensVenda = new ArrayList<>();
		venda = new Venda();
		return mv;
	}

	private void calcularTotal() {
		venda.setValorTotal(0.);
		for (ItensVenda it : itensVenda) {
			venda.setValorTotal(venda.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping("/alterar-quantidade/{id}/{acao}")
	public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {

		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId().equals(id)) {
				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));

				} else if (acao.equals(0)) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				}
				break;
			}
		}
		return "redirect:/venda";
	}

	@GetMapping("/remover-produto/{id}")
	public String removerProduto(@PathVariable Long id) {

		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId() == id) {
				itensVenda.remove(it);
				break;
			}
		}
		return "redirect:/venda";
	}

	@GetMapping("/adicionar-venda/{id}")
	public String adicionarVenda(@PathVariable Long id) {
		Optional<Produto> prod = produtoRepository.findById(id);
		Produto produto = prod.get();
		int count = 0;
		for (ItensVenda it : itensVenda) {

			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(0.);

				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				System.out.println(it.getValorTotal());
				count = 1;
				break;
			}
		}

		if (count == 0) {
			ItensVenda item = new ItensVenda();
			item.setProduto(produto);
			item.setValorUnitario(produto.getValor());
			item.setQuantidade(item.getQuantidade() + 1);
			System.out.println(item.getValorTotal());
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario()));
			itensVenda.add(item);
		}

		return "redirect:/venda";
	}

}
