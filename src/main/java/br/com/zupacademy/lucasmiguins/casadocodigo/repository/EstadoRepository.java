package br.com.zupacademy.lucasmiguins.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;

public interface EstadoRepository extends CrudRepository<Estado, Long>{

	List<Estado> findByPais(Pais pais);
	
	Estado findByNome(String nome);
}
