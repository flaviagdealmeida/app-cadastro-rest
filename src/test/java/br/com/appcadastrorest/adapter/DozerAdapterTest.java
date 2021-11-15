package br.com.appcadastrorest.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.appcadastrorest.data.entity.Pessoa;
import br.com.appcadastrorest.data.vo.v1.PessoaVO;


public class DozerAdapterTest {
	
	private Pessoa mockEntity(Integer numero) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(0L);
		pessoa.setNome("Nome Teste"+numero);
		pessoa.setSobrenome("Sobrenome Teste"+numero);
		pessoa.setNomeSocial("NomeSocial Teste"+numero);
		pessoa.setEmail("email "+numero);
		return pessoa;
	}
	
	private PessoaVO mockVO(Integer numero) {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setId(0L);
		pessoa.setNome("Nome Teste"+numero);
		pessoa.setSobrenome("Sobrenome Teste"+numero);
		pessoa.setNomeSocial("NomeSocial Teste"+numero);
		pessoa.setEmail("email "+numero);
		return pessoa;
	}
	
	private List<Pessoa> mockEntityList(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for(int i = 0; i < 14; i++) {
			pessoas.add(mockEntity(i));
		}
		return pessoas;
	}
	
	private List<PessoaVO> mockVOList(){
		List<PessoaVO> pessoas = new ArrayList<PessoaVO>();
		for(int i = 0; i < 14; i++) {
			pessoas.add(mockVO(i));
		}
		return pessoas;
	}
	
	@Test
	public void parseEntityToVOTest() {
		PessoaVO output = 
				DozerAdapter.parseObject(mockEntity(0), PessoaVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Nome Teste0", output.getNome());
		Assert.assertEquals("Sobrenome Teste0", output.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", output.getNomeSocial());
		Assert.assertEquals("email 0", output.getEmail());
	}
		
	@Test
	public void parseEntityListToVOListTest() {
		List<PessoaVO> outputList = 
				DozerAdapter.parseListObjects
				(mockEntityList(), PessoaVO.class);
		PessoaVO outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Nome Teste0", outputZero.getNome());
		Assert.assertEquals("Sobrenome Teste0", outputZero.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", outputZero.getNomeSocial());
		Assert.assertEquals("email 0", outputZero.getEmail());
	}
	
	@Test
    public void parseVOToEntityTest() {
        Pessoa output = DozerAdapter.parseObject(mockVO(0), Pessoa.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Nome Teste0", output.getNome());
        Assert.assertEquals("Sobrenome Teste0", output.getSobrenome());
        Assert.assertEquals("NomeSocial Teste0", output.getNomeSocial());
        Assert.assertEquals("email 0", output.getEmail());
    }
    

    @Test
    public void parserVOListToEntityListTest() {
        List<Pessoa> outputList = DozerAdapter.parseListObjects(mockVOList(), Pessoa.class);
        Pessoa outputZero = outputList.get(0);
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Nome Teste0", outputZero.getNome());
        Assert.assertEquals("Sobrenome Teste0", outputZero.getSobrenome());
        Assert.assertEquals("NomeSocial Teste0", outputZero.getNomeSocial());
        Assert.assertEquals("email 0", outputZero.getEmail());
	
    }
	
	
}
