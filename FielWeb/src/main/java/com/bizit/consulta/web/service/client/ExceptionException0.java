
/**
 * ExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package com.bizit.consulta.web.service.client;

public class ExceptionException0 extends java.lang.Exception{
    
    private com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.ExceptionE faultMessage;
    
    public ExceptionException0() {
        super("ExceptionException0");
    }
           
    public ExceptionException0(java.lang.String s) {
       super(s);
    }
    
    public ExceptionException0(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.ExceptionE msg){
       faultMessage = msg;
    }
    
    public com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.ExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    