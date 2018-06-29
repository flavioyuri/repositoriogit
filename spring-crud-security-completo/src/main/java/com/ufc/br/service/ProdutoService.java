package com.ufc.br.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository pr; 
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adicionarProduto(Produto produto, MultipartFile imagem) {
		produto.setNome(produto.getNome());
		produto.setPreco(produto.getPreco());
		
		String caminho = "images/" + produto.getNome() + ".jpg";
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		pr.save(produto);
	}
	
	public List<Produto> todosOsProdutos(){
		
		return pr.findAll();
	}
	
	public Produto buscarPorId(long id) {
		return pr.getOne(id);
	}
	
	public void removerProduto(long id) {
		pr.deleteById(id);
	}
	
	public Produto findNaQuery(long id) {
		return manager.createQuery("select distinct(x) from Produto x where x.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
}
