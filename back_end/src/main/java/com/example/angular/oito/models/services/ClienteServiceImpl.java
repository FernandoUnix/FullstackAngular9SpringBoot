package com.example.angular.oito.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.angular.oito.models.dao.IClienteDao;
import com.example.angular.oito.models.dao.IFaturaDao;
import com.example.angular.oito.models.dao.IProdutoDao;
import com.example.angular.oito.models.entity.Cliente;
import com.example.angular.oito.models.entity.Fatura;
import com.example.angular.oito.models.entity.Produto;
import com.example.angular.oito.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFaturaDao faturaDao;
	
	@Autowired
	private IProdutoDao produtoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	public Fatura findFaturaById(Long id) {
		return faturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Fatura saveFatura(Fatura fatura) {
		return faturaDao.save(fatura);
	}

	@Override
	@Transactional
	public void deleteFaturaById(Long id) {
		faturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findProdutoByNome(String nome) {
		return produtoDao.findByNomeContainingIgnoreCase(nome);
	}
}
