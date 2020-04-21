package com.example.angular.oito.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.angular.oito.models.entity.Cliente;
import com.example.angular.oito.models.entity.Region;
import com.example.angular.oito.models.services.IClienteService;
import com.example.angular.oito.models.services.IUploadFileService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/api")
public class ClienteRestController {

//	@Autowired
//	private Logger log = org.slf4j.LoggerFactory.getLogger(ClienteRestController.class);

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	@GetMapping("/clientes/{id}")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public ResponseEntity<?> getCliente(@PathVariable Long id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<String, Object>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error el realizar la consulta");
			response.put("error", ex.getMessage().concat(" : ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente con el id ".concat(id.toString()).concat(" no exist"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(x -> "El campo '" + x.getField() + "' " + x.getDefaultMessage()).collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException ex) {

			response.put("mensaje", "Error el realizar el insert");
			response.put("error", ex.getMessage().concat(" : ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "el cliente ha sido creado con éxito.");
		response.put("cliente", clienteNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/clientes/upload")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap();
		Cliente cliente = clienteService.findById(id);

		if (!archivo.isEmpty()) {
			
			String nombreArchivo  = null;
			
			try {
				
				nombreArchivo =	uploadFileService.copiar(archivo);
			
			} catch (IOException ex) {
				response.put("mensaje", "Error al subir la imagem: " + nombreArchivo);
				response.put("error", ex.getMessage().concat(" : ").concat(ex.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = cliente.getFoto();

			uploadFileService.elimar(nombreFotoAnterior);

			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "has subido correctamente la imagem: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente, BindingResult result,
			@PathVariable Long id) {

		Cliente atual = clienteService.findById(id);
		Cliente clienteUpdated = null;

		Map<String, Object> response = new HashMap<String, Object>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(x -> "El campo '" + x.getField() + "' " + x.getDefaultMessage()).collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (atual == null) {
			response.put("mensaje", "El cliente con el id ".concat(id.toString()).concat(" no exist"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			atual.setApellido(cliente.getApellido());
			atual.setNombre(cliente.getNombre());
			atual.setEmail(cliente.getEmail());
			atual.setRegion(cliente.getRegion());

			clienteUpdated = clienteService.save(atual);
		} catch (DataAccessException ex) {

			response.put("mensaje", "Error el actualizar el cliente");
			response.put("error", ex.getMessage().concat(" : ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "el cliente ha sido actualizado con éxito.");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Long id) {

		Cliente cliente = clienteService.findById(id);
		String nombreFotoAnterior = cliente.getFoto();

		uploadFileService.elimar(nombreFotoAnterior);

		clienteService.delete(id);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;
		
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/regiones")
	@Secured("ROLE_ADMIN")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
}
