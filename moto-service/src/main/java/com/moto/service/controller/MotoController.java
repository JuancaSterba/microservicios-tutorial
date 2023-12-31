package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicio.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping("/")
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> motos = motoService.getAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id")Long id){
		Moto moto = motoService.getMotoById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping("/")
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto auto){
		Moto nuevaMoto = motoService.save(auto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuario(@PathVariable("usuarioId")Long id){
		List<Moto> autos = motoService.byUsuarioId(id);
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}

}
