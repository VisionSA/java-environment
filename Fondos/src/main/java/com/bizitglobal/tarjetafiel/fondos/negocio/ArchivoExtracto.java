package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.fondos.exception.FormatoNoValidoException;
import com.bizitglobal.tarjetafiel.fondos.exception.FormatoNoValidoImporteException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;


public class ArchivoExtracto {
	private ExtractoBancario extractoBancario;
    private int contadorDeItems;	
    private Double importeTotal;
	private Operador operador;
    private String nombreArchivo;

    private String idConciliados = "";
	private String idNoConciliados = "";

	private boolean formatoCorrecto=true;
	private Long minFecha;
	private Long maxFecha;
	
	private String mensageResultado;
	private BancoPropio bancoPropio;
	
    public ArchivoExtracto(){
    	
    }
    public ArchivoExtracto(InputStream in, String name, Operador operador)throws FormatoNoValidoImporteException, FormatoNoValidoException { 
		contadorDeItems = 0;
		importeTotal = new Double(0);
		nombreArchivo= name;
		this.operador = operador;
		BufferedReader d=null;
		try {
			d = new BufferedReader(new InputStreamReader(in));
            String cadenaTexto = d.readLine();
            extractoBancario = armarCabecera(cadenaTexto);
            if(formatoCorrecto)
            {
            	extractoBancario.setRegistro(cadenaTexto);
	            cadenaTexto = d.readLine();
	            int nroLinea= 0;
	            int cantRegistroDetalleExtracto=0;
				while (cadenaTexto != null) {
					nroLinea++;
					//Solo registro de movimiento -> M.
					if(!cadenaTexto.substring(0, 1).equals("F") && !cadenaTexto.substring(0, 1).equals("H"))
					{
						cantRegistroDetalleExtracto++;
						DetalleExtracto detalleExtracto = armarItem(cadenaTexto,nroLinea);
						detalleExtracto.setRegistro(cadenaTexto);
						detalleExtracto.setExtractoBancario(extractoBancario);
						extractoBancario.getDetallesExtractoOrdenado().add(detalleExtracto);
					}
					else if(cadenaTexto.substring(0, 1).equals("F"))
					{
						extractoBancario = armarUltimaLinea(cadenaTexto, extractoBancario);
						extractoBancario.setRegistroF(cadenaTexto);
					}
					cadenaTexto = d.readLine();				
				}		
				extractoBancario.setCantRegistroMF(cantRegistroDetalleExtracto);
				extractoBancario.setImporteTotal(importeTotal);
				SortedSet e = extractoBancario.getDetallesExtractoOrdenado();
				extractoBancario.setDetallesExtracto(e);
				this.minFecha = new Long(((DetalleExtracto)e.first()).getFechaMovimientoCadena());
				this.maxFecha = new Long(((DetalleExtracto)e.last()).getFechaMovimientoCadena());
				
				if(extractoBancario.getDetallesExtracto().size()>0)
				{
					DetalleExtracto detalle = (DetalleExtracto) extractoBancario.getDetallesExtracto().iterator().next();
					extractoBancario.setIdBanco(detalle.getCodigoBanco());
				}
				
            }else
            {
            	throw new FormatoNoValidoException("El archivo no tiene el formato correcto."); 
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				d.close();
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
    /***
     * Este metodo elimina del Set detallesExtracto los registros que ya fueron guardados en la base. 
     * @param listRegistrosBase: debe tener las fechas almacenadas en la base. 
     */
    public void eliminarRegistrosYaGuardados(List listRegistrosBase){
    	SortedSet sortNew = new TreeSet();
    	
    	Iterator iterBase = listRegistrosBase.iterator();	
		SortedSet sortSet = extractoBancario.getDetallesExtractoOrdenado();
		
		while(iterBase.hasNext()){
			Long fechaBase = (Long)iterBase.next();
			
			Iterator iterSort = sortSet.iterator();
			while(iterSort.hasNext()){
				DetalleExtracto detalle = (DetalleExtracto)iterSort.next();
				if(!detalle.getFechaMovimientoCadena().equals(fechaBase)){
					sortNew.add(detalle);
				}
			}
			sortSet = sortNew;
			sortNew = new TreeSet();
		}
		
		extractoBancario.setDetallesExtractoOrdenado(sortSet);
		extractoBancario.setDetallesExtracto(sortSet);
    }
    public boolean validarCantidadItems()
    {
    	boolean result=false;
    	if(extractoBancario!=null && extractoBancario.getDetallesExtracto()!=null 
    			&& extractoBancario.getDetallesExtracto().size()>0)
    	{
    		if(extractoBancario.getCantRegistroMF()==this.contadorDeItems)
    			result=true;
    	}
    	return result;
    }

	public ExtractoBancario armarCabecera(String linea) {
		ExtractoBancario result = null;
        result = new ExtractoBancario();
        
        if(linea!=null && linea.trim().length()==22)
        {
        	result.setTimeStamp(new Date());
	        result.setNroCliente(linea.substring(2, 7));
	        result.setFechaProcesoCadena(linea.substring(8,16));
	        //Fecha proceso
	        Calendar calendar = Calendar.getInstance();
	        int  anio= Integer.parseInt(linea.substring(8,12));
	        int mes= Integer.parseInt(linea.substring(12,14));
	        int  dia= Integer.parseInt(linea.substring(14,16));
	        calendar.set(Calendar.DAY_OF_MONTH, dia);
	        calendar.set(Calendar.MONTH, mes-1);
	        calendar.set(Calendar.YEAR, anio);
	        result.setFechaProceso(calendar.getTime());
	        
	        result.setNombreArchivo(linea.substring(16,22));
	        result.setUrlArchivo("nada...");
	        result.setOperador(this.operador);
        }else
        {
        	this.formatoCorrecto = false;
        }
        return result;
	}
	
	
	public ExtractoBancario armarUltimaLinea(String linea, ExtractoBancario extractoBancario) {
		extractoBancario.setCantRegistroHF(Integer.parseInt(linea.substring(4, 10)));
		extractoBancario.setCantRegistroMF(Integer.parseInt((linea.substring(10, 16))));
        
        return extractoBancario;
	}
	
	public DetalleExtracto armarItem(String linea,int nroLinea)throws  FormatoNoValidoException,FormatoNoValidoImporteException{
		DetalleExtracto detalleExtracto = new DetalleExtracto();
	    
	    try{
	    	
	    	detalleExtracto.setTipoRegistro(linea.substring(0,1).charAt(0));
	    	if(detalleExtracto.getTipoRegistro().equals('M') || detalleExtracto.getTipoRegistro().equals('V')){
	    		
	    		detalleExtracto.setFechaMovimientoCadena(new Long(linea.substring(6,14)));//para comparar busquedas
	    		detalleExtracto.setConciliado('N');
	    		detalleExtracto.setCodigoBanco(new Long(linea.substring(1,4)));
	    		detalleExtracto.setNroCuentaCorto(linea.substring(4,6));
	    		//Fecha Movimiento
		        Calendar calendar = Calendar.getInstance();
		        int  anio= Integer.parseInt(linea.substring(6,10));
		        int mes= Integer.parseInt(linea.substring(10,12));
		        int  dia= Integer.parseInt(linea.substring(12,14));
		        calendar.set(Calendar.DAY_OF_MONTH, dia);
		        calendar.set(Calendar.MONTH, mes-1);
		        calendar.set(Calendar.YEAR, anio);
		        detalleExtracto.setFechaMovimiento(calendar.getTime());
		        //Fecha Valor
		        anio= Integer.parseInt(linea.substring(14,18));
		        mes= Integer.parseInt(linea.substring(18,20));
		        dia= Integer.parseInt(linea.substring(20,22));
		        calendar.clear();
		        calendar.set(Calendar.DAY_OF_MONTH, dia);
		        calendar.set(Calendar.MONTH, mes-1);
		        calendar.set(Calendar.YEAR, anio);
		        detalleExtracto.setFechaValor(calendar.getTime());

		        detalleExtracto.setSigno(linea.substring(22,23).charAt(0));
	    		
	    		detalleExtracto.setNroComprobante(linea.substring(40,52));
	    		detalleExtracto.setCodigoOperacion(linea.substring(52,55));
	    		//Fecha Proceso
		        anio= Integer.parseInt(linea.substring(55,59));
		        mes= Integer.parseInt(linea.substring(59,61));
		        dia= Integer.parseInt(linea.substring(61,63));
		        calendar.clear();
		        calendar.set(Calendar.DAY_OF_MONTH, dia);
		        calendar.set(Calendar.MONTH, mes-1);
		        calendar.set(Calendar.YEAR, anio);
		        detalleExtracto.setFechaProceso(calendar.getTime());
	    		
		        detalleExtracto.setDescripcion(linea.substring(63,91));
		        detalleExtracto.setSucursalOrigen(linea.substring(91,96));
		        detalleExtracto.setCodigoDepositante(linea.substring(96,104));
		        detalleExtracto.setNroCuenta(linea.substring(104,121));
	        	detalleExtracto.setCodigoOperacionBanco(linea.substring(121,126));
	        	
	    	}
	        
		    
	    } catch (NumberFormatException e) {
	    	String webAppHome = System.getProperty("catalina.home") + File.separator + "webapps";
			PropertieFile propertieFile = new PropertieFile(webAppHome + File.separator + "error.properties");
		    FileWriter fw;
			try {
				fw = new FileWriter(webAppHome+ "/"	+  "/"+ "erroresTran.txt",true);
				SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy.MM.dd  hh:mm aaa");
			    String  fecha=simpleDateFormat.format(new Date());
			    String error= "\n"+fecha +" "+ propertieFile.getProperties("formatNoValidoImporte")+ " Archivo: "+nombreArchivo+" linea: "+ String.valueOf(nroLinea)+" ";
				fw.write(error);
				fw.close();
	           throw new FormatoNoValidoException(propertieFile.getProperties("formatNoValidoImporte"));
			} catch (IOException e1) {
				e1.printStackTrace();
		   }
	    }	
	    
	    try{
	    	detalleExtracto.setImporte(new Double(linea.substring(23,40).trim())/100);
	        
	    }catch (NumberFormatException e) {
	    	String webAppHome = System.getProperty("catalina.home") + File.separator + "webapps";
			PropertieFile propertieFile = new PropertieFile(webAppHome + File.separator + "error.properties");
		    FileWriter fw;
			try {
				fw = new FileWriter(webAppHome+ "/"	+  "/"+ "erroresTran.txt",true);
				SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy.MM.dd  hh:mm aaa");
			    String  fecha=simpleDateFormat.format(new Date());
			    String error= "\n"+fecha +" "+ propertieFile.getProperties("formatNoValidoImporte")+ " Archivo: "+nombreArchivo+" linea: "+ String.valueOf(nroLinea)+" columna: 91,107 ";
				fw.write(error);
				fw.close();
	           throw new FormatoNoValidoImporteException(propertieFile.getProperties("formatNoValidoImporte"));
			} catch (IOException e1) {
				e1.printStackTrace();
		   }
		}     
	    
	    importeTotal += detalleExtracto.getImporte();
	    contadorDeItems++;
	 
	   
	    return detalleExtracto;
	}

	public int getContadorDeItems() {
		return contadorDeItems;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public Operador getOperador() {
		return operador;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getIdConciliados() {
		return idConciliados;
	}

	public void setIdConciliados(String idConciliados) {
		this.idConciliados = idConciliados;
	}

	public String getIdNoConciliados() {
		return idNoConciliados;
	}

	public void setIdNoConciliados(String idNoConciliados) {
		this.idNoConciliados = idNoConciliados;
	}
	public boolean isFormatoCorrecto() {
		return formatoCorrecto;
	}
	public ExtractoBancario getExtractoBancario() {
		return extractoBancario;
	}
	public Long getMinFecha() {
		return minFecha;
	}
	public Long getMaxFecha() {
		return maxFecha;
	}
	public Date getMinFechaDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(this.minFecha.toString().trim().substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(this.minFecha.toString().trim().substring(4, 6))-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(this.minFecha.toString().substring(6, 8)));
		Date minfechaDate = cal.getTime();
		return minfechaDate;
	}
	
	public Date getMaxFechaDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(this.maxFecha.toString().substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(this.maxFecha.toString().substring(4, 6))-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(this.maxFecha.toString().substring(6, 8)));
		Date maxfechaDate = cal.getTime();
		return maxfechaDate;
	}
	public String getMensageResultado() {
		return mensageResultado;
	}
	public void setMensageResultado(String mensageResultado) {
		this.mensageResultado = mensageResultado;
	}
	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}
	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	
}
