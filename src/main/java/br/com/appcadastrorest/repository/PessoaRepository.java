package br.com.appcadastrorest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appcadastrorest.data.entity.Pessoa;

public interface PessoaRepository extends 
					JpaRepository<Pessoa, Long> {

}
