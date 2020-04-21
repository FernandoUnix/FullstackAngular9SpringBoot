package com.example.angular.oito.models.services;

import com.example.angular.oito.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
}
