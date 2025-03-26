package com.bizit.consulta.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "t_vis_web_usuarios",uniqueConstraints=@UniqueConstraint(columnNames="c_email"))
public class UsuarioWeb {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;	
	
	@NotNull
    @Column(name = "c_email")
    @Size(max = 50)
    private String email;	
	
	@NotNull
    @Column(name = "c_password")
    @Size(min = 8, max = 12)
    private String password;

    @NotNull
    @Column(name = "c_activo")
    private Boolean activo;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "LL")
    @Column(name = "c_ultimo_login")
    private Date ultimoLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "LL")
    @Column(name = "c_fecha_creacion")
    private Date fechaCreacion;
    
    @ManyToOne(targetEntity = UsuarioComercioWeb.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id_usuario_comercio_web")
    private UsuarioComercioWeb usuarioComercioWeb;    
    

	public UsuarioComercioWeb getUsuarioComercioWeb() {
		return usuarioComercioWeb;
	}

	public void setUsuarioComercioWeb(UsuarioComercioWeb usuarioComercioWeb) {
		this.usuarioComercioWeb = usuarioComercioWeb;
	}



	@PersistenceContext
    transient EntityManager entityManager;	

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
            UsuarioWeb attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public UsuarioWeb merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UsuarioWeb merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new UsuarioWeb().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countUsuarioWebs() {
        return ((Number) entityManager().createQuery("select count(o) from UsuarioWeb o").getSingleResult()).longValue();
    }

	@SuppressWarnings("unchecked")
    public static List<UsuarioWeb> findAllUsuarioWebs() {
        return entityManager().createQuery("select o from UsuarioWeb o").getResultList();
    }

	public static UsuarioWeb findUsuarioWeb(Long id) {
        if (id == null) return null;
        return entityManager().find(UsuarioWeb.class, id);
    }

	@SuppressWarnings("unchecked")
    public static List<UsuarioWeb> findUsuarioWebEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from UsuarioWeb o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public Boolean getActivo() {
        return this.activo;
    }

	public void setActivo(Boolean activo) {
        this.activo = activo;
    }

	public Date getUltimoLogin() {
        return this.ultimoLogin;
    }

	public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

	public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

	public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
	
	public static UsuarioWeb logInUsuarioWeb(UsuarioWeb usuarioWeb) {
        if (usuarioWeb == null) return null;
        
        StringBuffer query = new StringBuffer();
        query.append("select uw ");
        query.append("from UsuarioWeb uw ");
        query.append("where uw.email = :email ");
        query.append("and uw.password = :password");
        
        UsuarioWeb uw = (UsuarioWeb) entityManager().createQuery(query.toString()).setParameter("email", usuarioWeb.getEmail()).setParameter("password", usuarioWeb.getPassword()).getSingleResult();
        
/*@I4923*/        query = new StringBuffer();
//        query.append(" SELECT COM.C_CODIGO_POSNET FROM T_VIS_TRA_COD_COMERCIOS COM "); 
//        query.append(" where COM.C_ID_COD_COMERCIO = :codComercio ");
        query.append(" SELECT COM.C_CODIGO_POSNET FROM T_VIS_TRA_COD_COMERCIOS COM "); 
        query.append(" INNER JOIN T_VIS_GEN_SUC_EMPRESAS SUC ON SUC.C_ID_SUCURSAL = COM.C_ID_SUCURSAL ");
        query.append(" INNER JOIN T_VIS_EVA_ACTIVIDADES ACT ON ACT.C_ID_SUCURSAL = SUC.C_ID_SUCURSAL ");
        query.append(" WHERE SUC.C_ID_EMPRESA = ( ");
        query.append(" SELECT EMP.C_ID_EMPRESA FROM T_VIS_GEN_EMPRESAS EMP ");
        query.append(" INNER JOIN T_VIS_GEN_SUC_EMPRESAS SUC1 ON SUC1.C_ID_EMPRESA = EMP.C_ID_EMPRESA ");
        query.append(" INNER JOIN T_VIS_TRA_COD_COMERCIOS COM1 ON COM1.C_ID_SUCURSAL = SUC1.C_ID_SUCURSAL ");
        query.append(" WHERE COM1.C_ID_COD_COMERCIO = :codComercio) ");
        query.append(" AND ACT.C_CUIL = ( ");
        query.append(" SELECT IND.C_CUIL FROM T_VIS_EVA_INDIVIDUOS ind where IND.C_ID_INDIVIDUO = :idIndividuo) ");
        
        List<String> codPosnet = (List<String>) entityManager().createNativeQuery(query.toString()).setParameter("codComercio", uw.getUsuarioComercioWeb().getIdCodigoComercio())
/*@F4923*/        						.setParameter("idIndividuo", uw.getUsuarioComercioWeb().getIdIndividuo()).getResultList(); 
        uw.getUsuarioComercioWeb().setCodigoPosnet(codPosnet);
        return uw;
        //return (UsuarioWeb) entityManager().createQuery(query.toString()).setParameter("email", usuarioWeb.getEmail()).setParameter("password", usuarioWeb.getPassword()).getSingleResult();
    }
	
	

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Password: ").append(getPassword()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Activo: ").append(getActivo()).append(", ");
        sb.append("UltimoLogin: ").append(getUltimoLogin()).append(", ");
        sb.append("FechaCreacion: ").append(getFechaCreacion()).append(", ");
        sb.append("UsuarioComercioWeb: ").append(getUsuarioComercioWeb());
        return sb.toString();
    }

	public static UsuarioWeb findUsuarioWebByEmail(String email) {
		StringBuffer query = new StringBuffer();
		query.append("select uw ");
		query.append("from UsuarioWeb uw ");
		query.append("where uw.email = :email ");

		return (UsuarioWeb) entityManager().createQuery(query.toString())
				.setParameter("email", email).getSingleResult();
	}

	public static UsuarioWeb findUsuarioWebByUsuarioComercioWeb(
			UsuarioComercioWeb ucw) {
		StringBuffer query = new StringBuffer();
		query.append("select uw ");
		query.append("from UsuarioWeb uw ");
		query.append("where uw.usuarioComercioWeb = :ucw ");

		return (UsuarioWeb) entityManager().createQuery(query.toString())
				.setParameter("ucw", ucw).getSingleResult();
	}
}
