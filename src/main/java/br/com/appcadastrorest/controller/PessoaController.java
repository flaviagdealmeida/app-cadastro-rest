package br.com.appcadastrorest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.appcadastrorest.data.vo.v1.PessoaVO;
import br.com.appcadastrorest.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Pessoa Endpoint")
@RestController
@RequestMapping("api/pessoa/v1")
public class PessoaController {

	@Autowired
	PessoaService service;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todas as pessoas")
	@GetMapping(produces = {"application/json", "application/xml"})
	
	public ResponseEntity<CollectionModel<PessoaVO>> exibirPessoas(
			
			/*
			 * envia uma requisição e ela retorna a informação no corpo atraves de uma
			 * sequencia de parametros que estão listados abaixo com valores setados como
			 * padrão
			 */
			
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<PessoaVO> pessoasVO = service.buscarTodos(pageable);
		pessoasVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PessoaController.class)
					.exibirPessoaPorId(p.getKey()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(pessoasVO));
	}
	
	@CrossOrigin({"localhost:8080","http://www.fgatech.com.br"})
	@Operation(summary="Lista pessoa através do Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public PessoaVO exibirPessoaPorId(@PathVariable("id") Long idPessoa) {
		PessoaVO pessoaVO =  service.buscarPorId(idPessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class)
				.exibirPessoaPorId(idPessoa)).withSelfRel());
		return pessoaVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar pessoas por nome")
	@GetMapping(value="/buscarPorNome/{nome}",
			produces = {"application/json", "application/xml"})
	public ResponseEntity<CollectionModel<PessoaVO>> exibirPessoasPorNome(
			@PathVariable("nome") String nome,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
		
		Page<PessoaVO> pessoasVO = service.buscarPessoaPorNome(nome, pageable);
		pessoasVO
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PessoaController.class)
					.exibirPessoaPorId(p.getKey()))
					.withSelfRel()
					)
			);
		return ResponseEntity.ok(CollectionModel.of(pessoasVO));
	}
	
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Insere dados de uma nova pessoa")
	@PostMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public PessoaVO inserirPessoa(@RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO =  service.inserir(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class)
				.exibirPessoaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Atualiza dados de uma pessoa")
	@PutMapping(produces = {"application/json", "application/xml"},
								consumes = {"application/json", "application/xml"})
	public PessoaVO atualizarPessoa(@RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO = service.atualizar(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class)
				.exibirPessoaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary = "Inativa pessoas com o id especificado")
	@PatchMapping(value="/{id}", produces = {"application/json", "application/xml"})
	public PessoaVO  inativarPessoas(@PathVariable("id") Long id) {
	
		PessoaVO pessoaVO = service.inativarPessoa(id);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).exibirPessoaPorId(id)).withSelfRel());
		return pessoaVO;
		
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Remove dados de uma pessoa através do Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> 
	excluirPessoa(@PathVariable("id") Long idPessoa){
		service.deletar(idPessoa);
		return ResponseEntity.ok().build();
	}
	

}
