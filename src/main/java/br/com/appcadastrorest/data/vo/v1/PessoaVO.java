package br.com.appcadastrorest.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"email", "nomeSocial", "sobrenome", "nome", "id"})
public class PessoaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("PessoaId")
	private Long id;
	
	@JsonProperty("first_name")
	private String nome;
	
	@JsonProperty("last_name")
	private String sobrenome;
	
	@JsonProperty("social_name")
	private String nomeSocial;
	
	@JsonProperty("E-mail")
	private String email;
	
	public PessoaVO() {
		
	}
	
	public PessoaVO(Long id, String nome, String sobrenome, String nomeSocial, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nomeSocial = nomeSocial;
		this.email = email;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, nomeSocial, sobrenome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaVO other = (PessoaVO) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(nomeSocial, other.nomeSocial) && Objects.equals(sobrenome, other.sobrenome);
	}
	
	
	
	
	
	
	
}
