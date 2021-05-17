package br.com.zupacademy.lucasmiguins.casadocodigo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	Optional<Categoria> findByNome(String nome);
}
