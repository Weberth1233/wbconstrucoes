package br.unitins.wbconstrucoes.application;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	
	private Util() {
		
	}
	
	public static String hashSHA256(String valor) {
		return DigestUtils.sha256Hex(valor);
	}
	public static void addErrorMessage(String mensagem) {
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,null));
	}
	public static void addInfoMessage(String mensagem) {
		FacesContext.getCurrentInstance().
			addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,null));
	}
	public static void AddWarningMessage(String mensagem) {
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,mensagem,null));
	}
}
