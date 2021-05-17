package br.com.zupacademy.lucasmiguins.casadocodigo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long>{
	
	Optional<Autor> findByEmail(String email);
}
