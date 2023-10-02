package com.moto.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moto.service.entidades.Moto;

public interface MotoRepository extends JpaRepository<Moto, Long>{
	
	List<Moto> findByUsuarioId(Long usuarioId);

}
