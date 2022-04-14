package br.com.appcadastrorest.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

//vamos extender de um filtro generico e pegar o metodo de implementação
public class JwtTokenFilter extends GenericFilterBean {

	
	//injetamos a classe provider
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
	
		this.tokenProvider = tokenProvider;
	}


	//o filtro serve para filtrar  se há um token devolvido pelo cabeçalho 
	// trabalhado no nosso resolverToken
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		
		if (token != null && tokenProvider.validateToken(token)) {
			Authentication auth = tokenProvider.getAuthentication(token);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
		
	}

}
