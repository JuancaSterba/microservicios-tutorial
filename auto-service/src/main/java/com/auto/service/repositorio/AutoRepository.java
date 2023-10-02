package com.auto.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.service.entidades.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{
	
	List<Auto> findByUsuarioId(Long usuarioId);

}
