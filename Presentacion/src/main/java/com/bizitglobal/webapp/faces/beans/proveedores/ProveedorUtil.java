package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.dao.ClienteDao;
import com.bizitglobal.tarjetafiel.general.dao.LocalidadDao;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFielDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoTelefonoDao;
import com.bizitglobal.tarjetafiel.general.negocio.Cliente;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;
import com.bizitglobal.tarjetafiel.impuestos.dao.CategoriaDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.proveedores.dao.GrupoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ClienteRelacion;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCategoria;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorEmail;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorTelefono;
import com.bizitglobal.tarjetafiel.proveedores.negocio.RelacionProveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorFormaPagoService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorService;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.ImpuestoEditable;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProveedorUtil {
	private static final Logger log = Logger.getLogger(ProveedorUtil.class);


	public static Set cargarEmails(List tablaDeEmails, Proveedor proveedor) {
		log.info("	generando lista de emails...");
		Set result = new HashSet();

		if (tablaDeEmails != null && tablaDeEmails.size() > 0) {
			Iterator emails = tablaDeEmails.iterator();
			while (emails.hasNext()) {
				ProveedorEmail proveedorEmail = (ProveedorEmail) emails.next();
				proveedorEmail.setProveedor(proveedor);
				Email aux = proveedorEmail.getEmail();
				int hash = aux.hashCode();
				if (hash == aux.getIdEmail().intValue()) {
					log.info("Se deberia insertar en la base de datos->" + aux);
					aux.setIdEmail(null);
				}
				result.add(proveedorEmail);
			}
		}

		return result;
	}


	public static List setearProveedor(List tablaDeFormasDePago, Proveedor proveedor) {
		log.info("	seteando proveedor a las formas de pago...");
		List result = new ArrayList();
		if (!tablaDeFormasDePago.isEmpty()) {
			Iterator formasPagosConProveedor = tablaDeFormasDePago.iterator();
			while (formasPagosConProveedor.hasNext()) {
				ProveedorFormaPago aux = (ProveedorFormaPago) formasPagosConProveedor.next();
				aux.setProveedor(proveedor);
				result.add(aux);
			}
		}
		return result;
	}


	public static Set cargarRelacionConClientes(List tablaRelacionConClientes, Proveedor proveedor) {
		log.info("	generando lista de relacion con clientes...");
		Set relacionConClientes = new HashSet();
		Iterator iterDeRelacionConClientes = tablaRelacionConClientes.iterator();
		while (iterDeRelacionConClientes.hasNext()) {
			Cliente cliente = (Cliente) iterDeRelacionConClientes.next();
			ClienteRelacion clienteRelacion = new ClienteRelacion();
			clienteRelacion.setIdCliente(new Long(cliente.getIdCliente().longValue()));
			clienteRelacion.setProveedor(proveedor);
			relacionConClientes.add(clienteRelacion);
		}

		return relacionConClientes;
	}


	public static Set cargarRelacionConProveedores(List tablaRelacionConProveedores, Proveedor proveedor) {
		log.info("	generando lista de relacion con proveedores...");
		Set relacionConProveedores = new HashSet();
		Iterator iterDeRelacionConProveedores = tablaRelacionConProveedores.iterator();
		while (iterDeRelacionConProveedores.hasNext()) {
			Proveedor aux = (Proveedor) iterDeRelacionConProveedores.next();
			RelacionProveedor proveedorRelacion = new RelacionProveedor();
			proveedorRelacion.setCuitHijo(aux.getCuit());
			proveedorRelacion.setCuitPadre(proveedor.getCuit());
			proveedorRelacion.setProveedor(proveedor);
			relacionConProveedores.add(proveedorRelacion);
		}

		return relacionConProveedores;
	}


	public static Set cargarDomicilios(List tablaDomicilios, Proveedor proveedor) {
		Set result = new HashSet();
		Iterator iterDomicilios = tablaDomicilios.iterator();
		while (iterDomicilios.hasNext()) {
			ProveedorDomicilio proveedorDomicilio = (ProveedorDomicilio) iterDomicilios.next();
			proveedorDomicilio.setProveedor(proveedor);
			// Domicilio aux = proveedorDomicilio.getDomicilio();
			// int id = aux.getIdDomicilio().intValue();
			// aux.setIdDomicilio(null);
			// int hash = aux.hashCode();
			// if(hash != id) {
			// aux.setIdDomicilio(new Long(id+""));
			// }
			result.add(proveedorDomicilio);
		}
		return result;
	}


	/*
	 * Preguntarle a hernan xq hace lo que esta comentarizado mas abajo.
	 */
	// public static Set cargarTelefonos(List tablaDeTelefonos, Proveedor proveedor) {
	// log.info("	generando lista de telefonos...");
	// //Set result = new HashSet();
	//
	// if(tablaDeTelefonos != null && tablaDeTelefonos.size() > 0) {
	// Iterator telefonos = tablaDeTelefonos.iterator();
	// while(telefonos.hasNext()) {
	// ProveedorTelefono proveedorTelefono = (ProveedorTelefono)telefonos.next();
	// proveedorTelefono.setProveedor(proveedor);
	// result.add(proveedorTelefono);
	// }
	// }
	//
	// return result;
	// }
	//
	public static Set recargarComposicionDePagos(List tablaDeComposicionDePago, Proveedor proveedor) {
		Set result = new HashSet();

		if (tablaDeComposicionDePago != null && tablaDeComposicionDePago.size() > 0) {
			Iterator composicion = tablaDeComposicionDePago.iterator();
			while (composicion.hasNext()) {
				// TipoVencimiento aux = new TipoVencimiento();
				TipoVencimiento aux = (TipoVencimiento) composicion.next();
				aux.setOrden(new Integer(1));
				// aux.setDias(leeido.getDias());
				// aux.setPorcentajeMonto(leeido.getPorcentajeMonto());
				aux.setProveedor(proveedor);
				aux.setIdTipoVencimiento(null);
				result.add(aux);
			}
		}

		return result;
	}


	/**
	 * Retorna una lista de objetos select item, para mostrarlos en la lista desplegable
	 * 
	 * @param sucursalFielDao
	 *            , objeto dao para acceso a datos.
	 * @return Una lista de select item.
	 */
	public static List cargarListaSucursales(SucursalFielDao sucursalFielDao) {
		List result = new ArrayList();
		List sucursalesList = sucursalFielDao.listarTodos();
		if (!sucursalesList.isEmpty()) {
			Iterator sucursales = sucursalesList.iterator();
			while (sucursales.hasNext()) {
				SelectItem item = new SelectItem();
				SucursalFiel aux = (SucursalFiel) sucursales.next();
				item.setValue(aux.getIdSucursal());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaGrupos(GrupoDao grupoDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List grupoList = grupoDao.listarTodos(new Filtro());
		if (!grupoList.isEmpty()) {
			Iterator grupos = grupoList.iterator();
			while (grupos.hasNext()) {
				SelectItem item = new SelectItem();
				Grupo aux = (Grupo) grupos.next();
				item.setValue(aux.getIdGrupo());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaLocalidades(LocalidadDao localidadDao) {
		List result = new ArrayList();
		List localidadesList = localidadDao.listarTodos();
		if (!localidadesList.isEmpty()) {
			Iterator localidades = localidadesList.iterator();
			while (localidades.hasNext()) {
				SelectItem item = new SelectItem();
				Localidad aux = (Localidad) localidades.next();
				item.setValue(aux.getIdLocalidad());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaJurisdicciones(JurisdiccionDao jurisdiccionDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione"));
		List jurisList = jurisdiccionDao.listarTodos(new Filtro());
		if (!jurisList.isEmpty()) {
			Iterator iterJur = jurisList.iterator();
			while (iterJur.hasNext()) {
				Jurisdiccion aux = (Jurisdiccion) iterJur.next();
				result.add(new SelectItem(aux.getIdJurisdiccion(), aux.getDescripcion()));
			}
		}

		return result;
	}


	public static List cargarListaDiasSemanaNombre() {
		List result = new ArrayList();
		String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
		for (int i = 0; i < dias.length; i++) {
			SelectItem item = new SelectItem();
			item.setValue(dias[i]);
			item.setLabel(dias[i]);
			result.add(item);
		}

		return result;
	}


	public static List cargarListaDiasSemana() {
		List result = new ArrayList();

		for (int i = 1; i < 5; i++) {
			SelectItem item = new SelectItem();
			item.setValue(new Integer(i));
			item.setLabel(new String(i + ""));
			result.add(item);
		}

		return result;
	}


	public static List cargarListaDiasMes() {
		log.info("	generando lista de dias del mes...");
		List result = new ArrayList();
		for (int i = 1; i < 32; i++) {
			SelectItem item = new SelectItem();
			item.setValue(new Integer(i));
			item.setLabel(new Integer(i).toString());
			result.add(item);
		}

		return result;
	}


	public static List generarListaImpuestos(TipoImpuestoDao tipoImpuestoDao, JurisdiccionActividadDao actividadDao) {
		List result = new ArrayList();
		Filtro filtro = new Filtro();
		List listaTiposImpuestos = tipoImpuestoDao.listarTodos(filtro);
		// List activiList = actividadDao.listarTodos(new Filtro());
		// if (!activiList.isEmpty()) {
		// Iterator iterator = activiList.iterator();
		// while (iterator.hasNext()) {
		// JurisdiccionActividad element = (JurisdiccionActividad) iterator.next();
		// // element.getActividad().getDescripcion();
		// element.getJurisdiccion().getDescripcion();
		// }
		// }
		if (!listaTiposImpuestos.isEmpty()) {
			Iterator iter = listaTiposImpuestos.iterator();
			while (iter.hasNext()) {
				TipoImpuesto aux = (TipoImpuesto) iter.next();
				// List categorias = new ArrayList();
				ImpuestoEditable impuesto = new ImpuestoEditable(aux, actividadDao, "prov");

				// impuesto.setTipo(aux.getDescripcion());
				// impuesto.setJurisdiccion(aux.getJurisdiccion().getDescripcion());
				// impuesto.setCategorias(Convertidores.setToList(aux.getCategorias()));

				result.add(impuesto);
			}
		}

		return result;
	}


	public static List marcarListaDesdeImpuestos(IndividuoDao individuoDao, Long cuit, List listaDeImpuestos) {
		log.info("Obteniendo individuo.");
		Individuo individuo = ProveedorUtil.getIndividuo(individuoDao, cuit);

		if (individuo != null) {
			log.info("individuo encontrado.");
			List individuoCategoriaList = Convertidores.setToList(individuo.getIndividuoCategorias());
			if (!individuoCategoriaList.isEmpty()) {
				Iterator individuoCategorias = individuoCategoriaList.iterator();
				while (individuoCategorias.hasNext()) {
					ImpuestosIndividuo individuoCategoria = (ImpuestosIndividuo) individuoCategorias.next();
					Categoria categoria = individuoCategoria.getCategoria();
					if (!listaDeImpuestos.isEmpty()) {
						Iterator impuestos = listaDeImpuestos.iterator();
						while (impuestos.hasNext()) {
							ImpuestoEditable aux = (ImpuestoEditable) impuestos.next();
							if (aux.tieneCategoria(categoria)) {
								aux.setCategoriaSeleccionada(categoria.getIdCategoria());
							}
						}
					}
				}
			}
		}
		log.info("	generando lista de impuestos y marcando categorias...");
		return listaDeImpuestos;
	}


	public static List marcarListaDesdeProveedores(Set setDeCategoriasProveedor, List listaDeImpuestos) {
		log.info("	generando lista de impuestos y marcando categorias...");
		List result = new ArrayList();

		if (!setDeCategoriasProveedor.isEmpty()) {
			List categoriaList = Convertidores.setToList(setDeCategoriasProveedor);
			Iterator categorias = categoriaList.iterator();
			while (categorias.hasNext()) {
				ProveedorCategoria proveedorCategoria = (ProveedorCategoria) categorias.next();
				Categoria categoria = proveedorCategoria.getJurisdiccionActividad().getCategoria();
				JurisdiccionActividad jActividad = proveedorCategoria.getJurisdiccionActividad();

				if (!listaDeImpuestos.isEmpty()) {
					Iterator impuestos = listaDeImpuestos.iterator();
					while (impuestos.hasNext()) {
						ImpuestoEditable aux = (ImpuestoEditable) impuestos.next();
						if (aux.tieneCategoria(categoria)) {
							aux.setCategoriaSeleccionada(categoria.getIdCategoria());
							aux.setProveedorCategoria(proveedorCategoria);
							aux.setJurisSeleccionada(jActividad.getJurisdiccion().getIdJurisdiccion());
							aux.buscarActividades();
							aux.setJurisActividadSeleccionada(jActividad.getIdJurisdiccionActividad());
						}
					}
				}
			}
		}
		result = listaDeImpuestos;
		return result;
	}


	public static Individuo getIndividuo(IndividuoDao individuoDao, Long cuit) {
		Individuo result = null;

		Filtro filtro = new Filtro("cuit", cuit);
		List individuoList = individuoDao.listarTodos(filtro);

		if (individuoList.size() > 0) {
			result = (Individuo) individuoList.get(0);
		}

		return result;
	}


	public static Set cargarImpuestos(List tablaDeImpuestos, Proveedor proveedor,
			CategoriaDao categoriaDao, JurisdiccionActividadDao jurisdiccionActividadDao) {
		log.info("	generando lista de impuestos...");
		Set result = new HashSet();
		Iterator iterImpuestos = tablaDeImpuestos.iterator();
		while (iterImpuestos.hasNext()) {
			ImpuestoEditable aux = (ImpuestoEditable) iterImpuestos.next();
			// ProveedorCategoria cat = new ProveedorCategoria();
			if (!Validador.esNulo(aux.getJurisActividadSeleccionada()) && !aux.getJurisActividadSeleccionada().equals(new Long(0))) {
				ProveedorCategoria cat = aux.getProveedorCategoria();
				cat.setJurisdiccionActividad(jurisdiccionActividadDao.
						buscarJurisdiccionActividad(aux.getJurisActividadSeleccionada()));
				cat.setProveedor(proveedor);
				// cat.setIdProvCategoria(aux.getIdProveedorCategoria());
				cat.setActivo("S");
				result.add(cat);
			}
		}

		return result;
	}


	public static List eliminarEmail(List listaEmail, Long idEmail) {

		if (!listaEmail.isEmpty())
			listaEmail.remove(buscarEmail(listaEmail, idEmail));

		if (listaEmail.isEmpty())
			listaEmail = new ArrayList();

		return listaEmail;
	}


	public static ProveedorEmail buscarEmail(List eMails, Long id) {

		if (!eMails.isEmpty()) {
			Iterator iterator = eMails.iterator();
			while (iterator.hasNext()) {
				ProveedorEmail element = (ProveedorEmail) iterator.next();
				if (element.getEmail().getIdEmail().equals(id))
					return element;
			}
		}
		return null;
	}


	public static List eliminarTelefono(List listaTelefono, Long idTelefono) {
		List result = new ArrayList();
		if (!listaTelefono.isEmpty()) {
			Iterator iter = listaTelefono.iterator();
			while (iter.hasNext()) {
				ProveedorTelefono aux = (ProveedorTelefono) iter.next();
				int hash = aux.getTelefono().hashCode();
				if (hash != idTelefono.intValue() || !aux.getTelefono().getIdTelefono().equals(idTelefono)) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static List eliminarCompPago(List listaCompPago, Long idCompPago) {
		List result = new ArrayList();
		if (!listaCompPago.isEmpty()) {
			Iterator iter = listaCompPago.iterator();
			while (iter.hasNext()) {
				TipoVencimiento aux = (TipoVencimiento) iter.next();
				if (!aux.getIdTipoVencimiento().equals(idCompPago)) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static Domicilio buscarDomicilio(List listaDomicilios, Long idDomicilio) {
		if (!listaDomicilios.isEmpty()) {
			Iterator domicilios = listaDomicilios.iterator();
			while (domicilios.hasNext()) {
				ProveedorDomicilio aux = (ProveedorDomicilio) domicilios.next();
				if (idDomicilio.equals(aux.getDomicilio().getIdDomicilio())) {
					return aux.getDomicilio();
				}
			}
		}
		return new Domicilio();
	}


	public static List eliminarDomicilio(List listaDomicilios, Long idDomicilio) {
		List result = new ArrayList();
		if (!listaDomicilios.isEmpty()) {
			Iterator domicilios = listaDomicilios.iterator();
			while (domicilios.hasNext()) {
				ProveedorDomicilio aux = (ProveedorDomicilio) domicilios.next();
				if (!idDomicilio.equals(aux.getDomicilio().getIdDomicilio())) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static List eliminarRCP(List listaRCP, Long idRelacion) {
		List result = new ArrayList();
		if (!listaRCP.isEmpty()) {
			Iterator relaciones = listaRCP.iterator();
			while (relaciones.hasNext()) {
				Proveedor aux = (Proveedor) relaciones.next();
				if (!aux.getIdProveedor().equals(idRelacion)) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static List eliminarRCC(List listaRCC, Long idRelacion) {
		log.info("Entrando a eliminarRCC ->" + idRelacion);

		List result = new ArrayList();
		if (!listaRCC.isEmpty()) {
			Iterator relaciones = listaRCC.iterator();
			while (relaciones.hasNext()) {
				Cliente aux = (Cliente) relaciones.next();
				if (!aux.getIdCliente().equals(new Integer(idRelacion.intValue()))) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static Object[] eliminarFP(List listaFP, Long idFP) {
		// Arreglo de resultados.
		// result[0] -> lista de formas de pago sin el objeto eliminado.
		// result[1] -> el objeto eliminado.
		Object[] result = { new ArrayList(), null };
		if (!listaFP.isEmpty()) {
			Iterator relaciones = listaFP.iterator();
			while (relaciones.hasNext()) {
				ProveedorFormaPago aux = (ProveedorFormaPago) relaciones.next();
				if (!aux.getIdProvFormaPago().equals(idFP)) {
					((List) result[0]).add(aux);
				} else {
					result[1] = aux;
				}
			}
		}

		return result;
	}


	public static List leerEmails(Set setDeEmails) {
		List result = new ArrayList();
		if (!setDeEmails.isEmpty()) {
			Iterator iter = setDeEmails.iterator();
			while (iter.hasNext()) {
				ProveedorEmail aux = (ProveedorEmail) iter.next();
				result.add(aux.getEmail());
			}
		}

		return result;
	}


	public static List leerTelefonos(Set setDeTelefonos) {
		List result = new ArrayList();
		if (!setDeTelefonos.isEmpty()) {
			Iterator iter = setDeTelefonos.iterator();
			while (iter.hasNext()) {
				ProveedorTelefono aux = (ProveedorTelefono) iter.next();
				result.add(aux.getTelefono());
			}
		}

		return result;
	}


	public static List leerDomicilios(Set setDeDomicilios) {
		List result = new ArrayList();
		if (!setDeDomicilios.isEmpty()) {
			Iterator iter = setDeDomicilios.iterator();
			while (iter.hasNext()) {
				ProveedorDomicilio aux = (ProveedorDomicilio) iter.next();
				result.add(aux.getDomicilio());
			}
		}

		return result;
	}


	public static List leerRelacionConProveedores(Set setDeRelaciones, ProveedorService service) {
		List result = new ArrayList();
		List proveedores = null;
		if (!setDeRelaciones.isEmpty()) {
			Iterator iter = setDeRelaciones.iterator();
			while (iter.hasNext()) {
				log.info("Hallando proveedores relacionados...");
				RelacionProveedor aux = (RelacionProveedor) iter.next();

				try {
					proveedores = service.getProveedores(new Filtro("cuit", aux.getCuitHijo()));
				} catch (ProveedorException e) {
					e.printStackTrace();
				}

				if (!proveedores.isEmpty()) {
					Proveedor prov = (Proveedor) proveedores.get(0);
					result.add(prov);
				}
			}
		}

		return result;
	}


	public static List leerRelacionConClientes(Set setDeClientes, ClienteDao clienteDao) {
		List result = new ArrayList();
		if (!setDeClientes.isEmpty()) {
			Iterator iter = setDeClientes.iterator();
			while (iter.hasNext()) {
				ClienteRelacion aux = (ClienteRelacion) iter.next();
				List clientes = clienteDao.listarTodos(new Filtro("idCliente", aux.getIdCliente()));
				Cliente cliente = null;
				if (!clientes.isEmpty()) {
					cliente = (Cliente) clientes.get(0);
				}
				result.add(cliente);
			}
		}

		return result;
	}


	public static List cargarListaDeTiposDeTelefonos(TipoTelefonoDao tipoTelefonoDao) {
		log.info("	generando lista de tipos de telefonos...");
		List result = new ArrayList();
		List tipoTelefonoList = tipoTelefonoDao.listarTodos();
		if (!tipoTelefonoList.isEmpty()) {
			Iterator tipos = tipoTelefonoList.iterator();
			while (tipos.hasNext()) {
				SelectItem item = new SelectItem();
				TipoTelefono aux = (TipoTelefono) tipos.next();
				if (aux.getParaProveedor().equals("S")) {
					item.setValue(aux.getIdTipoTelefono());
					item.setLabel(aux.getDescripcion());
					result.add(item);
				}
			}
		}

		return result;
	}


	/**
	 * Graba o actualiza la lista de formas de pago en la base de datos.
	 * 
	 * @param dao
	 *            , dao para ejecutar las operaciones.
	 * @param formas
	 *            , lista de formas de pago a grabar o actualizar.
	 * @param proveedor
	 *            , proveedor para setear a las formas de pago.
	 * @param listaParaBorrar
	 *            , lista de formas de pago a eliminar.
	 */
	public static void grabarFormasDePagos(ProveedorFormaPagoService dao, List formas,
			Proveedor proveedor, List listaParaBorrar) {
		proveedor.setFormasDePago(new HashSet());
		if (!listaParaBorrar.isEmpty()) {
			Iterator iter = listaParaBorrar.iterator();
			while (iter.hasNext()) {
				ProveedorFormaPago aux = (ProveedorFormaPago) iter.next();
				aux.setEsActivo("N");
				aux.setProveedor(proveedor);
				proveedor.getFormasDePago().add(aux);
				// try {
				// dao.actualizarProveedorFormaPago(aux);
				// } catch (ProveedorFormaPagoException e) {
				// e.printStackTrace();
				// }
			}
		}

		listaParaBorrar = new ArrayList();

		if (!formas.isEmpty()) {
			Iterator iter = formas.iterator();
			while (iter.hasNext()) {
				ProveedorFormaPago aux = (ProveedorFormaPago) iter.next();
				aux.setProveedor(proveedor);
				proveedor.getFormasDePago().add(aux);
				// try {
				// dao.grabarProveedorFormaPago(aux);
				// } catch (ProveedorFormaPagoException e) {
				// e.printStackTrace();
				// }
			}
		}
	}


	public static Telefono buscarTelefono(List telefonos, Long idTelefono) {
		log.info("idTelefono: " + idTelefono);
		Telefono result = null;
		if (!telefonos.isEmpty()) {
			Iterator iterator = telefonos.iterator();
			while (iterator.hasNext()) {
				ProveedorTelefono element = (ProveedorTelefono) iterator.next();
				log.info("id: " + element.getTelefono().getIdTelefono());
				if (element.getTelefono().getIdTelefono().equals(idTelefono))
					result = element.getTelefono();
			}
		}
		return result;
	}


	public static List eliminarTelefonos(List telefonos, Long id) {

		if (!telefonos.isEmpty())
			telefonos.remove(buscarTelefonos(telefonos, id));

		return telefonos;
	}


	public static ProveedorTelefono buscarTelefonos(List telefonos, Long idTelefono) {
		log.info("idTelefono: " + idTelefono);
		if (!telefonos.isEmpty()) {
			Iterator iterator = telefonos.iterator();
			while (iterator.hasNext()) {
				ProveedorTelefono element = (ProveedorTelefono) iterator.next();
				log.info("id: " + element.getTelefono().getIdTelefono());
				if (element.getTelefono().getIdTelefono().equals(idTelefono))
					return element;
			}
		}
		return null;
	}


	public static Set eliminarTelefonoSet(Set listaTelefonos, Long idTelefono) {

		if (!listaTelefonos.isEmpty())
			listaTelefonos.remove(buscarTelefonos(Convertidores.setToList(listaTelefonos), idTelefono));

		if (listaTelefonos.isEmpty())
			listaTelefonos = new HashSet();

		return listaTelefonos;
	}
}
