package com.bizit.consulta.web.service;

import java.util.List;
import com.bizit.consulta.web.entity.PermisoWeb;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

@RemotingDestination
@Service
public class PermisoWebService {

	public PermisoWeb create(PermisoWeb permisoWeb) {
        permisoWeb.persist();
        return permisoWeb;
    }

	public PermisoWeb show(Long id) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        return PermisoWeb.findPermisoWeb(id);
    }

	public List<PermisoWeb> list() {
        return PermisoWeb.findAllPermisoWebs();
    }

	public List<PermisoWeb> listPaged(Integer page, Integer size) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            return PermisoWeb.findPermisoWebEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
        } else {
            return list();
        }
    }

	public PermisoWeb update(PermisoWeb permisoWeb) {
        if (permisoWeb == null) throw new IllegalArgumentException("A permisoWeb is required");
        permisoWeb.merge();
        return permisoWeb;
    }

	public void remove(Long id) {
        if (id == null) throw new IllegalArgumentException("An Identifier is required");
        PermisoWeb.findPermisoWeb(id).remove();
    }
}
