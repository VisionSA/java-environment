package com.bizit.consulta.web.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;


@RemotingDestination
@Service
public class EmailService {
	
	@Autowired
    private transient MailSender mailTemplate;
	
	private static final String CATALINA_HOME = System.getProperty("catalina.home");
	
	public void sendMessage(String subject, String mailTo, String message) throws UsuarioWebException {
		 InputStream file;
		 Properties dbProps = new Properties();
		 
		try {
			//String dir = "D:/Proyectos/Fiel/tarjetafiel/FielWeb/target/fielWeb/WEB-INF/classes/META-INF/spring/email.properties";
			String dir = CATALINA_HOME + "/webapps/fielWeb/WEB-INF/classes/META-INF/spring/email.properties";
			file = new FileInputStream(dir);
			dbProps.load(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsuarioWebException("Error al cargar las configuraciones de correo");
		}
		 
		org.springframework.mail.SimpleMailMessage simpleMailMessage = new org.springframework.mail.SimpleMailMessage();
        simpleMailMessage.setFrom(dbProps.getProperty("email.mailFrom"));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(message);
        mailTemplate.send(simpleMailMessage);
    }

}
