<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{RetencionBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaRetencionForm').submit();
    	}
    </s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaRetencionForm');" onload="if('${RetencionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${RetencionBean.tituloCorto}
    <small>${RetencionBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<h:form id="altaRetencionForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!RetencionBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>   	      
	</h:panelGroup>
	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
            	
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{RetencionBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{RetencionBean.popup.nombreIcono}" />
					        <h:outputText value=" #{RetencionBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{RetencionBean.irANuevaRetencion}" 
				        		 onclick="clickLink('nuevoRetn');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva retención."/>

				        	<x:commandButton action="#{RetencionBean.irAModificarRetencion}" 
				        		 onclick="clickLink('modificarRetn');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando la retención."/>
							
							<x:commandButton action="#{RetencionBean.irAListarRetencion}" 
								 onclick="clickLink('listarRetn');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado de retenciones."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoRetn" action="#{RetencionBean.irANuevaRetencion}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarRetn" action="#{RetencionBean.irAModificarRetencion}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarRetn" action="#{RetencionBean.irAListarRetencion}" forceId="true" style="display: block;"/>
            	            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalIndividuos">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
             		<h:panelGrid columns="1" id="panelDatos">
	             		<h:panelGrid id="panelPrincipalUno" columns="5" width="850">
												
			                <h:outputText id="outDescripcion" value="Descripción: " styleClass="texto"/>
	    		            <h:inputText id="inDescripcion" value="#{RetencionBean.retencion.descripcion}" 
		                			 size="100" maxlength="100" styleClass="bordeceldatext" 
		                			 style=" width :150px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>

							
			                <h:outputText id="outCodigoReg" value="Cód. Regimen: " styleClass="texto"/>
	    		            <h:inputText id="inCodReg" value="#{RetencionBean.retencion.codigoRegimen}" 
		                			 size="3" maxlength="3" styleClass="bordeceldainferior" 
		                			 style=" width :40px;" onfocus="encender(this);" onblur="apagar(this);"/>
							
			                <h:outputText id="outCodigoNom" value="Cod. Nomina: " styleClass="texto"/>
	    		            <h:inputText id="inCodigoNom" value="#{RetencionBean.retencion.codigoNorma}" 
		                			 size="5" maxlength="5" styleClass="bordeceldatext" 
		                			 style=" width :90px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>							

			                <h:outputText id="outAcPagos" value="Acumula Pagos: " styleClass="texto"/>
		        			<x:selectBooleanCheckbox id="chkAcPagos" value="#{RetencionBean.acumulaPagos}"/>

							<h:outputText id="outMiniImp" value="Mínimo Imponible: " styleClass="texto"/>
			                <h:inputText id="inMiniImp" value="#{RetencionBean.retencion.minimoImponible}" 
		                			 	 size="12" maxlength="12" styleClass="bordeceldainferior" style=" width : 100px;"
		                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
		                	<f:verbatim>&nbsp;</f:verbatim>	                			 	 
		                	
							<h:outputText id="outMiniRet" value="Mínimo Retención: " styleClass="texto"/>
			                <h:inputText id="inMiniRet" value="#{RetencionBean.retencion.minimoRetencion}" 
		                			 	 size="12" maxlength="12" styleClass="bordeceldainferior" style=" width : 100px;"
		                			 	 onfocus="encender(this);" onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
		                	
			                <h:outputText id="outCtaCtble" value="Cta. Contable: " styleClass="texto"/>
	    		            <h:inputText id="inCtaCtble" value="#{RetencionBean.retencion.cuentaContable}" 
		                			 size="80" maxlength="80" styleClass="bordeceldatext" 
		                			 style=" width :100px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>		                	
							<f:verbatim>&nbsp;</f:verbatim>
		                	<f:verbatim>&nbsp;</f:verbatim>
		                	
							<h:outputText id="outVigenciaDesde" value="Vigencia Desde:" styleClass="texto"/>
					 		<f:verbatim>
				                <div class="input-group date">
				                    <div class="input-group-addon">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA">
				                </div>
							</f:verbatim>
					 		<f:verbatim>&nbsp;</f:verbatim>
							
							<h:outputText id="outVigenciaHasta" value="Vigencia Hasta:" styleClass="texto"/>
					 		<f:verbatim>
				                <div class="input-group date">
				                    <div class="input-group-addon">
				                 	   <i class="fa fa-calendar"></i>
				                    </div>
				                    <input type="text" class="form-control pull-right" id="fHasta" placeholder="DD/MM/AAAA">
				                </div>
							</f:verbatim>
		                	
							<h:outputText value="Provincia:" styleClass="texto" />
							<h:selectOneMenu value="#{RetencionBean.provinciaSeleccionada}" 
		        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
		        					         onblur="apagar(this);">
		       					<f:selectItems value="#{RetencionBean.listaProvincias}"/>
		       				</h:selectOneMenu>
							
		                	<f:verbatim>&nbsp;</f:verbatim>
		                	<f:verbatim>&nbsp;</f:verbatim>
		                	<f:verbatim>&nbsp;</f:verbatim>		                	
		                
						</h:panelGrid>
						<s:fieldset legend="Seleccion de Actividad" >
							<h:panelGrid id="panelPrincipalDos" columns="2" width="400">
								<h:outputText value="Tipo impuesto:" styleClass="texto" id="outputTipoImp" />
								<h:selectOneMenu id="lstTipoImp" value="#{RetencionBean.idTipoImpSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									binding="#{RetencionBean.tipoImpHtml}" valueChangeListener="#{RetencionBean.cambiarCategorias}"
									onblur="apagar(this);" style="width: 250px" onchange="submit();">
									<f:selectItems value="#{RetencionBean.tipoImpItems}" id="selectedTipoImp" />
								</h:selectOneMenu>
								
								<h:outputText id="outCategoria" value="Categoria:" styleClass="texto"/>
								<h:selectOneMenu id="lstCategoria" value="#{RetencionBean.categoriaSeleccionada}" 
			        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
			        					         onblur="apagar(this);" style="width: 250px" onchange="submit();"
			        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
			        					         binding="#{RetencionBean.categHtml}">
			       					<f:selectItems id="edCat" value="#{RetencionBean.categoriaItems}"/>
			       				</h:selectOneMenu>
			                	
								<h:outputText id="outJurisdiccion" value="Jurisdicción:" styleClass="texto"/>
								<h:selectOneMenu id="lstJuris" value="#{RetencionBean.jurisdiccionSeleccionada}" 
			        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
			        					         onblur="apagar(this);" onchange="submit();" style="width: 250px"
			        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
			        					         binding="#{RetencionBean.jurisHtml}">
			       					<f:selectItems id="adJur" value="#{RetencionBean.listaJurisdicciones}"/>
			       				</h:selectOneMenu>
								
								<h:outputText value="Aplicable:" styleClass="texto"/>
								<h:selectOneMenu value="#{RetencionBean.aplicableSeleccionada}" 
			        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
			        					         onblur="apagar(this);" onchange="submit();" style="width: 250px"
			        					         valueChangeListener="#{RetencionBean.cambiarActividad}"
			        					         binding="#{RetencionBean.aplicHtml}">
			       					<f:selectItems id="edApl" value="#{RetencionBean.listaAplicables}"/>
			       				</h:selectOneMenu>
								
								<h:outputText id="outActividad" value="Actividad:" styleClass="texto"/>
								<h:selectOneMenu id="lstActividad" value="#{RetencionBean.activodadSeleccionada}" 
			        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
			        					         onblur="apagar(this);" style="width: 250px">
			       					<f:selectItems id="edAct" value="#{RetencionBean.listaActividades}"/>
			       				</h:selectOneMenu>
							</h:panelGrid>
						</s:fieldset>
             		</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>	

				<%-- GESTIONAR TRAMOS --%>
				<s:layoutingTitlePane id="gestionarTramos" label=" Gestionar Tramos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >             								
						<h:panelGrid columns="2" id="panelExclusion" width="590">
						<x:dataTable value="#{RetencionBean.tramos}" id="tablaExcluciones"
									 styleClass="standardTable" sortable="true"
                                     headerClass="dataTable_Header"
                                     footerClass="standardTable_Header" 
                                     rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_ColumnRight"							
						             var="tramo" style=" width : 570px;">
	                        <x:column id="a_" defaultSorted="true">
	                            <f:facet name="header">
	                                <h:outputText value="Monto Desde" styleClass="texto"/>
	                            </f:facet>
	                            <h:outputText id="tramo_1" value="#{tramo.montoDesde}" style=" width : 192px;"/>
	                        </x:column>								
	                        
	                        <x:column id="b_">
	                            <f:facet name="header">
	                                <h:outputText value="Monto Hasta" styleClass="texto"/>
	                            </f:facet>
	                            <h:outputText id="tramo_2" value="#{tramo.montoHasta}" style=" width : 192px;"/>
	                        </x:column>									                        
		                        
	                        <x:column id="c_">
	                            <f:facet name="header">
	                                <h:outputText value="Monto Mínimo" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText id="tramo_3" value="#{tramo.montoMinimo}" style=" width : 40px;"/>
	                        </x:column>
	                        
	                        <x:column id="d_">
	                            <f:facet name="header">
	                                <h:outputText value="Porcentaje Retención" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText id="tramo_4" value="#{tramo.porcRetencion}" style=" width : 40px;"/>
	                        </x:column>	       
	                        
	                        <x:column id="e_">
								<x:commandLink action="#{RetencionBean.eliminarTramo}" id="eliminarTramoLink">
									<f:param id="idTramo" name="idTramo" value="#{tramo.idTramoRetencion}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar un tramo." border="0"/>								
								</x:commandLink>
							</x:column>	                        	                        
						</x:dataTable>
							<h:outputLink value="#" 
									      onclick="popup('#{facesContext.externalContext.requestContextPath}/tarjetafiel/impuestos/retenciones/popup/tramosPopup.jsf',700,400);" 
									      id="agregarTramoLink">
								<x:graphicImage value="/img/cat_pad.gif" 
												title="Agregar tramos." border="0"/>								
							</h:outputLink>						
					</h:panelGrid>
				</s:layoutingTitlePane>
				
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonGrabar" value="Guardar" action="#{RetencionBean.grabar}" styleClass="btn btn-primary btn-flat pull-right" />
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{RetencionBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
					</h:panelGrid>					
			</h:panelGrid>
           </h:panelGroup>
        </f:facet>

    </x:panelLayout>


    <h:inputText id="FechaDesde" value="#{RetencionBean.vigenciaDesde}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>
    <h:inputText id="FechaHasta" value="#{RetencionBean.vigenciaHasta}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>


</h:form>    
</center>

<div class="box-header"></div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{RetencionBean.inicializar}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    $("#fHasta").datepicker({
      autoclose: true,
    });

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("altaRetencionForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		//la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("altaRetencionForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00"; 
    });



  });
</script>


</body>
</html>
</f:view>
