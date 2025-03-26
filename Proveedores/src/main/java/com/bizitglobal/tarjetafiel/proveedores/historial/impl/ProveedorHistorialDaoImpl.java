package com.bizitglobal.tarjetafiel.proveedores.historial.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.historial.ProveedorHistorialDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public class ProveedorHistorialDaoImpl implements ProveedorHistorialDao {
	private DataSource dataSource;
	
	public void grabarHistorialProveedor(Proveedor unProveedor) throws ProveedorException {
/*		JdbcTemplate jt = new JdbcTemplate(dataSource);
		
		String sqlInsert = "insert into t_vis_prov_proveedores_his(" +
				"c_id_proveedor,c_cuit,c_digito_verif_cuit,c_razon_social,c_nombre_fantasia"+
				",c_inscripcion_dgr,c_cond_iva,c_limite_credito,c_cbu,c_digito_verif_cbu"+
				",c_id_domicilio_legal,c_id_domicilio_postal,c_id_grupo,c_id_modalidad_pago,"+
				"c_id_sucursal,c_id_forma_pago,c_id_banco,c_id_moneda,c_cod_iva,c_es_cheque_cruzado,"+
				"c_tipo_cta,c_cod_cta,c_orden_cheque,c_nro_cuenta_fondos,c_timestamp,"+
				"c_id_operador) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] values = new Object[] {
				unProveedor.getIdProveedor(),
				unProveedor.getCuit(),
				unProveedor.getDigitoVerificadorCuit(),
				unProveedor.getRazonSocial(),
				unProveedor.getNombreFantasia(),
				unProveedor.getInscripcionDgr(),
				unProveedor.getCondIva(),
				unProveedor.getLimiteCredito(),
				//unProveedor.getCbu(),
				//unProveedor.getDigitoVerificadorCbu(),
				//unProveedor.getDomicilioLegal(),
				//unProveedor.getDomicilioPostal(),				
				unProveedor.getGrupo().getIdGrupo(),
				unProveedor.getModalidadPago().getIdModalidadPago(),
				unProveedor.getSucursalFiel(),				
				//unProveedor.getFormaPago().getIdFormaPago(),
				//unProveedor.getBanco().getIdBanco(),				
				unProveedor.getMoneda().getIdMoneda(),
				//unProveedor.getEsChequeCruzado(),
				//unProveedor.getTipoCta(),
				//unProveedor.getCodCta(),				
				//unProveedor.getOrdenCheque(),
				//unProveedor.getNroCuentaFondos(),
				unProveedor.getTimestamp(),
				unProveedor.getOperador().getId()};
		
		try {
			jt.update(sqlInsert,values);
		} catch (Exception e) {
			throw new ProveedorException("Falla la operación de historial en proveedor.");
		}
*/	
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
