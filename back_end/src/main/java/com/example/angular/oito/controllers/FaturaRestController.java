package com.example.angular.oito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.angular.oito.models.entity.Fatura;
import com.example.angular.oito.models.entity.Produto;
import com.example.angular.oito.models.services.IClienteService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/api")
public class FaturaRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/faturas/{id}")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@ResponseStatus(HttpStatus.OK)
	public Fatura show(@PathVariable Long id) {
		return clienteService.findFaturaById(id);
	}
	
	@PostMapping("/faturas")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	public Fatura criar(@RequestBody Fatura fatura) {
		return clienteService.saveFatura(fatura);	
	}
	
	@GetMapping("/faturas/filtrar-prdutos/{nome}")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public List<Produto> filtrarProdutos(@PathVariable String nome) {
		return clienteService.findProdutoByNome(nome);
	}
	
	@DeleteMapping("/faturas/{id}")
	@Secured({"ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.deleteFaturaById(id);
	}
}
