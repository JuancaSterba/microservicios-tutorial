package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Auto;

@FeignClient(name = "auto-service", url = "http://localhost:8002/auto")
public interface AutoFeignClient {
	
	@PostMapping("/")
	public Auto save(@RequestBody Auto auto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Auto> getAutos(@PathVariable("usuarioId")Long id);

}
