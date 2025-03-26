/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.LoggerContext;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.service.impl.CajaServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sporta
 */
public class Test {

   
    public static void main(String []args){
       
       Logger logger = LoggerFactory.getLogger(Test.class);
      
        try{       
            ApplicationContext ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
            CajaServiceImpl caja = (CajaServiceImpl)ctx.getBean("cajaServiceTarget");
            List<Caja> cajas = caja.getCajas();
            logger.info("Cajas ");
        } catch(Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }

}
