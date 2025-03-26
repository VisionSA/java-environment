
/**
 * ArchivoLiqComercioServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package com.bizit.consulta.web.service.client;

    /**
     *  ArchivoLiqComercioServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ArchivoLiqComercioServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ArchivoLiqComercioServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ArchivoLiqComercioServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getArchivoCuponesService method
            * override this method for handling normal response from getArchivoCuponesService operation
            */
           public void receiveResultgetArchivoCuponesService(
                    com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.GetArchivoCuponesServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getArchivoCuponesService operation
           */
            public void receiveErrorgetArchivoCuponesService(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getArchivoLiqComercio method
            * override this method for handling normal response from getArchivoLiqComercio operation
            */
           public void receiveResultgetArchivoLiqComercio(
                    com.bizit.consulta.web.service.client.ArchivoLiqComercioServiceStub.GetArchivoLiqComercioResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getArchivoLiqComercio operation
           */
            public void receiveErrorgetArchivoLiqComercio(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                


    }
    