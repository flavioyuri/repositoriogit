package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Papel;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void adicionarUsuario(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		Papel papel = new Papel();
		papel.setId(1L);
		List<Papel> papels = new ArrayList<Papel>();
		papels.add(papel);
		usuario.setPapel(papels);

		usuarioRepository.save(usuario);
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.getOne(id);
}
}
