package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.EnvioEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.Contacts;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.EmailInCola;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.ValoresParanEmail;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.WspEnvio;
import com.bizitglobal.tarjetafiel.planificadoremail.service.RegistrationService;
import com.bizitglobal.tarjetafiel.general.dao.IGenericDao;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.bizitglobal.tarjetafiel.planificadoremail.service.impl.HttpPostMultipart;


/***** @Id:6958 ******/
public class SimpleRegistrationService implements RegistrationService {
	private IGenericDao genericDao;
	private JavaMailSender mailSender;
	private JavaMailSender mailSender1;
	private VelocityEngine velocityEngine;
	private EmailInCola emailInCola;


	// private EnvioEmailService envioEmailService = null;
	// private static final String ENVIO_EMAIL_SERVICE_NAME = "envioEmailService";

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}


	public String sendConfirmationEmail(final EmailInCola emailInCola) {
		String envioMail = "P";
		this.emailInCola = emailInCola;
		String msj= "";
		WspEnvio wspEnvio = null;
		Contacts params = null;		
		
		 if (emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 20) {
			
			String chatPlatform = "whatsapp";
			String chatChannelNumber = "5402644293400";
			String ruleNameOrId = "resumen_v4";			
			String platformContactId = emailInCola.getIdIdentificador()+"";
			
			String vencimiento = " " ;
			String estado_cuenta = " ";
			String total = " ";
			String id_cliente = " ";
			String idCliente = " ";
			String headerDocumentUrl = " ";
			String nombre = " ";
			String headerDocumentCaption = "Ver Resumen";
			
			
			List lstValores = Convertidores.setToList(emailInCola
					.getValoresParam());
			Iterator<ValoresParanEmail> iterParam = lstValores.iterator();
			while (iterParam.hasNext()) {
				ValoresParanEmail valor = (ValoresParanEmail) iterParam
						.next();
				
				if (valor.getNombre().equals("vencimiento")) {
					//vencimiento = 	valor.getValor();
					vencimiento = 	"30/03/2022";
				}
				if (valor.getNombre().equals("estado_cuenta")) {
					estado_cuenta = 	valor.getValor();
				}
				
				if (valor.getNombre().equals("total")) {
					total = 	valor.getValor();
				}
				if (valor.getNombre().equals("idcliente")) {
					id_cliente = 	valor.getValor();
					idCliente = 	valor.getValor();
				}
				
				if (valor.getNombre().equals("headerDocumentUrl")) {
					headerDocumentUrl = 	valor.getValor();					
				}
				
				if (valor.getNombre().equals("nombre")) {
					nombre = 	valor.getValor();					
				}				
				
			}
			
			  
			  msj = "" +
						"{" +
						    "\"chatPlatform\" : \"" + chatPlatform + "\"," +
						    "\"chatChannelNumber\": \"" + chatChannelNumber + "\"," +
						    "\"ruleNameOrId\": \"" + ruleNameOrId + "\"," +
						    "\"platformContactId\": \"" + platformContactId + "\"," +						    
                    			"\"vencimiento\":\"" + vencimiento + "\"," +
                    			"\"estado_cuenta\":\"" + estado_cuenta + "\"," +
                    			"\"total\":\"" + total + "\"," +
                    			"\"id_cliente\":\"" + id_cliente + "\"," +
                    			"\"idCliente\":\"" + idCliente + "\"," +
                    			"\"headerDocumentUrl\":\"" + headerDocumentUrl + "\"," +
                    			"\"headerDocumentCaption\":\"" + headerDocumentCaption + "\"," +
                    			"\"nombre\":\"" + nombre + "\"" +                     			
						"}";			
		
			
		} else {
			
		
			
			 msj = 	"{";
				
				List lstValores = Convertidores.setToList(emailInCola
						.getValoresParam());
				Iterator<ValoresParanEmail> iterParam = lstValores.iterator();
				while (iterParam.hasNext()) {
					ValoresParanEmail valor = (ValoresParanEmail) iterParam
							.next();
					if (valor.getNombre().equals("nombre")) {
						valor.getValor().replace("Ñ", "N");
					}
										
					
					msj +=	"\""+valor.getNombre()+"\" : \"" + valor.getValor().trim().replace("\"","") + "\",";
				}
				
				msj +=	"\"email\": \"" + emailInCola.getEmail() + "\"";
				msj += " }";
				System.out.println(" json ValoresParamEmail  " + msj );
		}	
			
				
		 if (emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 20){
			envioMail =	getBotmaker(emailInCola,msj);
		} else {
			
			
			//envioMail =	getSendinBlue(emailInCola,msj);
			envioMail =	getSendinBlue(emailInCola,msj);
		}
		
		return envioMail;

	}
	
	
	public String uploadFile() {
    	try {
    	    // Set header
    		Map<String, String> headers = new HashMap<String, String>();
    	   // headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
    	  //  HttpPostMultipart multipart = new HttpPostMultipart("http://localhost/index", "utf-8", headers);
    	  //  HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.76:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
    	    
    	    HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.76:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
 
    	    // Add form field
//    	    multipart.addFormField("username", "test_name");
//    	    multipart.addFormField("password", "test_psw");
    	    String bucket = "Comercio/2020-09-20";
    	    multipart.addFormField("bucket", bucket);
    	    // Add file
    	    multipart.addFilePart("file", new File("/usr/local/apache-tomcat-6.0.20/webapps/archivos//liquidacionesComercios/2020-09-20/A20-09-2020_310414_02378.pdf"));
    	    // Print result
    	    String response = multipart.finish();
    	    System.out.println(response);
    	    return "E";
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    return "R";
    	}
    }  
	
	private String getSendinBlue(EmailInCola emailInCola, String msj){
		
		 try{
			 
			 URL url = null;
			 
			  
			 if (emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 8 ||
					 emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 9 ||
					 emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 11 ||
					 emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 13 ||
					 emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 22 ||
					 emailInCola.getPlanProcesoEmail().getIdPlan().intValue() == 15) {
				  //url = new URL("http://15.228.58.39:8080/spring-boot-web/envioSms");
				  url = new URL("http://192.168.0.76:8080/spring-boot-web/envioSms");
				 
			 } else {
				 // url = new URL("http://15.228.58.39:8080/spring-boot-web/envioEmail");
				//  url = new URL("http://192.168.0.76:8080/spring-boot-web/envioEmail");
				  url = new URL("http://192.168.0.76:8080/spring-boot-web/envioEmailPublicidad");
				  
				 
			 }
			  
			  
			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("POST");
			  conn.setRequestProperty("Content-Type", "application/json");
			  conn.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			 // wr.writeBytes(URLEncoder.encode(myObject.toString(),"UTF-8"));
			  wr.writeBytes(msj);
			  wr.flush();
			  wr.close();
			  
			  int responseCode = conn.getResponseCode();
			  if (responseCode == 201 || responseCode == 200 ) {
				 
				  conn.disconnect();
				  return "E";
			  }
			  else{
				  conn.disconnect();
				  return "R";
			  }
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return "R";
		 }
	 }
	
	
	private String getBotmaker(EmailInCola emailInCola, String msj){
		 try{
			 
			 URL url = null;
			 
			
				  url = new URL("http://192.168.0.76:8080/spring-boot-web/envioWspPost");
				 
		
			  
			  
			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			  conn.setRequestMethod("POST");
			  conn.setRequestProperty("Content-Type", "application/json");
			  conn.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			 // wr.writeBytes(URLEncoder.encode(myObject.toString(),"UTF-8"));
			  
			  
			  
			  wr.writeBytes(msj);
			  wr.flush();
			  wr.close();
			  
			  int responseCode = conn.getResponseCode();
			  if (responseCode == 201 || responseCode == 200 ) {
				 
				  conn.disconnect();
				  return "E";
			  }
			  else{
				  conn.disconnect();
				  return "R";
			  }
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return "R";
		 }	 }



	public List buscarValores(EmailInCola emailInCola)
			throws EnvioEmailException {
		List resultado = null;
		try {
			String sql = " SELECT * FROM t_vis_pla_valores_param_email where c_id_emailincola = "
					+ emailInCola.getIdEmail();
			resultado = (List) this.genericDao.listarMapResulsetSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}


	public EmailInCola getEmailInCola() {
		return emailInCola;
	}


	public void setEmailInCola(EmailInCola emailInCola) {
		this.emailInCola = emailInCola;
	}


	public IGenericDao getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}


	public void setMailSender1(JavaMailSender mailSender1) {
		this.mailSender1 = mailSender1;
	}

}