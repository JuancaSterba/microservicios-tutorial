package com.auto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.service.entidades.Auto;
import com.auto.service.repositorio.AutoRepository;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository autoRepository;
	
	public List<Auto> getAll(){
		return autoRepository.findAll();
	}
	
	public Auto getAutoById(Long id) {
		return autoRepository.findById(id).orElse(null);
	}
	
	public Auto save(Auto auto) {
		Auto nuevoAuto = autoRepository.save(auto);
		return nuevoAuto;
	}
	
	public List<Auto> byUsuarioId(Long usuarioId){
		return autoRepository.findByUsuarioId(usuarioId);
	}

}
