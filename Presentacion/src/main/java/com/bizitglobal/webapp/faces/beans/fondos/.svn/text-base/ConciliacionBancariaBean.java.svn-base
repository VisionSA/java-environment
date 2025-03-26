package com.bizitglobal.webapp.faces.beans.fondos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondoDetalle;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoAcreditacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionBancaria;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.transacciones.exception.FechaFacturacionNulaException;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormatoNoValidoCodAutorizacionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormatoNoValidoCodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormatoNoValidoImporteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ArchivoPosnet;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.PaginaDeRegistros;
import com.bizitglobal.webapp.faces.beans.util.PaginadorPorDemanda;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({ "rawtypes" })
public class ConciliacionBancariaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConciliacionBancariaBean.class);
	private String focoHidden;
	private UploadedFile uploadedFile;
	private String titulo;
	private String periodo;
	private boolean panelAdjuntar;
	private boolean mostrarTablaNoConc;
	private boolean mostrarTablaLotesAutomAbiertos;
	private boolean mostrarResultConc = false;
	private List listCuponesNoConc;
	private boolean conciliado;
	private boolean mostrarCargaManual = false;
	private boolean mostrarEncabezado = false;
	private boolean todos;
	// bandera para
	private boolean primero;
	private ConciliacionBancaria conciliacionBancaria; // un objeto

	private boolean busquedaAvanzada;
	private String tituloNoConciliados = "";
	private PaginaDeRegistros paginador; // este paginador es para presentar los
	private PaginadorPorDemanda pagDeMov;
	// items que se conciliaran
	// manualmente....

	private BufferedWriter bw;
	private List<String> lstIdTransacciones;
	private File respuesta;
	private int cantRechazados;
	private int cantRechazosDefinitivos;
	private int cantConciliada;
	private Date hoy = new Date();
	private List listaRechazadosDefinitivos = new ArrayList();
	private boolean mostrarLinkBusquedaCupNoAsoc;

	private boolean mostrarTablaNoConcCabecera = true;

	private static int numeroAcreditacionDetalleTabla = 0;


	public ConciliacionBancariaBean() {
		error.borrar();
		this.titulo = "";
		borrar();
	}


	public String inicializar() {
		borrar();
		return "conciliacionBancaria";
	}


	// *inicializa segun si el ususario registrado es un cajero
	public String inicializar2() {
		log.info("Ejecutando ==> inicilizando 2 el bean de Conciliacion de cupones.");
		borrar();
		return null;
	}


	private boolean validarCarga() {
		error.borrar();
		popup.borrar();
		if (uploadedFile == null || uploadedFile.equals("")) {
			error.agregar(Error.TRAN_CONCILIACIONES_ARCHIVO_REQUERIDO);
		} else {
			// Valida el formato del archivo.
			try {
				BufferedReader d = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()));
				String cadenaTexto = d.readLine();
				ArchivoAcreditacion archivo = new ArchivoAcreditacion(uploadedFile.getInputStream(), uploadedFile.getName(), new Long(
						uploadedFile.getSize()).intValue(), Session.getOperador());
				AcreditacionFondo acreditacionFondo = archivo.getAcreditacionFondo();

				if (archivo.isFormatoCorrecto())
				{
					// Filtro filtro = new Filtro();
					// filtro.agregarCampoOperValor("nroCliente", Filtro.LIKEXS, acreditacionFondo.getNroCliente());
					// filtro.agregarCampoOperValor("idBanco", Filtro.LIKEXS, acreditacionFondo.getIdBanco());
					// filtro.agregarOrderBy("idAcreditacion");
					// //filtro.agregarCampoOperValor("fechaProcesoCadena", Filtro.LIKEXS, acreditacionFondo.getFechaProcesoCadena());
					// List acreditList = fondosService.getAcreditacionFondoService().getAcreditaciones(filtro);
					//
					// if(acreditList!=null && acreditList.size()>0){
					// AcreditacionFondo acreditacionGuardada = (AcreditacionFondo)acreditList.get(acreditList.size()-1);
					// Long fechaMaximaGuardada = new Long(acreditacionGuardada.getFechaProcesoCadena());
					// Long fechaMaximaArchivo = new Long (acreditacionFondo.getFechaProcesoCadena());
					// if(fechaMaximaGuardada>=fechaMaximaArchivo)
					// {
					// error.agregar("El archivo de acreditaciones ya fue procesado.");
					// }
					// }
					Iterator iter = acreditacionFondo.getAcreditacionesDetalleOrdenado().iterator();
					Long idBanco = acreditacionFondo.getIdBanco();
					while (iter.hasNext()) {
						AcreditacionFondoDetalle detalle = (AcreditacionFondoDetalle) iter.next();
						if (!detalle.getIdBanco().equals(idBanco))
						{
							error.agregar("El archivo tiene registro de otros bancos.");
							break;
						}
					}

				} else {
					error.agregar("El archivo no tiene el formato correcto.");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// catch (IOException e2) {
			// e2.printStackTrace();
			// }
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String cancelarBusqueda() {
		panelAdjuntar = false;
		Session.redirect("/tarjetafiel/fondos/conciliacionBancaria.jsf");
		return "";
	}


	public void borrar() {
		error.borrar();
		tituloLargo = "TARJETA FIEL - Acreditaciones";
		tituloCorto = "Acreditaciones Bancaria";
		listCuponesNoConc = new ArrayList();
		popup.borrar();
		conciliado = false;
		mostrarTablaNoConc = false;
		mostrarTablaLotesAutomAbiertos = false;
		panelAdjuntar = false;
		mostrarResultConc = false;
		mostrarCargaManual = false;
		mostrarEncabezado = false;
		tituloNoConciliados = "";

		paginador = new PaginaDeRegistros();
		// idTipoConcSeleccionada= new Long(0);
		// idTipoAccionSeleccionada= new Long(0);
		primero = true;
		lstIdTransacciones = new ArrayList<String>();
		cantRechazados = 0;
		cantRechazosDefinitivos = 0;
		cantConciliada = 0;
		mostrarLinkBusquedaCupNoAsoc = false;
		this.titulo = "";
		this.periodo = "";

	}


	public String cancelar() {
		borrar();
		return null;
	}


	/**
	 * busca los cupones cargados manual o automaticamente no conciliados ('X')
	 * 
	 * @param event
	 */
	public void buscarNoConciliados(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		// loteID = (String) map.get("idLote");
		// buscarNoConciliados(loteID);
		mostrarTablaNoConc = true;
		mostrarTablaNoConcCabecera = false;
	}


	public String ejecutar2() {
		error.borrar();
		if (validarCarga()) {
			ArchivoPosnet archivoPosnet;
			ArchivoAcreditacion archivoAcreditacion;
			try {
				archivoAcreditacion = new ArchivoAcreditacion(uploadedFile.getInputStream(), uploadedFile.getName(),
						new Long(uploadedFile.getSize()).intValue(), Session.getOperador());

				// Buscamos el banco para mostrar.
				Filtro filtro = new Filtro("idBanco", Filtro.IGUAL, archivoAcreditacion.getAcreditacionFondo().getIdBanco());
				List listBancos = generalService.getBancoService().getBancos(filtro);
				if (listBancos != null && listBancos.size() > 0)
				{
					this.titulo = "Acreditaciones:  " + ((Banco) listBancos.get(0)).getDescripcion();
					this.periodo = "    Desde: " + archivoAcreditacion.getMinFecha() + "  Hasta:" + archivoAcreditacion.getMaxFecha();
				}

				// Filtramos solamente los registros que no tenemos en la base.
				List listAcredDetallesBase = fondosService.getAcreditacionFondoDetalleService()
						.buscarRangoConDatos(archivoAcreditacion.getMinFecha(), archivoAcreditacion.getMaxFecha(),
								archivoAcreditacion.getAcreditacionFondo().getIdBanco());
				archivoAcreditacion.eliminarRegistrosYaGuardados(listAcredDetallesBase);

				if (archivoAcreditacion.getAcreditacionFondo().getAcreditacionesDetalleOrdenado().size() > 0)
				{
					if (archivoAcreditacion.validarCantidadItems())
					{
						this.conciliacionBancaria = fondosService.getAcreditacionFondoService().conciliarAcreditaciones(archivoAcreditacion,
								Session.getOperador());
						this.listarAcreditacionesNoConciliadas();
					} else {
						error.agregar("La cantidad de items del archivo no coincide con lo informado en el archivo.");
						this.mostrarEncabezado = false;
						this.mostrarTablaNoConc = false;
					}
				}
				else {
					error.agregar("El archivo seleccionado no contiene items sin procesar.");
					this.mostrarEncabezado = false;
					this.mostrarTablaNoConc = false;
				}
			} catch (FormatoNoValidoImporteException e1) {
				error.agregar("El proceso de conciliacion no termino correctamente, consulte el archivo erroresTrans.log para obtener mas detalles del error, o comuniquese con el area de sistemas");
				e1.printStackTrace();
			} catch (FormatoNoValidoCodComercioException e1) {
				error.agregar("El proceso de conciliacion no termino correctamente, consulte el archivo erroresTrans.log para obtener mas detalles del error, o comuniquese con el area de sistemas");
				e1.printStackTrace();
			} catch (FormatoNoValidoCodAutorizacionException e1) {
				error.agregar("El proceso de conciliacion no termino correctamente, consulte el archivo erroresTrans.log para obtener mas detalles del error,  o comuniquese con el area de sistemas");
				e1.printStackTrace();
			} catch (FechaFacturacionNulaException e1) {
				error.agregar("El proceso de conciliacion no termino correctamente, consulte el archivo erroresTrans.log para obtener mas detalles del error,  o comuniquese con el area de sistemas");
				e1.printStackTrace();
			} catch (IOException e1) {
				error.agregar("El proceso de conciliacion no termino correctamente, consulte el archivo erroresTrans.log para  obtener mas detalles del error");
				e1.printStackTrace();
			} catch (Exception e) {
				error.agregar("Hubo un error en el proceso de conciliacion");
				e.printStackTrace();
			}
		}
		return null;
	}


	private void listarAcreditacionesNoConciliadas()
	{
		listCuponesNoConc.clear();
		try {

			boolean hayNoEncontrados = false;
			if (conciliacionBancaria != null) {

				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("acreditacionFondo.idAcreditacion", Filtro.IGUAL, conciliacionBancaria.getArchivoAcreditacion()
						.getAcreditacionFondo().getIdAcreditacion());
				filtro.agregarCampoOperValor("conciliado", Filtro.LIKEXS, "N");

				pagDeMov = new PaginadorPorDemanda(filtro, (Paginacion) fondosService.getAcreditacionFondoDetalleService(), listCuponesNoConc, 20,
						error,
						"/tarjetafiel/fondos/conciliacionBancaria.jsf");

				mostrarResultConc = true;
				tituloNoConciliados = "Resultado de la Ejecucion de Acreditaciones";
				mostrarEncabezado = true;
				mostrarTablaLotesAutomAbiertos = true;
				mostrarTablaNoConc = true;
				panelAdjuntar = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public String habilitarCarga() {
		panelAdjuntar = true;
		return null;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public List getListCuponesNoConc() {
		return listCuponesNoConc;
	}


	public void setListCuponesNoConc(List listCuponesNoConc) {
		this.listCuponesNoConc = listCuponesNoConc;
	}


	public boolean isConciliado() {
		return conciliado;
	}


	public void setConciliado(boolean conciliado) {
		this.conciliado = conciliado;
	}


	public boolean isMostrarCargaManual() {
		return mostrarCargaManual;
	}


	public void setMostrarCargaManual(boolean mostrarCargaManual) {
		this.mostrarCargaManual = mostrarCargaManual;
	}


	public String getTituloNoConciliados() {
		return tituloNoConciliados;
	}


	public void setTituloNoConciliados(String tituloNoConciliados) {
		this.tituloNoConciliados = tituloNoConciliados;
	}


	public boolean isMostrarEncabezado() {
		return mostrarEncabezado;
	}


	public void setMostrarEncabezado(boolean mostrarEncabezado) {
		this.mostrarEncabezado = mostrarEncabezado;
	}


	public boolean isTodos() {
		return todos;
	}


	public void setTodos(boolean todos) {
		this.todos = todos;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public boolean getPanelAdjuntar() {
		return panelAdjuntar;
	}


	public void setPanelAdjuntar(boolean panelAdjuntar) {
		this.panelAdjuntar = panelAdjuntar;
	}


	public boolean isMostrarTablaLotesAutomAbiertos() {
		return mostrarTablaLotesAutomAbiertos;
	}


	public void setMostrarTablaLotesAutomAbiertos(
			boolean mostrarTablaLotesAutomAbiertos) {
		this.mostrarTablaLotesAutomAbiertos = mostrarTablaLotesAutomAbiertos;
	}


	public boolean isMostrarTablaNoConc() {
		return mostrarTablaNoConc;
	}


	public void setMostrarTablaNoConc(boolean mostrarTablaNoConc) {
		this.mostrarTablaNoConc = mostrarTablaNoConc;
	}


	public boolean isMostrarResultConc() {
		return mostrarResultConc;
	}


	public void setMostrarResultConc(boolean mostrarResultConc) {
		this.mostrarResultConc = mostrarResultConc;
	}


	public PaginaDeRegistros getPaginador() {
		return paginador;
	}


	public void setPaginador(PaginaDeRegistros paginador) {
		this.paginador = paginador;
	}


	public boolean isPrimero() {
		return primero;
	}


	public void setPrimero(boolean primero) {
		this.primero = primero;
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isBusquedaAvanzada() {
		return busquedaAvanzada;
	}


	public void setBusquedaAvanzada(boolean busquedaAvanzada) {
		this.busquedaAvanzada = busquedaAvanzada;
	}


	public BufferedWriter getBw() {
		return bw;
	}


	public void setBw(BufferedWriter bw) {
		this.bw = bw;
	}


	public int getCantRechazados() {
		return cantRechazados;
	}


	public void setCantRechazados(int cantRechazados) {
		this.cantRechazados = cantRechazados;
	}


	public int getCantRechazosDefinitivos() {
		return cantRechazosDefinitivos;
	}


	public void setCantRechazosDefinitivos(int cantRechazosDefinitivos) {
		this.cantRechazosDefinitivos = cantRechazosDefinitivos;
	}


	public int getCantConciliada() {
		return cantConciliada;
	}


	public void setCantConciliada(int cantConciliada) {
		this.cantConciliada = cantConciliada;
	}


	public boolean isMostrarLinkBusquedaCupNoAsoc() {
		return mostrarLinkBusquedaCupNoAsoc;
	}


	public void setMostrarLinkBusquedaCupNoAsoc(
			boolean mostrarLinkBusquedaCupNoAsoc) {
		this.mostrarLinkBusquedaCupNoAsoc = mostrarLinkBusquedaCupNoAsoc;
	}


	public boolean isMostrarTablaNoConcCabecera() {
		return mostrarTablaNoConcCabecera;
	}


	public void setMostrarTablaNoConcCabecera(boolean mostrarTablaNoConcCabecera) {
		this.mostrarTablaNoConcCabecera = mostrarTablaNoConcCabecera;
	}


	public PaginadorPorDemanda getPagDeMov() {
		return pagDeMov;
	}


	public void setPagDeMov(PaginadorPorDemanda pagDeMov) {
		this.pagDeMov = pagDeMov;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public ConciliacionBancaria getConciliacionBancaria() {
		return conciliacionBancaria;
	}


	public void setConciliacionBancaria(ConciliacionBancaria conciliacionBancaria) {
		this.conciliacionBancaria = conciliacionBancaria;
	}

}