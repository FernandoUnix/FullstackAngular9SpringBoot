package com.example.angular.oito.models.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.angular.oito.models.entity.Cliente;
import com.example.angular.oito.models.entity.Fatura;
import com.example.angular.oito.models.entity.Produto;
import com.example.angular.oito.models.entity.Region;

public interface IClienteService {

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public Cliente save(Cliente cliente);

	public void delete(Long id);

	public Cliente findById(Long id);

	public List<Region> findAllRegiones();

	public Fatura findFaturaById(Long id);

	public Fatura saveFatura(Fatura fatura);

	public void deleteFaturaById(Long id);
	
	
	public List<Produto> findProdutoByNome(String nome);
	
}