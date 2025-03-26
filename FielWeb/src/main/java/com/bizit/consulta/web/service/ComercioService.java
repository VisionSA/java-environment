package com.bizit.consulta.web.service;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizit.consulta.web.entity.UsuarioComercioWeb;
import com.bizit.consulta.web.exception.ComercioException;
import com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub;
import com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.GetArchivoLiqComercio;
import com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.GetArchivoLiqComercioResponse;
import com.bizit.consulta.web.utils.AccesosExternos;
import com.bizit.consulta.web.utils.Constantes;
import com.bizit.consulta.web.utils.FileUtils;
import com.bizit.consulta.web.utils.MapUtil;
import com.bizit.consulta.web.vo.DetalleRetencionVO;
import com.bizit.consulta.web.vo.RetencionVO;

@RemotingDestination
@Service
public class ComercioService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AccesosExternos accesosExternos;

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Map> listarLiqComercios(UsuarioComercioWeb ucw, Date fechaDesde) {

		List<Map> result = MapUtil.map(
				sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								getQueryListarLiqComercio(ucw, fechaDesde))
						.setDate("fechaDesde", fechaDesde)
/*@I4923*/						//.setLong("codComercio", ucw.getIdCodigoComercio())
/*@F4923*/						//.setString("codComercio", ucw.getCodigosPosnet())
						.list(),
				new String[] { "idListaPrecio", "perDesde", "perHasta",
						"nroLiquidacion", "pdf", "totalBruto",
/*@I4148*/						"totalNeto", "totalPagos", "listaPrecio", "idLiqComercio", "fechaLiq", "codPosnet" });
/*@F4148*/		return result;

	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<RetencionVO> listarRetencionesComercio(UsuarioComercioWeb ucw,
			Date fechaDesde) throws ComercioException {
		try {
			List<Object[]> resConsulta = sessionFactory.getCurrentSession()
					.createSQLQuery(getQueryListarRetComercio(ucw, fechaDesde))
					.setDate("fechaDesde", fechaDesde)
/*@I4923*/					//.setLong("codComercio", ucw.getIdCodigoComercio())
					.list();
			return toListRetenciones(resConsulta);
		} catch (Exception e) {
/*@F4923*/			e.printStackTrace();
			throw new ComercioException("Error al consultar los datos");
		}
	}

	public Byte[] getArchivoLiquidacion(Long idLiq, String url) throws ComercioException
	{
		try {
			ArchivoLiqComercioServiceStub archivoServicio = new ArchivoLiqComercioServiceStub(url);
			GetArchivoLiqComercio liquidacion = new GetArchivoLiqComercio();
			liquidacion.setIdLiqComercio(idLiq);
			GetArchivoLiqComercioResponse response;
			response = archivoServicio.getArchivoLiqComercio(liquidacion);
			return getBytesFromDataHandler(response.get_return());
		} catch (RemoteException e1) {
			throw new ComercioException("Error al conectarse al servidor.");
		} catch (IOException e) {
			throw new ComercioException("Error al leer los archivos.");
		}
		catch (Exception e) {
/*@I4923*/			e.printStackTrace();
/*@F4923*/			throw new ComercioException("Error al consultar los archivos.");
		}
	}
	
	public static Byte[] getBytesFromDataHandler(DataHandler data) 
		       throws IOException {
			InputStream in = null;
			byte out[] = null;
			in = data.getInputStream();
			if(in != null) {
				out = new byte[in.available()];
				in.read(out);
			} else {
				out = new byte[0];
			}
			
			//Esto no se debería hacer, buscar una forma mejor...
			Byte[] respuesta = new Byte[out.length];
			for(int i = 0; i < out.length; i++)
			{
				respuesta[i] = new Byte(out[i]);
			}
			
			return respuesta;
		}
	
	private List<RetencionVO> toListRetenciones(List<Object[]> resConsulta) {
		List<RetencionVO> resultado = new ArrayList<RetencionVO>();

		RetencionVO ret = null;

		Iterator<Object[]> it = resConsulta.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
/*@I4923*/			if (ret == null || !obj[0].equals(ret.getPeriodo())
					|| !obj[3].equals(ret.getCodigoPosnet())) {
				// Si es nulo o son distintos los periodos
				ret = new RetencionVO();
				ret.setPeriodo((String) obj[0]);
				ret.setPdf((String) obj[1]);
				ret.setDetalle(new ArrayList<DetalleRetencionVO>());
				ret.setCodigoPosnet((String) obj[3]);
				resultado.add(ret);
			}
			DetalleRetencionVO det = new DetalleRetencionVO();
			det.setDescripcion((String) obj[2]);
			det.setCodigoPosnet((String) obj[3]);
/*@F4923*/			det.setMonto((BigDecimal) obj[4]);
			ret.getDetalle().add(det);
		}

		return resultado;
	}

	private String getQueryListarRetComercio(UsuarioComercioWeb ucw,
			Date fechaDesde) {
		StringBuilder query = new StringBuilder();

		query.append("SELECT con.c_mes || '/' || con.c_anio periodo, ");
		query.append("con.c_url_pdf urlPdf, ");
		query.append("imp.c_descripcion descripcionRetencion, ");
/*@I4923*/		query.append(" COM.C_CODIGO_POSNET codigoPosnet, ");
		query.append("SUM(re.c_monto) monto ");
		query.append("FROM t_vis_tra_liq_comercios li ");
		query.append("INNER JOIN t_vis_tra_liq_com_retenciones re ");
		query.append("ON (li.c_id_liq_comercio= re.c_id_liquidacion) ");
		query.append("INNER JOIN t_vis_tra_constancia_ret_com con ");
		query.append("ON (re.c_id_constancia_ret_com=con.c_id_constancia_ret_com) ");
		query.append("INNER JOIN t_vis_imp_retenciones imp ");
		query.append("ON (re.c_cod_retencion= imp.c_cod_retencion) ");
		query.append("INNER JOIN t_vis_tra_liquidaciones liq ");
		query.append("ON (li.c_numero_liquidacion = liq.c_id_liquidacion) ");
		query.append(" INNER JOIN T_VIS_TRA_COD_COMERCIOS COM ON COM.C_ID_COD_COMERCIO = LI.C_COD_COMERCIO ");
		//query.append("WHERE li.c_cod_comercio = :codComercio ");
		query.append("WHERE li.c_cod_comercio IN ( ");
		query.append(" SELECT COM1.C_ID_COD_COMERCIO FROM T_VIS_TRA_COD_COMERCIOS COM1 WHERE COM1.C_CODIGO_POSNET IN (" +
				ucw.getCodigosPosnet() +
/*@F4923*/				"))  ");
		query.append("AND TRUNC(liq.c_fecha_liquidacion) >= :fechaDesde ");
		query.append("GROUP BY re.c_id_constancia_ret_com, ");
		query.append("re.c_cod_retencion, ");
		query.append("con.c_url_pdf, ");
		query.append("con.c_anio, ");
		query.append("con.c_mes, ");
/*@I4923*/		query.append("imp.c_descripcion, ");
/*@F4923*/		query.append(" COM.C_CODIGO_POSNET ");
		query.append("ORDER BY re.c_id_constancia_ret_com ");

		return query.toString();
	}

	private String getQueryListarLiqComercio(UsuarioComercioWeb ucw,
			Date fechaDesde) {
		StringBuilder query = new StringBuilder();

		query.append("SELECT liqcomlp.c_id_lista_precio idListaPrecio,");
		query.append("liqcomlp.c_periodo_desde perDesde, ");
		query.append("liqcomlp.c_periodo_hasta perHasta, ");
		query.append("liqcom.c_numero_liquidacion, ");
		query.append("pdf.c_guardar_en guardarEn, ");
		query.append("liqcomlp.c_total_bruto totalBruto, ");
		query.append("liqcomlp.c_total_neto totalNeto, ");
		query.append("liqcom.c_total_pagos totalPagos, ");
		query.append("listaP.c_descripcion listaPrecio, ");
		query.append("liqcom.c_id_liq_comercio, ");
/*@I4923*/		query.append("PDF.C_FECHA_LIQUIDACION, ");
		query.append(" CODCOM.C_CODIGO_POSNET ");
		query.append("FROM t_vis_tra_liq_comercios liqcom ");
		query.append("INNER JOIN t_vis_tra_cod_comercios codcom ON liqcom.c_cod_comercio = codcom.c_id_cod_comercio ");
		query.append("INNER JOIN t_vis_tra_liq_comercios_lp liqComlp ON liqComlp.c_id_liq_comercio = liqcom.c_id_liq_comercio ");
		query.append("INNER JOIN t_vis_tra_lista_precios listaP ON listap.c_id_listaprecios = liqcomlp.c_id_lista_precio ");
		query.append("INNER JOIN t_vis_tra_pdf_liq_comercios pdf ON pdf.c_numero_liquidacion= liqcom.c_numero_liquidacion ");
		//query.append("WHERE codcom.c_id_cod_comercio= :codComercio ");
		query.append("WHERE codcom.c_codigo_posnet in ("+ ucw.getCodigosPosnet() +") ");
		query.append("AND trunc(liqComlp.c_periodo_desde) >= :fechaDesde ");
		//query.append("ORDER BY  liqcomlp.c_periodo_desde DESC");
/*@F4923*/		query.append("ORDER BY  CODCOM.C_CODIGO_POSNET, liqcomlp.c_periodo_desde DESC ");

		return query.toString();
	}

	public byte[] obtenerPdf(String urlPdf) throws ComercioException {
		/*En algún momento se cambió la configuracion de la ruta donde se guardan los archivos,
		 * por lo que hay limpiar las urls nuevas para que funcionen los pdf
		 */
		urlPdf = urlPdf.replace("/webapps", "");
		
		URL url = null;
		URLConnection urlConnection = null;
		try {
			url = new URL(accesosExternos.getFielServer() + urlPdf
					+ Constantes.EXTENSION_PDF);
			urlConnection = url.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			return FileUtils
					.toByteArrayFromDataInputStream(new DataInputStream(
							urlConnection.getInputStream()));
		} catch (MalformedURLException e) {
/*@I4923*/			e.printStackTrace();
			throw new ComercioException(
					"El archivo no existe o no es posible el acceso");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ComercioException("No se encuentra el archivo generado");
		}catch (IOException e) {
/*@F4923*/			e.printStackTrace();
			throw new ComercioException("No es posible conectarse al servidor");
		}
	}

}
