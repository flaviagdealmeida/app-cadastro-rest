package br.com.appcadastrorest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.appcadastrorest.adapter.DozerAdapter;
import br.com.appcadastrorest.data.entity.Pessoa;
import br.com.appcadastrorest.data.vo.v1.PessoaVO;
import br.com.appcadastrorest.exception.ResourceNotFoundException;
import br.com.appcadastrorest.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;

	// Agora vamos usar nossa classe de conversão de dados
	private PessoaVO converteParaPessoaVO(Pessoa entity) {
		return DozerAdapter.parseObject(entity, PessoaVO.class); // Pode ser a classe PessoaAdapter existe uma diferença
																	// no meu
	}

	public PessoaVO inserir(PessoaVO pessoaVO) {
		var entity = DozerAdapter.parseObject(pessoaVO, Pessoa.class);
		var vo = DozerAdapter.parseObject(repository.save(entity), PessoaVO.class);
		return vo;
	}

	public Page<PessoaVO> buscarTodos(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::converteParaPessoaVO);
	}

	public PessoaVO buscarPorId(Long idPessoaVO) {
		var entity = repository.findById(idPessoaVO)
				.orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
		return DozerAdapter.parseObject(entity, PessoaVO.class);
	}

	public Page<PessoaVO> buscarPessoaPorNome(String nome, Pageable pageable){
		var page = repository.buscarPessoaPorNome(nome, pageable);
		return page.map(this::converteParaPessoaVO);
		
	}
	
	
	public PessoaVO atualizar(PessoaVO pessoaVO) {
		var entity = repository.findById(pessoaVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
		entity.setNome(pessoaVO.getNome());
		entity.setSobrenome(pessoaVO.getSobrenome());
		entity.setNomeSocial(pessoaVO.getNomeSocial());
		entity.setEmail(pessoaVO.getEmail());

		var vo = DozerAdapter.parseObject(repository.save(entity), PessoaVO.class);
		return vo;
	}

	@Transactional
	public PessoaVO inativarPessoa(Long id) {
		repository.inativarPessoa(id);
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
		return DozerAdapter.parseObject(entity, PessoaVO.class);
	}

	public void deletar(Long idPessoaVO) {
		var entity = repository.findById(idPessoaVO)
				.orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
		repository.delete(entity);
	}

}
