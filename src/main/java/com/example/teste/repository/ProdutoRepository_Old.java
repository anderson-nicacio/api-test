package com.example.teste.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.example.teste.model.Produto;
import com.example.teste.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_Old {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer ultimoId = 0;
	
	/** 
	 * Metodo para retornar uma lista de produtos.
	 * @return Lista de produtos.
	 * */
	public List<Produto> obterTodos(){
		
		return produtos;
	}
	
	/** 
	 * Metodo que retorna o produto encontrado pelo seu id.
	 * @param id do produto que será localizado.
	 * @return Retorna um produto caso seja encontrado.
	 * */
	public Optional<Produto> obterPorId(Integer id) {
		return produtos
				.stream()
				.filter(produto -> produto.getId() == id)
				.findFirst();
		
	}
	
	/** 
	 * Metodo para adicionar produto na lista.
	 * @param produto que será adicionado.
	 * @return Retorna o produto que foi adicionado a lista.
	 * */
	public Produto cadastrar(Produto produto) {
		
		ultimoId+= 1;
		
		produto.setId(ultimoId);
		produtos.add(produto);
		
		return produto;
	}
	
	/**
	 * Metodo para deletar o produto por id.
	 * @param id do produto a ser deletado.
	 * */
	public void deletar(Integer id) {
		produtos.removeIf(produto -> produto.getId() == id);
	}
	
	/** 
	 * Metodo para atualizar o produto na lista.
	 * @param produto que será atualizado.
	 * @return Retorna o produto após atualizar a lista.*/
	public Produto atualizar(Produto produto) {
		//Encontrar produto na lista
		
		Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
		
		if(produtoEncontrado.isEmpty()) {
			throw new ResourceNotFoundException("Produto referênte ao ID não encontrado");
		}
		
		// Deletar produto aintigo
		deletar(produto.getId());
		
		// Adicionar o produto atualizado na lista
		produtos.add(produto);
		
		return produto;
	}
}






