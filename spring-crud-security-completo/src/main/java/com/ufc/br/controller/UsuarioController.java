package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Usuario;
import com.ufc.br.service.UsuarioService;

public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/cadastrarUsuario")
	public ModelAndView formularioCliente() {
		ModelAndView mv = new ModelAndView("visitante/formCliente");
		mv.addObject("cliente", new Usuario());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarCliente(Usuario usuario) {
		usuarioService.adicionarUsuario(usuario);
		ModelAndView mv = new ModelAndView("visitante/formUsuario");
		mv.addObject("mensagem", "Show!");

		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("visitante/login");
		return mv;
}
}
