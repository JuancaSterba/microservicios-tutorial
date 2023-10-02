package com.usuario.service.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")Long id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario usuarioNuevo = usuarioService.save(usuario);
		return ResponseEntity.ok(usuarioNuevo);
	}
	
	@GetMapping("/{usuarioId}/autos")
	public ResponseEntity<List<Auto>> listarAutos(@PathVariable("usuarioId")Long id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if ( usuario == null ) {
			return ResponseEntity.notFound().build();
		}
		List<Auto> autos = usuarioService.getAutos(id);
		return ResponseEntity.ok(autos);
	}
	
	@GetMapping("/{usuarioId}/motos")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId")Long id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if ( usuario == null ) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/{usuarioId}/auto")
	public ResponseEntity<Auto> guardarAuto(@PathVariable("usuarioId")Long id, @RequestBody Auto auto){
		Auto nuevoAuto = usuarioService.saveAuto(id, auto);
		return ResponseEntity.ok(nuevoAuto);
	}
	
	@PostMapping("/{usuarioId}/moto")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId")Long id, @RequestBody Moto moto){
		Moto nuevaMoto = usuarioService.saveMoto(id, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/{usuarioId}/vehiculos")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") Long id){
		Map<String, Object> vehiculos = usuarioService.getVehiculosByUsuario(id);
		return ResponseEntity.ok(vehiculos);
	}

}
