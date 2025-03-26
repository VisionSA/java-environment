package com.bizit.consulta.web.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.bizit.consulta.web.entity.UsuarioComercioWeb;

@Service
@RemotingDestination
public class UsuarioComercioWebService {
	
	
	public UsuarioComercioWeb create(UsuarioComercioWeb usuarioComercioWeb) {
		usuarioComercioWeb.persist();
		return usuarioComercioWeb;
	}

	public UsuarioComercioWeb show(Long id) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		return UsuarioComercioWeb.findUsuarioComercioWeb(id);
	}

	public List<UsuarioComercioWeb> list() {		
		return UsuarioComercioWeb.findAllUsuarioComercioWebs();
	}

	public List<UsuarioComercioWeb> listPaged(Integer page, Integer size) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			return UsuarioComercioWeb.findUsuarioComercioWebEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
		} else {
			return list();
		}
	}

	public UsuarioComercioWeb update(UsuarioComercioWeb UsuarioComercioWeb) {
		if (UsuarioComercioWeb == null)
			throw new IllegalArgumentException("A UsuarioComercioWeb is required");
		UsuarioComercioWeb.merge();
		return UsuarioComercioWeb;
	}

	public void remove(Long id) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		UsuarioComercioWeb.findUsuarioComercioWeb(id).remove();
	}
	
	

}
