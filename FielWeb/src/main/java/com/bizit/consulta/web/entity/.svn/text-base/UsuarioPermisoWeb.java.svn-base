package com.bizit.consulta.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "t_vis_web_usu_perm_web")
public class UsuarioPermisoWeb {

    @NotNull
    @ManyToOne(targetEntity = UsuarioWeb.class)
    @JoinColumn
    private UsuarioWeb usuario;

    @NotNull
    @ManyToOne(targetEntity = PermisoWeb.class)
    @JoinColumn
    private PermisoWeb permiso;

	public UsuarioWeb getUsuario() {
        return this.usuario;
    }

	public void setUsuario(UsuarioWeb usuario) {
        this.usuario = usuario;
    }

	public PermisoWeb getPermiso() {
        return this.permiso;
    }

	public void setPermiso(PermisoWeb permiso) {
        this.permiso = permiso;
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
            UsuarioPermisoWeb attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public UsuarioPermisoWeb merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UsuarioPermisoWeb merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new UsuarioPermisoWeb().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countUsuarioPermisoWebs() {
        return ((Number) entityManager().createQuery("select count(o) from UsuarioPermisoWeb o").getSingleResult()).longValue();
    }

	@SuppressWarnings("unchecked")
    public static List<UsuarioPermisoWeb> findAllUsuarioPermisoWebs() {
        return entityManager().createQuery("select o from UsuarioPermisoWeb o").getResultList();
    }

	public static UsuarioPermisoWeb findUsuarioPermisoWeb(Long id) {
        if (id == null) return null;
        return entityManager().find(UsuarioPermisoWeb.class, id);
    }

	@SuppressWarnings("unchecked")
    public static List<UsuarioPermisoWeb> findUsuarioPermisoWebEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from UsuarioPermisoWeb o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Usuario: ").append(getUsuario()).append(", ");
        sb.append("Permiso: ").append(getPermiso());
        return sb.toString();
    }
}
