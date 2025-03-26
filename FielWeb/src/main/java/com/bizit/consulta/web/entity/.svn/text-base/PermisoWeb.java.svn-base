package com.bizit.consulta.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;


@Configurable
@Entity
@Table(name = "t_vis_web_permisos")
public class PermisoWeb {
	
	public static final long ID_SWF_COMERCIO = 1L;
	
	

    @NotNull
    @Column(name = "c_nombre")
    @Size(max = 50)
    private String nombre;

    @NotNull
    @Column(name = "c_swf")
    @Size(max = 100)
    private String swf;

	public String getNombre() {
        return this.nombre;
    }

	public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getSwf() {
        return this.swf;
    }

	public void setSwf(String swf) {
        this.swf = swf;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PermisoWeb attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public PermisoWeb merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PermisoWeb merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new PermisoWeb().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPermisoWebs() {
        return ((Number) entityManager().createQuery("select count(o) from PermisoWeb o").getSingleResult()).longValue();
    }

	@SuppressWarnings("unchecked")
    public static List<PermisoWeb> findAllPermisoWebs() {
        return entityManager().createQuery("select o from PermisoWeb o").getResultList();
    }

	public static PermisoWeb findPermisoWeb(Long id) {
        if (id == null) return null;
        return entityManager().find(PermisoWeb.class, id);
    }

	@SuppressWarnings("unchecked")
    public static List<PermisoWeb> findPermisoWebEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from PermisoWeb o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Nombre: ").append(getNombre()).append(", ");
        sb.append("Swf: ").append(getSwf());
        return sb.toString();
    }

	@SuppressWarnings("unchecked")
	public static List<PermisoWeb> findPermisoWeb(UsuarioWeb uw) {
		StringBuilder query = new StringBuilder();
		query.append("select upw.permiso ");
		query.append("from UsuarioPermisoWeb upw ");
		query.append("where upw.usuario = :uw ");
		
		return entityManager().createQuery(query.toString()).setParameter("uw", uw).getResultList();
		
	}
}
