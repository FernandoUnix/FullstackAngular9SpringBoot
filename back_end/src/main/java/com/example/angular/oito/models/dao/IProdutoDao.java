package com.example.angular.oito.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.angular.oito.models.entity.Produto;

public interface IProdutoDao extends CrudRepository<Produto, Long> {

	//@Query("select p from Produto p where p.nome like %?1%")
	//public List<Produto> findByNome(String nome);
	
	public List<Produto> findByNomeContainingIgnoreCase(String nome);
	
}
