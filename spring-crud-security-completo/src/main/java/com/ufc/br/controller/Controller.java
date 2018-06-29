package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.service.ProdutoService;

@org.springframework.stereotype.Controller
public class Controller{

	@Autowired
	private ProdutoRepository pr;
	
	@Autowired
	private ProdutoService ps;
	
	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.GET)
	public @ResponseBody String form() {
		
		return "produto/formProduto";
	}
	

	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
	public @ResponseBody String form(Produto produto) {
		
		pr.save(produto);
		
		return "redirect:/cadastrarProduto";
	}
	
	@RequestMapping("/produtos")
	public ModelAndView ListaProdutos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Produto> produtos = pr.findAll();
		mv.addObject("produto", produtos);
		return mv;
	}
	/*
	public ModelAndView salvarProdutos(Produto produto) {
		pessoaService
	}*/
}
