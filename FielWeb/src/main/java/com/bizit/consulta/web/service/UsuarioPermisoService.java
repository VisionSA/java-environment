package com.bizit.consulta.web.service;

import java.util.List;
import com.bizit.consulta.web.entity.UsuarioPermisoWeb;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

@RemotingDestination
@Service
public class UsuarioPermisoService {

	public UsuarioPermisoWeb create(UsuarioPermisoWeb usuarioPermisoWeb) {
        usuarioPermisoWeb.persist();
        return usuarioPermisoWeb;
    }

	public UsuarioPermisoWeb show(Long id) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        return UsuarioPermisoWeb.findUsuarioPermisoWeb(id);
    }

	public List<UsuarioPermisoWeb> list() {
        return UsuarioPermisoWeb.findAllUsuarioPermisoWebs();
    }

	public List<UsuarioPermisoWeb> listPaged(Integer page, Integer size) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            return UsuarioPermisoWeb.findUsuarioPermisoWebEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
        } else {
            return list();
        }
    }

	public UsuarioPermisoWeb update(UsuarioPermisoWeb usuarioPermisoWeb) {
        if (usuarioPermisoWeb == null) throw new IllegalArgumentException("A usuarioPermisoWeb is required");
        usuarioPermisoWeb.merge();
        return usuarioPermisoWeb;
    }

	public void remove(Long id) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        UsuarioPermisoWeb.findUsuarioPermisoWeb(id).remove();
    }
}
