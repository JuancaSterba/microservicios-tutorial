package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.AutoFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorios.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AutoFeignClient autoFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
	
	public List<Auto> getAutos(Long usuarioId){
		List<Auto> autos = restTemplate.getForObject("http://localhost:8002/auto/usuario/" + usuarioId, List.class);
		return autos;
	}
	
	public List<Moto> getMotos(Long usuarioId){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
		return motos;
	} 
	
	public Auto saveAuto(Long usuarioId, Auto auto) {
		auto.setUsuarioId(usuarioId);
		Auto nuevoAuto = autoFeignClient.save(auto);
		return nuevoAuto;
	}
	
	public Moto saveMoto(Long usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}
	
	public Map<String, Object> getVehiculosByUsuario(Long usuarioId){
		Map<String, Object> vehiculos = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if (usuario==null) {
			vehiculos.put("Mensaje", "El usuario no existe");
			return vehiculos;
		}
		
		vehiculos.put("Usuario", usuario);
		
		List<Auto> autos = autoFeignClient.getAutos(usuarioId);
		if (autos.isEmpty()) {
			vehiculos.put("Autos", "El usuario no tiene Autos");
		} else {
			vehiculos.put("Autos", autos);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos.isEmpty()) {
			vehiculos.put("Motos", "El usuario no tiene Motos");
		} else {
			vehiculos.put("Motos", motos);
		}
		
		return vehiculos;
	}

}
