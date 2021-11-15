package br.com.appcadastrorest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//Agora vamos usar nossa classe de conversão de dados
	
		public PessoaVO inserir(PessoaVO pessoaVO) {
			var entity = DozerAdapter.parseObject(pessoaVO, Pessoa.class);
			var vo = DozerAdapter.parseObject(repository.save(entity), PessoaVO.class);
			return vo;
		}
		
		
		public List<PessoaVO> buscarTodos() {
			return DozerAdapter.parseListObjects(repository.findAll(), PessoaVO.class);
		}
		
		public PessoaVO buscarPorId(Long idPessoaVO) {
			var entity = repository.findById(idPessoaVO)
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			return DozerAdapter.parseObject(entity, PessoaVO.class);
		}
		
		public PessoaVO atualizar(PessoaVO pessoaVO) {
			var entity = repository.findById(pessoaVO.getId())
					.orElseThrow(()-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			entity.setNome(pessoaVO.getNome());
			entity.setSobrenome(pessoaVO.getSobrenome());
			entity.setNomeSocial(pessoaVO.getNomeSocial());
			entity.setEmail(pessoaVO.getEmail());
			
			var vo = DozerAdapter.parseObject(repository.save(entity), PessoaVO.class);
			return vo;
		}
		
		public void deletar(Long idPessoaVO) {
			var entity = repository.findById(idPessoaVO).orElseThrow(()
					-> new ResourceNotFoundException("Não foram encontrados registros com esse Id"));
			repository.delete(entity);
		}
		
	
	
	
	
}
