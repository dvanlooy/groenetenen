package be.vdab.web;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// enkele imports ...
@Component
@SessionScope
class VoorkeurImpl implements Voorkeur, Serializable {
	private static final long serialVersionUID = 1L;
	private String foto; // je maakt een getter en een setter
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}