package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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


public class ArchivoAcreditacion {
	private AcreditacionFondo acreditacionFondo;
    private int contadorDeItems;	
    private BigDecimal importeTotal;
	private Operador operador;
    private String nombreArchivo;
    private Long idChequeMinimo;
    private Long idChequeMaximo;

	private boolean formatoCorrecto=true;
	
	private Long minFecha;
	private Long maxFecha;
	
    public ArchivoAcreditacion(){
    	
    }
    public ArchivoAcreditacion(InputStream in, String name, int maxUploadFile, Operador operador)throws FormatoNoValidoImporteException, FormatoNoValidoException { 
		contadorDeItems = 0;
		importeTotal = new BigDecimal(0);
		nombreArchivo= name;
		this.operador = operador;
		BufferedReader d=null;
		Long fechaMaxima = 0L;
		try {
			d = new BufferedReader(new InputStreamReader(in));
            String cadenaTexto = d.readLine();
            acreditacionFondo = armarCabecera(cadenaTexto);
            if(formatoCorrecto)
            {
            	acreditacionFondo.setRegistroOriginal(cadenaTexto);
	            cadenaTexto = d.readLine();
	            int nroLinea= 0;
				while (cadenaTexto != null) {
					if(!cadenaTexto.substring(0, 1).equals("F"))//Si no es el registro final.
					{
						AcreditacionFondoDetalle acreditacionFondoDetalle = armarItem(cadenaTexto,nroLinea++);
						acreditacionFondoDetalle.setRegistroOriginal(cadenaTexto);
						acreditacionFondoDetalle.setAcreditacionFondo(acreditacionFondo);
						acreditacionFondo.getAcreditacionesDetalleOrdenado().add(acreditacionFondoDetalle);
						
						if(fechaMaxima< acreditacionFondoDetalle.getFechaSolicitudLong())
							fechaMaxima = acreditacionFondoDetalle.getFechaSolicitudLong();
					}
					else
					{
						acreditacionFondo = armarUltimaLinea(cadenaTexto, acreditacionFondo);
						acreditacionFondo.setRegistroOriginalFinal(cadenaTexto);
					}
					cadenaTexto = d.readLine();				
				}		
				acreditacionFondo.setImporteTotal(importeTotal);
				SortedSet c = acreditacionFondo.getAcreditacionesDetalleOrdenado();
				acreditacionFondo.setAcreditacionesFondoDetalle(c);
				idChequeMinimo = this.getChequeMinimo(c);
				//idChequeMinimo = ((AcreditacionFondoDetalle)c.first()).getIdCheque();;
				idChequeMaximo = ((AcreditacionFondoDetalle)c.last()).getIdCheque();
				acreditacionFondo.setFechaProcesoCadena(fechaMaxima.toString());
				acreditacionFondo.setIdBanco(((AcreditacionFondoDetalle)acreditacionFondo.getAcreditacionesDetalleOrdenado().first()).getBancoDebito());
				
				this.minFecha = buscarFechaMinima(c);
				this.maxFecha = buscarFechaMaxima(c);
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
    public Long getChequeMinimo(SortedSet sortSet)
    {
    	Long result=new Long(0);
    	AcreditacionFondoDetalle aux = null;
    	Iterator iter = sortSet.iterator();
    	while(iter.hasNext()){
    		aux = (AcreditacionFondoDetalle)iter.next();
    		if(aux.getIdCheque().longValue()>0L)
    		{
    			result = aux.getIdCheque(); 
    			break;
    		}
    	}
    	return result;
    }
    
    private Long buscarFechaMinima(SortedSet sortSet)
    {
    	Long result=new Long(99999999);
    	AcreditacionFondoDetalle aux = null;
    	Iterator iter = sortSet.iterator();
    	while(iter.hasNext()){
    		aux = (AcreditacionFondoDetalle)iter.next();
    		if(aux.getFechaSolicitudLong().longValue()<result)
    		{
    			result = aux.getFechaSolicitudLong(); 
    		}
    	}
    	return result;
    }
    
    private Long buscarFechaMaxima(SortedSet sortSet)
    {
    	Long result=new Long(0);
    	AcreditacionFondoDetalle aux = null;
    	Iterator iter = sortSet.iterator();
    	while(iter.hasNext()){
    		aux = (AcreditacionFondoDetalle)iter.next();
    		if(aux.getFechaSolicitudLong().longValue()>result)
    		{
    			result = aux.getFechaSolicitudLong(); 
    		}
    	}
    	return result;
    }
    
    public boolean validarCantidadItems()
    {
    	boolean result=false;
    	if(acreditacionFondo!=null && acreditacionFondo.getAcreditacionesDetalleOrdenado()!=null 
    			&& acreditacionFondo.getAcreditacionesDetalleOrdenado().size()>0)
    	{
    		if(acreditacionFondo.getTotalTranferenciasFinal().intValue()==this.contadorDeItems)
    			result=true;
    	}
    	return result;
    }

	public AcreditacionFondo armarCabecera(String linea) {
		AcreditacionFondo result = null;
        result = new AcreditacionFondo();
        
        if(linea!=null && linea.trim().length()==22)
        {
	        result.setTipoRegistroInicial(linea.substring(0, 1));
	        result.setNroCliente(linea.substring(2, 7));
	        result.setFechaProcesoCadena(linea.substring(8, 16));
	        
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
        }else
        {
        	this.formatoCorrecto = false;
        }
        return result;
	}
	
	
	public AcreditacionFondo armarUltimaLinea(String linea, AcreditacionFondo acreditacionFondo) {
        
        acreditacionFondo.setTipoRegistroFinal(linea.substring(0, 1));
        acreditacionFondo.setBlancosFinal(linea.substring(1, 4));
        acreditacionFondo.setTotalTranferenciasFinal(new Long(linea.substring(4, 10)));
        
        return acreditacionFondo;
	}
	
	public AcreditacionFondoDetalle armarItem(String linea,int nroLinea)throws  FormatoNoValidoException,FormatoNoValidoImporteException{
		AcreditacionFondoDetalle acreditacionFondoDetalle = new AcreditacionFondoDetalle();
	    
	    try{
	    	
	    	acreditacionFondoDetalle.setNroTransaccion(new Long(linea.substring(0,7)));
	    	//Fecha Solicitud
	        Calendar calendar = Calendar.getInstance();
	        int  anio= Integer.parseInt(linea.substring(7,11));
	        int mes= Integer.parseInt(linea.substring(11,13));
	        int  dia= Integer.parseInt(linea.substring(13,15));
	        calendar.set(Calendar.DAY_OF_MONTH, dia);
	        calendar.set(Calendar.MONTH, mes-1);
	        calendar.set(Calendar.YEAR, anio);
	        acreditacionFondoDetalle.setFechaSolicitud(calendar.getTime());
	        
	        //Solapamiento fecha y banco.
	        acreditacionFondoDetalle.setFechaSolicitudLong(new Long(linea.substring(7,15)));
	        acreditacionFondoDetalle.setFechaSolicitudCadena(linea.substring(7,15));
	        acreditacionFondoDetalle.setIdBanco(new Long(linea.substring(17,20)));
	        
	        acreditacionFondoDetalle.setTipoTrasferencia(Integer.parseInt(linea.substring(15,17)));
	        
	        acreditacionFondoDetalle.setBancoDebito(new Long(linea.substring(17,20)));
	        acreditacionFondoDetalle.setTipoCuentaDebito(Integer.parseInt(linea.substring(20,22)));
	        acreditacionFondoDetalle.setNroCuentaDebito(linea.substring(22,39).trim());
	        
	        acreditacionFondoDetalle.setBancoCredito(new Long(linea.substring(39,42)));
	        acreditacionFondoDetalle.setTipoCuentaCredito(Integer.parseInt(linea.substring(42,44)));
	        acreditacionFondoDetalle.setNroCuentaCredito(linea.substring(44,61).trim());
	        
	        acreditacionFondoDetalle.setNombreBeneficiario(linea.substring(61,90).trim());
	        
	        
	        acreditacionFondoDetalle.setMoneda(linea.substring(107,110).trim());
	        acreditacionFondoDetalle.setNumeroReferencia(new Long(linea.substring(110,117).trim()));
	        
	        //Guardamos el idCheque que viene en el archivo.
	        acreditacionFondoDetalle.setObservacion1(linea.substring(117,177).trim());
	        String arr[] = acreditacionFondoDetalle.getObservacion1().split("#");
	        if(arr!=null && arr.length>1)
	        	acreditacionFondoDetalle.setIdCheque(new Long(arr[1]));
	        else
	        	acreditacionFondoDetalle.setIdCheque(new Long(0));
	        
	        acreditacionFondoDetalle.setObservacion2(linea.substring(177,277).trim());
	        acreditacionFondoDetalle.setBlancos(linea.substring(277,321).trim());
	        acreditacionFondoDetalle.setFiller(linea.substring(321,350).trim());
	        
		    
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
	        acreditacionFondoDetalle.setImporteTrasferencia(new Double(linea.substring(90,107).trim())/100);
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
	    
	    importeTotal = importeTotal.add(new BigDecimal(acreditacionFondoDetalle.getImporteTrasferencia()));
	    contadorDeItems++;
	 
	   
	    return acreditacionFondoDetalle;
	}

	/***
     * Este metodo elimina del Set acreditacionDetalles los registros que ya fueron guardados en la base. 
     * @param List<Long>: con las fechas de los registros t_vis_fon_acredit_detalle almacenados en la base. 
     */
    public void eliminarRegistrosYaGuardados(List listRegistrosBase){
    	SortedSet sortNew = new TreeSet();
    	
    	Iterator iterBase = listRegistrosBase.iterator();	
		SortedSet sortSet = acreditacionFondo.getAcreditacionesDetalleOrdenado();
		
		while(iterBase.hasNext()){
			Long fechaBase = (Long)iterBase.next();
			
			Iterator iterSort = sortSet.iterator();
			while(iterSort.hasNext()){
				AcreditacionFondoDetalle detalle = (AcreditacionFondoDetalle)iterSort.next();
				if(!detalle.getFechaSolicitudLong().equals(fechaBase)){
					sortNew.add(detalle);
				}
			}
			sortSet = sortNew;
			sortNew = new TreeSet();
		}
		
		acreditacionFondo.setAcreditacionesDetalleOrdenado(sortSet);
		acreditacionFondo.setAcreditacionesFondoDetalle(sortSet);
    }
    
	public Long getIdChequeMinimo() {
		return idChequeMinimo;
	}

	public Long getIdChequeMaximo() {
		return idChequeMaximo;
	}

	public AcreditacionFondo getAcreditacionFondo() {
		return acreditacionFondo;
	}

	public int getContadorDeItems() {
		return contadorDeItems;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public Operador getOperador() {
		return operador;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public boolean isFormatoCorrecto() {
		return formatoCorrecto;
	}
	
	public Long getMinFecha() {
		return minFecha;
	}
	
	public Long getMaxFecha() {
		return maxFecha;
	}
	
	
	
	
}
