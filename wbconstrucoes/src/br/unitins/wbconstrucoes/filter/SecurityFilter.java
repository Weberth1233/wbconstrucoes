package br.unitins.wbconstrucoes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.wbconstrucoes.model.Usuario;

/*Assim vai percorrer todas as paginas no filter*/
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/*"})
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(request, response);
		return;
		
		/*HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		System.out.println(url);
		if(url.equals("/wbconstrucoes/faces/login.xhtml")) {
			chain.doFilter(request, response);
			return;
		}
		/*Criando a sessão e adicionando o usuario nela*/
		/*HttpSession session = httpServletRequest.getSession(false);
		
		Usuario usuario = null;
		/*Se a sessao estiver funcionado eu vou adicionar um usuario a sessão*/
		/*if(session != null) {
			usuario = (Usuario) session.getAttribute("usuarioLogado");
		}
		if(usuario == null) {
			((HttpServletResponse) response).sendRedirect("/wbconstrucoes/faces/login.xhtml");
		}else {
			/*Para seguir o fluxo das paginas
			chain.doFilter(request, response);
			return;
		}*/
	}
	
	@Override
	public void destroy() {
	}
}
