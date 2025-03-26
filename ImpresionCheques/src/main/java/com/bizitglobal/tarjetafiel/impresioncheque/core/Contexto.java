/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sporta
 */
public class Contexto {

    private static Logger logger = LoggerFactory.getLogger(Contexto.class);

    private static Contexto contexto;

    private ApplicationContext ctx;

    private Contexto() {
        
    }

    public static Contexto getInstance(){
        if(contexto == null){
            contexto = new Contexto();
            contexto.levantarContexto();
        }

        return contexto;
    }

    private void levantarContexto(){
        try{
            ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }        
    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    public Object getBean(String bean){
        return ctx.getBean(bean);
    }

}
