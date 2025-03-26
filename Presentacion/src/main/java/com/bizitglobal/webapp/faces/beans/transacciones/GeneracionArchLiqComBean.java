package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.LibroMayorFondosBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class GeneracionArchLiqComBean extends BaseBean {
	private static final Logger log = Logger
			.getLogger(LibroMayorFondosBean.class);
	public static int renglon = 0;
	private Long idCodComercioHidden;
	private CodComercio codComercio;
	private CodComercio codcomercioFiltro;
	private List listLiqComercio = new ArrayList();
	private List estadoItems = new ArrayList();
	private Date fecDesde;
	private Date fecHasta;
	private List tablaListDetLiquidacion;


	public GeneracionArchLiqComBean() {
		super();
		estadoItems = Util
				.cargarSelectItemMascara(CodComercio.estadoStaticList);
	}


	public Long getIdCodComercioHidden() {
		return idCodComercioHidden;
	}


	public void setIdCodComercioHidden(Long idCodComercioHidden) {
		this.idCodComercioHidden = idCodComercioHidden;
	}


	public CodComercio getCodComercio() {
		return codComercio;
	}


	public void setCodComercio(CodComercio codComercio) {
		this.codComercio = codComercio;
	}


	public CodComercio getCodcomercioFiltro() {
		return codcomercioFiltro;
	}


	public void setCodcomercioFiltro(CodComercio codcomercioFiltro) {
		this.codcomercioFiltro = codcomercioFiltro;
	}


	public List getListLiqComercio() {
		return listLiqComercio;
	}


	public void setListLiqComercio(List listLiqComercio) {
		this.listLiqComercio = listLiqComercio;
	}


	public List getEstadoItems() {
		return estadoItems;
	}


	public void setEstadoItems(List estadoItems) {
		this.estadoItems = estadoItems;
	}


	public Date getFecDesde() {
		return fecDesde;
	}


	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}


	public Date getFecHasta() {
		return fecHasta;
	}


	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}


	public List getTablaListDetLiquidacion() {
		return tablaListDetLiquidacion;
	}


	public void setTablaListDetLiquidacion(List tablaListDetLiquidacion) {
		this.tablaListDetLiquidacion = tablaListDetLiquidacion;
	}


	@Override
	public void borrar() {
		error.borrar();
		this.tablaListDetLiquidacion = new ArrayList();
		codcomercioFiltro = new CodComercio();
		fecDesde = null;
		fecHasta = new Date();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Generar Archivo de Liquidación de Comercios";
	}


	public String listarDetalleLiqquidacion() {

		tablaListDetLiquidacion.clear();
		try {
			if (codcomercioFiltro.getCodigoPosnet() != null && fecDesde != null
					&& fecHasta != null) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(fecHasta);
				cal.add(Calendar.DATE, 1);

				// TODO: Agregar el tema del filtrado
				Filtro filtro = new Filtro();

				if (codcomercioFiltro.getCodigoPosnet() != null
						&& codcomercioFiltro.getCodigoPosnet().compareTo("") != 0) {
					filtro.agregarCampoOperValor("codComercio.codigoPosnet",
							Filtro.IGUAL, codcomercioFiltro.getCodigoPosnet());
				}

				filtro
						.agregarCampoOperValor("codComercio.estado",
								Filtro.IGUAL, "'"
										+ codcomercioFiltro.getEstado() + "'");

				filtro.agregarCampoOperValor("liquidacion.fechaLiquidacion",
						Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fecDesde));

				filtro.agregarCampoOperValor("liquidacion.fechaLiquidacion",
						Filtro.MENOR_IGUAL, Filtro.getTO_DATE(cal.getTime()));

				filtro.agregarOrderBy("idLiqComercio");

				Iterator iterator = transaccionesService
						.getLiqComercioService().getLiqComercio(filtro)
						.iterator();

				while (iterator.hasNext()) {
					tablaListDetLiquidacion.add(new LiquidacionWrapper(
							(LiqComercio) iterator.next()));
				}
			} else {
				error
						.agregar("Ingrese el valor a 'Codigo Posnet' y 'Fechas' para relizar la busqueda.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String generarArchLiquidaciones(LiqComercio liqComercio) {
		String result = null;
		try {
			result = transaccionesService.getArchivoCuponesService().generarArchivo(liqComercio);
		} catch (Exception e) {
			log.info(e.getMessage());
			error.agregar("Se produjo un Error al generar el archivo .txt");
		}

		return result;
	}


	public String cancelar(ActionEvent event) {
		borrar();
		return "inicio";
	}


	@Override
	public String inicializar() {

		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "generacionArchLiqCome";
	}


	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}

	public class LiquidacionWrapper {
		private LiqComercio liqComercio;


		public LiquidacionWrapper(LiqComercio liqComercio) {
			super();
			this.liqComercio = liqComercio;
		}


		public LiqComercio getLiqComercio() {
			return liqComercio;
		}


		public void setLiqComercio(LiqComercio liqComercio) {
			this.liqComercio = liqComercio;
		}


		public String generar() {
			String dir = null;
			dir = generarArchLiquidaciones(liqComercio);
			// Abrir archivo generado
			try {
				HttpServletResponse response = Session.getResponse();
				FileInputStream archivo2 = new FileInputStream(dir);
				int longitud = archivo2.available();
				byte[] datos = new byte[longitud];
				archivo2.read(datos);
				archivo2.close();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + dir + "\"");
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(datos, 0, longitud);
				ouputStream.flush();
				ouputStream.close();
				Session.responseComplete();
			} catch (Exception e) {
				error.agregar("Error al generar el archivo");
				e.getMessage();
				e.printStackTrace();
			}
			return null;
		}
	}

}