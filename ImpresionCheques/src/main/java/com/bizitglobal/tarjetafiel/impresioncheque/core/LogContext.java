/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizitglobal.tarjetafiel.impresioncheque.core;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.LoggerContext;

/**
 *
 * @author sporta
 */
public class LogContext {

    private static LogContext logContext;

    private LoggerContext loggerContext = new LoggerContext();

    private LogContext() {
        BasicConfigurator.configure(loggerContext);
        loggerContext.start();
    }

    public static LogContext getInstance(){
        if(logContext == null){
            logContext = new LogContext();
        }

        return logContext;
    }

}
