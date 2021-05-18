package br.com.zupacademy.lucasmiguins.casadocodigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
