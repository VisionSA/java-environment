package com.bizit.consulta.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import com.bizit.consulta.web.entity.PermisoWeb;
import com.bizit.consulta.web.entity.UsuarioComercioWeb;
import com.bizit.consulta.web.entity.UsuarioPermisoWeb;
import com.bizit.consulta.web.entity.UsuarioWeb;
import com.bizit.consulta.web.exception.LogInException;
import com.bizit.consulta.web.vo.UsuarioComercioVO;

@RemotingDestination
@Service
public class UsuarioWebService {

	@Autowired
	private EmailService emailService;

	public UsuarioWeb create(UsuarioWeb usuarioWeb) {
		usuarioWeb.persist();
		return usuarioWeb;
	}

	public UsuarioWeb show(Long id) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		return UsuarioWeb.findUsuarioWeb(id);
	}

	public List<UsuarioWeb> list() {
		return UsuarioWeb.findAllUsuarioWebs();
	}

	public List<UsuarioWeb> listPaged(Integer page, Integer size) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			return UsuarioWeb.findUsuarioWebEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
		} else {
			return list();
		}
	}

	public UsuarioWeb update(UsuarioWeb usuarioWeb) {
		if (usuarioWeb == null)
			throw new IllegalArgumentException("A usuarioWeb is required");
		usuarioWeb.merge();
		return usuarioWeb;
	}

	public void remove(Long id) {
		if (id == null)
			throw new IllegalArgumentException("An Identifier is required");
		UsuarioWeb.findUsuarioWeb(id).remove();
	}

	public UsuarioWeb logIn(UsuarioWeb usuarioWeb) throws LogInException {

		UsuarioWeb resultado = null;

		if (usuarioWeb == null) {
			throw new LogInException("El usuario no puede ser nulo.");
		}

		try {
			resultado = UsuarioWeb.logInUsuarioWeb(usuarioWeb);
		} catch (Exception e) {
			throw new LogInException("El usuario no existe.");
		}

		if (resultado.getActivo() == null || resultado.getActivo() == false) {
			throw new LogInException(
					"El usuario esta inactivo, por favor pongase en contacto con el Soporte de Tarjeta Fiel.");
		}

		resultado.setUltimoLogin(new Date());
		resultado.merge();
		return resultado;
	}

	public UsuarioComercioWeb validarUsuarioComercio(
			UsuarioComercioVO usuarioComercioVO) throws LogInException {
		UsuarioComercioWeb resultado = null;

		if (usuarioComercioVO == null) {
			throw new LogInException("Los datos no pueden ser nulos");
		}

		StringBuffer query = new StringBuffer();

		query.append("select ind.c_id_individuo, codcom.c_id_cod_comercio ");
		query.append("from t_vis_gen_empresas emp ");
		query.append("inner join t_vis_gen_suc_empresas sucEmp ");
		query.append("on emp.c_id_empresa = sucemp.c_id_empresa ");
		query.append("inner join t_vis_tra_cod_comercios codCom ");
		query.append("on sucemp.c_id_sucursal= codcom.c_id_sucursal ");
		query.append("inner join t_vis_eva_actividades act ");
		query.append("on act.c_id_sucursal = codcom.c_id_sucursal ");
		query.append("inner join t_vis_eva_individuos ind ");
		query.append("on ind.c_cuil=act.c_cuil ");
		query.append("where emp.c_cuit= :nroCuit ");
		query.append("and codcom.c_codigo_posnet= :codPosnet ");
		query.append("and ind.c_nro_documento = :dni ");
		query.append("and act.c_tipo in ('X','A') ");

		try {
			Object[] obj = (Object[]) UsuarioWeb
					.entityManager()
					.createNativeQuery(query.toString())
					.setParameter("nroCuit", usuarioComercioVO.getCuit())
					.setParameter("codPosnet",
							usuarioComercioVO.getCodigoPosnet())
					.setParameter("dni", usuarioComercioVO.getDni())
					.getSingleResult();

			if (obj != null && obj.length > 0) {
				resultado = new UsuarioComercioWeb();
				resultado.setIdIndividuo(((BigDecimal) obj[0]).longValue());
				resultado
						.setIdCodigoComercio(((BigDecimal) obj[1]).longValue());
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			throw new LogInException(
					"Los datos ingresados no son correctos o usted no est� autorizado a crearse un usuario para este comercio");
		}
		return resultado;
	}

	public void registrarUsuarioComercio(UsuarioWeb usuarioWeb)
			throws LogInException, UsuarioWebException {

		if (usuarioWeb == null) {
			throw new LogInException("Los datos no pueden ser nulos");
		}

		if (usuarioWeb.getUsuarioComercioWeb() != null) {
			UsuarioComercioWeb ucw = null;
			try {
				ucw = UsuarioComercioWeb.findUsuarioComercioWeb(usuarioWeb
						.getUsuarioComercioWeb());
			} catch (Exception e) {// El usuarioComercioWeb no existe puedo
									// crear el UsuarioWeb

				usuarioWeb.setFechaCreacion(new Date());
				usuarioWeb.setActivo(true);
				// Save
				usuarioWeb.persist();

				UsuarioPermisoWeb up = new UsuarioPermisoWeb();
				up.setUsuario(usuarioWeb);
				up.setPermiso(PermisoWeb.findPermisoWeb(new Long(
						PermisoWeb.ID_SWF_COMERCIO)));
				up.persist();

				emailService.sendMessage("Registro correcto de Comercio en Tarjeta Fiel",
						usuarioWeb.getEmail(),
						crearMensajeNuevoUsuario(usuarioWeb));

			}
			if (ucw != null) {
				throw new LogInException(
						"El usuario no puede crearse porque el individuo ya se ha registrado para este comercio,");
			}
		} else {
			throw new LogInException(
					"Sólo se pueden crear usuarios que fueron validados");
		}

	}

	public boolean recuperarPassword(String email) throws UsuarioWebException {

		if (email == null) {
			throw new UsuarioWebException("Debe especificar un email");
		}

		try {
			UsuarioWeb uw = UsuarioWeb.findUsuarioWebByEmail(email);

			emailService.sendMessage("Recuperar Contraseña", email,
					crearMensajeRecuperarPass(uw));

		} catch (UsuarioWebException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsuarioWebException(
					"No hay un usuario registrado para el email ingresado");
		}

		return true;
	}

	public boolean recuperarPassword(UsuarioComercioVO usuarioComercioVO,
			String email) throws UsuarioWebException {

		try {
			emailService
					.sendMessage("Recuperar Usuario y Contraseña",
							email,
							crearMensajeRecuperarPass(UsuarioWeb
									.findUsuarioWebByUsuarioComercioWeb(UsuarioComercioWeb
											.findUsuarioComercioWeb(validarUsuarioComercio(usuarioComercioVO)))));
		} catch (UsuarioWebException e) {
			throw e;
		} catch (Exception e) {
			throw new UsuarioWebException(
					"Los datos no pertenecen a un usuario activo");
		}

		return true;

	}

	private String crearMensajeRecuperarPass(UsuarioWeb uw) {
		StringBuffer resultado = new StringBuffer();
/*@I4923*/		//resultado.append("Subject: Recuperar Contraseña\n");
		resultado.append("<html>\n");
		resultado.append("<head>\n");
		resultado.append("<meta http-equiv='content-type' content='text/html; charset=ISO-8859-1'>\n");
		resultado.append("</head>\n");
		resultado.append("<body>\n");
		resultado.append("<p><h1><b>Datos de ingreso al sistema</b></h1></p>\n");
		resultado.append("<p><b>Usuario:</b><font color='blue' size='4px'> " + uw.getEmail() + "</font></p>\n");
		resultado.append("<p><b>Contrase&ntilde;a:</b><font color='blue' size='4px'>Contrase&ntilde;a: " + uw.getPassword() + "</font></p>\n");
		resultado.append("<p>Saludos Cordiales.</p>\n");
		resultado.append("<p><h2><b>Soporte TARJETA FIEL</b></h2></p>\n");
		resultado.append("</body>\n");
		resultado.append("</html>\n");
		return resultado.toString();
	}

	private String crearMensajeNuevoUsuario(UsuarioWeb usuarioWeb) {
		StringBuffer resultado = new StringBuffer();
		resultado.append("Mensaje Bienvenida\n\n");
		resultado.append("Usuario: " + usuarioWeb.getEmail() + "\n");
/*@F4923*/		resultado.append("Contrase&#241;a: " + usuarioWeb.getPassword() + "\n\n");
		resultado.append("Saludos Cordiales.\n\n");
		resultado.append("Soporte TARJETA FIEL");
		return resultado.toString();
	}

	public List<PermisoWeb> getPermisos(UsuarioWeb uw)
			throws UsuarioWebException {

		if (uw == null) {
			throw new UsuarioWebException("Usuario nulo");
		}
		try {
			return PermisoWeb.findPermisoWeb(uw);
		} catch (Exception e) {
			throw new UsuarioWebException(
					"El usuario no tiene permisos para acceder a la aplicación");
		}

	}

}
