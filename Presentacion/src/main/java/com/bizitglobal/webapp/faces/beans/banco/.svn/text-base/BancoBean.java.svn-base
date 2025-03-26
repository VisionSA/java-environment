package com.bizitglobal.webapp.faces.beans.banco;

import java.util.List;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.BancoDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.BancoException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.service.BancoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Validador;


public class BancoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(BancoBean.class);
	private Banco banco;
	private List bancos;
	private String tituloLargo = "Tarjeta Fiel - Bancos";
	private String tituloCorto = "Alta de bancos";

	private boolean desdeListar = false;
	private Long idBancoHidden;

	private BancoService bancoService = generalService.getBancoService();


	public BancoBean() {
		super();
		this.banco = new Banco();
		this.idBancoHidden = null;
		this.bancos = null;
		this.desdeListar = false;
		this.tituloLargo = "Tarjeta Fiel - Bancos";
		this.tituloCorto = "Alta de bancos";
	}


	public String inicializar() {
		borrar();
		return "altaBancos";
	}


	public boolean isDesdeListar() {
		return desdeListar;
	}


	public void setDesdeListar(boolean desdeListar) {
		this.desdeListar = desdeListar;
	}


	public Long getIdBancoHidden() {
		return idBancoHidden;
	}


	public void setIdBancoHidden(Long idBancoHidden) {
		this.idBancoHidden = idBancoHidden;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	public List getBancos() {
		return bancos;
	}


	public void setBancos(List bancos) {
		this.bancos = bancos;
	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}


	public void borrar() {
		super.borrarBaseBean();
		error.borrar();

		this.banco = new Banco();
		this.idBancoHidden = null;
		this.bancos = null;
		this.desdeListar = false;
		this.tituloLargo = "Tarjeta Fiel - Bancos";
		this.tituloCorto = "Alta de bancos";
	}


	public String cancelar() {
		String ret = null;

		if (desdeListar == true) {
			ret = listar();
		} else {
			borrar();
			ret = "inicio";
		}
		return ret;
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNuloVacio(banco.getCodigo())) {
			error.agregar(Error.BANCO_CODIGO_REQUERIDO);
		}
		if (Validador.esNuloVacio(banco.getDescripcion())) {
			error.agregar(Error.BANCO_DESCRIPCION_REQUERIDO);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String grabar() {
		String result = null;

		Banco bco = null;
		Banco bcosel = null;
		List tmp = null;

		if (validar()) {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("codigo", Filtro.LIKEXS, banco.getCodigo());
			try {
				tmp = bancoService.getBancos(filtro);
				if (desdeListar)
					bcosel = bancoService.leerBanco(idBancoHidden);

				if (!tmp.isEmpty() && (!desdeListar || !tmp.contains(bcosel))) {
					error.agregar(Error.BANCO_CODIGO_EXISTENTE);
					return null;
				}
			} catch (BancoException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}

		try {
			if (desdeListar == true) {
				bcosel.setCodigo(banco.getCodigo());
				bcosel.setDescripcion(banco.getDescripcion());
				log.info("Actualizando banco->" + bcosel);
				bancoService.actualizarBanco(bcosel);
				result = this.listar();
			} else {
				log.info("Grabando banco->" + banco);
				bancoService.grabarBanco(banco);
				this.borrar();
				result = null;
			}
		} catch (BancoDuplicateException e1) {
			e1.printStackTrace();
			result = "altaFallaBD";
		} catch (BancoException e2) {
			e2.printStackTrace();
			result = "fallaGeneral";
			error.agregar("");
		} catch (Exception e3) {
			e3.printStackTrace();
			result = "accesoDenegado";
			error.agregar("");
		}

		return result;
	}


	public String listar() {
		String result = "listadoBancos";
		this.tituloCorto = "Listado de bancos";

		try {
			bancos = bancoService.getBancos();
			log.info("Obteniendo lista de bancos...");
		} catch (BancoException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}

		return result;
	}


	public String editar() {
		String result = null;

		try {
			this.desdeListar = true;
			banco = bancoService.leerBanco(idBancoHidden);
			log.info("Actualizando banco->" + banco);
		} catch (BancoException e1) {
			result = "fallaGeneral";
		} catch (Exception e2) {
			result = "accesoDenegado";
		}
		return "altaBancos";
	}


	public String eliminar() {
		String result = "";

		try {
			bancoService.borrarBanco(idBancoHidden);
			log.info("Banco eliminado->codigo:" + idBancoHidden);
			result = this.listar();
		} catch (BancoException e1) {
			e1.printStackTrace();
			result = "fallaGeneral";
		} catch (Exception e2) {
			e2.printStackTrace();
			result = "accesoDenegado";
		}

		return result;
	}

}