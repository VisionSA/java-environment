package com.bizitglobal.tarjetafiel.proveedores.historial.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.historial.ComprobanteHistorialDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;

public class ComprobanteHistorialDaoImpl implements ComprobanteHistorialDao {
	private DataSource dataSource;
	
	public void grabarHistorialComprobante(Comprobante unComprobante) throws ComprobanteException {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		
//		String sqlInsert = "insert into t_vis_prov_proveedores_his(c_id_comprobante," +
//				"c_id_tipo_cte,c_nro_corto,c_nro_largo,c_cuit_pedido_por,c_fecha_emision"+
//				",c_fecha_contable,c_observacion,c_monto_grabado,c_monto_no_grabado,c_importe_neto"+
//				",c_total_impuestos,c_importe_total,c_contabilizado,c_id_proveedor,"+
//				"c_timestamp,c_id_operador) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//		Object[] values = new Object[] {
//				unComprobante.getIdComprobante(),
//				unComprobante.getTipoComprobante(),
//				unComprobante.getNroCorto(),
//				unComprobante.getNroLargo(),				
//				unComprobante.getCuitPedidoPor(),				
//				unComprobante.getFechaEmision(),				
//				unComprobante.getFechaContable(),				
//				unComprobante.getObservacion(),				
//				unComprobante.getMontoGrabado(),				
//				unComprobante.getMontoNoGrabado(),				
//				unComprobante.getImporteNeto(),				
//				unComprobante.getTotalImpuestos(),				
//				unComprobante.getImporteTotal(),				
//				unComprobante.getContabilizado(),				
//				unComprobante.getProveedor().getIdProveedor(),				
//				unComprobante.getTimestamp(),				
//				unComprobante.getOperador().getId()};
//		
//		try {
//			jt.update(sqlInsert,values);
//		} catch (Exception e) {
//			throw new ComprobanteException("Falla la operación de historial en comprobante.");
//		}
//	
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
