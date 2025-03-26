package com.bizit.consulta.web.entity;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "t_vis_web_usuarios_comercios")
public class UsuarioComercioWeb {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id")
	private Long id;

	@NotNull
	@Column(name = "c_id_individuo")
	private Long idIndividuo;

	@NotNull
	@Column(name = "c_id_codigo_comercio")
	private Long idCodigoComercio;
	
	@Transient
/*@I4923*/	private List<String> codigoPosnet;

	@Transient
	private String codigosPosnet;
	
	public void setCodigosPosnet(String codigosPosnet) {
		this.codigosPosnet = codigosPosnet;
	}
	
	public String getCodigosPosnet(){
		return codigosPosnet;
	}

	public List<String> getCodigoPosnet() {
		return codigoPosnet;
	}

	public void setCodigoPosnet(List<String> codigoPosnet) {
		this.codigoPosnet = codigoPosnet;
/*@F4923*/		this.codigosPosnet = getCodigosPosnetFn();
	}

	@PersistenceContext
	transient EntityManager entityManager;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdIndividuo() {
		return idIndividuo;
	}

	public void setIdIndividuo(Long idIndividuo) {
		this.idIndividuo = idIndividuo;
	}

	public Long getIdCodigoComercio() {
		return idCodigoComercio;
	}

	public void setIdCodigoComercio(Long idCodigoComercio) {
		this.idCodigoComercio = idCodigoComercio;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public UsuarioComercioWeb merge() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		UsuarioComercioWeb merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			UsuarioComercioWeb attached = this.entityManager.find(
					this.getClass(), this.id);
			this.entityManager.remove(attached);
		}
	}

	@Transactional
	public void flush() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.flush();
	}

	public static UsuarioComercioWeb findUsuarioComercioWeb(Long id) {
		if (id == null)
			return null;
		return entityManager().find(UsuarioComercioWeb.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<UsuarioComercioWeb> findUsuarioComercioWebEntries(
			int firstResult, int maxResults) {
		return entityManager()
				.createQuery("select o from UsuarioComercioWeb o")
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<UsuarioComercioWeb> findAllUsuarioComercioWebs() {
		return entityManager()
				.createQuery("select o from UsuarioComercioWeb o")
				.getResultList();
	}

	public static final EntityManager entityManager() {
		EntityManager em = new UsuarioWeb().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static UsuarioComercioWeb findUsuarioComercioWeb(
			UsuarioComercioWeb usuarioComercioWeb) {

		StringBuffer query = new StringBuffer();
		query.append("select ucw ");
		query.append("from UsuarioComercioWeb ucw ");
		query.append("where ucw.idIndividuo = :idIndividuo ");
		query.append("and ucw.idCodigoComercio = :idCodigoComercio");

		return (UsuarioComercioWeb) entityManager()
				.createQuery(query.toString())
				.setParameter("idIndividuo",
						usuarioComercioWeb.getIdIndividuo())
				.setParameter("idCodigoComercio",
						usuarioComercioWeb.getIdCodigoComercio())
				.getSingleResult();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ");
		sb.append("IdIndividuo: ").append(getIdIndividuo()).append(", ");
		sb.append("IdCodigoComercio: ").append(getIdCodigoComercio())
				.append(", ");
		return sb.toString();
	}
	
	/**
	 * @id: 4923
	 * Method: getCodigosPosnetFn
	 * Description: 
	 * @return
	 */
	public String getCodigosPosnetFn(){
		StringBuffer codigos = new StringBuffer();
		if (codigoPosnet == null){
			return "";
		}
		Iterator<String> codIt = codigoPosnet.iterator();
		while (codIt.hasNext())
		{
			codigos.append(codIt.next());
			codigos.append(", ");
		}
		
		codIt = null;
		return codigos.substring(0, codigos.length()-2);
	}

}
