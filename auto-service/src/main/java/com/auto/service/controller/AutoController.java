package com.auto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.service.entidades.Auto;
import com.auto.service.servicio.AutoService;

@RestController
@RequestMapping("/auto")
public class AutoController {
	
	@Autowired
	private AutoService autoService;
	
	@GetMapping("/")
	public ResponseEntity<List<Auto>> listarAutos(){
		List<Auto> autos = autoService.getAll();
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Auto> obtenerAuto(@PathVariable("id")Long id){
		Auto auto = autoService.getAutoById(id);
		if (auto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(auto);
	}
	
	@PostMapping("/")
	public ResponseEntity<Auto> guardarAuto(@RequestBody Auto auto){
		Auto nuevoAuto = autoService.save(auto);
		return ResponseEntity.ok(nuevoAuto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Auto>> listarAutosPorUsuario(@PathVariable("usuarioId")Long id){
		List<Auto> autos = autoService.byUsuarioId(id);
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}
}
