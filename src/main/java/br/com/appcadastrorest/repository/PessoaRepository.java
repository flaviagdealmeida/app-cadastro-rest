package br.com.appcadastrorest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.appcadastrorest.data.entity.Pessoa;

@Repository
public interface PessoaRepository extends 
					JpaRepository<Pessoa, Long> {
	
	@Modifying
	@Query("UPDATE Pessoa p SET p.ativo = false WHERE p.id =:id")
	void inativarPessoa(@Param("id") Long id);

	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
	Page<Pessoa>buscarPessoaPorNome(@Param("nome") String nome, Pageable pageable);
	
	
	
}
