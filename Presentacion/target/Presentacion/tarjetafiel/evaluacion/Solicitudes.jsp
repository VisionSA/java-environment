<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{SolicitudesBean.tituloLargo}"/></title>
    <s:script language="javascript">
		 function marcar(obj) {
		 	var len = document.getElementById('SolicitudesForm:listado_1').rows.length;
		    var val = obj.checked;
		     	
			for (var i = 0; i < len; i++){
				document.getElementById('SolicitudesForm:listado_1:'+i+':sel').checked = val;
			}
			
		}    
		 function marcar2(obj) {
		 	var len = document.getElementById('SolicitudesForm:listado_2').rows.length;
		    var val = obj.checked;
		     	
			for (var i = 0; i < len; i++){
				document.getElementById('SolicitudesForm:listado_2:'+i+':sel').checked = val;
			}
			
		} 		
    	function recargar() {
    		document.getElementById('SolicitudesForm').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" >
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${SolicitudesBean.tituloCorto}
    <small>${SolicitudesBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
<h:form id="SolicitudesForm">
   
	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >


        <f:facet name="body">
            <h:panelGroup id="body">
            	
				<h:panelGrid columns="1" align="center">	
				
					<s:layoutingTitlePane id="generarSolicitudes" label=" Generar Solicitudes" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >	
						<h:panelGrid id="filtroUno" columns="6" align="center">
							<h:panelGroup>
								<h:outputText value="Cantidad: " styleClass="texto"/>
								<x:inputText id="cantidadInput" value="#{SolicitudesBean.cantidad}" 
											 size="11" maxlength="11" styleClass="bordeceldainferior" 
											 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
											 onkeypress="return soloEnteros(this,event);" immediate="true" onchange="submit();"/>
							</h:panelGroup>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:panelGroup>
								<h:outputText value="Desde: " styleClass="texto"/>
								<x:inputText id="desdeInput" value="#{SolicitudesBean.desde}" disabled="true"
											 size="11" maxlength="11" styleClass="bordeceldainferior" 
											 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
											 onkeypress="return soloEnteros(this,event);"/>
							</h:panelGroup>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:panelGroup>
								<h:outputText value="Hasta: " styleClass="texto"/>
								<x:inputText id="desdeHasta" value="#{SolicitudesBean.hasta}"  disabled="true"
											 size="11" maxlength="11" styleClass="bordeceldainferior" 
											 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
											 onkeypress="return soloEnteros(this,event);"/>
							</h:panelGroup>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:commandButton action="#{SolicitudesBean.generarSolicitudes}" value="Generar" style=" height : 24px;"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>							
							<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>							
						</h:panelGrid>
					</s:layoutingTitlePane>				
										
					<s:layoutingTitlePane id="mostrarSolicitudes" label=" Lista de Solicitudes Generadas" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >	
						<h:panelGrid id="filtroDos" columns="4" align="center">							
							
							<h:panelGrid columns="7">
								<h:outputText value="Mostrar: " styleClass="texto"/>
								<f:verbatim>&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstOpciones" value="#{SolicitudesBean.opcionSeleccionada}" 
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     style=" width : 200px;"
		        					     valueChangeListener="#{SolicitudesBean.cambiarOpcion}" onchange="submit();"><%-- binding="#{SolicitudesBean.opciones}" --%>
	       							<f:selectItems value="#{SolicitudesBean.lstOpciones}" id="selectOpciones"/>
		        				</h:selectOneMenu>
						<%--	<h:outputText value="Impresas: " styleClass="texto"/>
								<x:selectBooleanCheckbox  value="#{SolicitudesBean.mostrarImpresas}" />
								<h:outputText value="No Impresas: " styleClass="texto"/>
								<x:selectBooleanCheckbox  value="#{SolicitudesBean.mostrarNoImpresas}"/>
								<h:outputText value="No Impresas: " styleClass="texto"/>
								<h:selectOneRadio value="#{SolicitudesBean.seleccionImpresas}">
									  <f:selectItem itemLabel="Impresas" itemValue="1"/>
									  <f:selectItem itemLabel="No Impresas" itemValue="2"/>								
								</h:selectOneRadio>--%>
		        			</h:panelGrid>
		        			
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>							
										
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 650; HEIGHT: 200px; ">
			                    <h:dataTable id="listado_1"
			                            styleClass="table-bordered table-striped"
			                            headerClass="dataTable_Header"
			                            footerClass="standardTable_Header"
			                            rowClasses="standardTable_Row1,standardTable_Row2" 
			                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"                            
			                            var="sol" 
			                            value="#{SolicitudesBean.solicitudesSeleccionables}"
			                            first="0"
			                            >
			
			                        <x:column defaultSorted="true">
			                            <f:facet name="header">
			                                <h:outputText value="Solicitud" styleClass="texto" />
			                            </f:facet>
			                            <h:outputText value="#{sol.numero}" styleClass="texto"/>
			                        </x:column>
			                   
			                        <x:column>
			                            <f:facet name="header">
			                                <h:outputText value="Promotor" styleClass="texto" />
			                            </f:facet>
			                            <h:outputText value="#{sol.promotor}" styleClass="texto"/>
			                        </x:column>
			
			                        <x:column>
			                            <f:facet name="header">
			                                <h:outputText value="Estado" styleClass="texto" />
			                            </f:facet>
			                            <h:outputText value="#{sol.solicitud.estados.descripcion}" styleClass="texto"/>
			                        </x:column>
			                        
			                        <x:column>
			                            <f:facet name="header">
			                                <h:outputText value="Impreso" styleClass="texto" />
			                            </f:facet>
			                            <h:outputText value="#{sol.solicitud.estaImpreso}" styleClass="texto"/>
			                        </x:column>
			                        
			                        <h:column>
			                            <f:facet name="header">
			                                <h:selectBooleanCheckbox  value="#{SolicitudesBean.selSolicitudes}" onclick="marcar(this);"/>
			                            </f:facet>                            
			                            <h:selectBooleanCheckbox id="sel"  value="#{sol.seleccionado}"/>
			                        </h:column>                         
			                	</h:dataTable>
			                </x:div>
			                
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
														
							<h:panelGrid columns="4">
								<h:panelGrid columns="2">
									<h:outputText value="Promotor: " styleClass="texto"/>
									<h:selectOneMenu value="#{SolicitudesBean.promotorSeleccionado}" 
		        					     			 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     			 style=" width : 200px;" onchange="submit();"
													 valueChangeListener="#{SolicitudesBean.cambiarPromotor}" >													 
											<f:selectItems value="#{SolicitudesBean.lstPromotores}" id="selectPromotores"/>											
									</h:selectOneMenu>
	
								</h:panelGrid>
								<h:commandButton onclick="confirm('Las solicitudes ya asignadas se reasignaran.')" action="#{SolicitudesBean.asignarSeleccionadas}" value="Asignar Seleccionadas" style=" height : 24px;"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:commandButton action="#{SolicitudesBean.imprimirSeleccionadas}" value="Imprimir Seleccionadas" style=" height : 24px;"/>																					
							</h:panelGrid>
							
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>								 					
							<f:verbatim>&nbsp;</f:verbatim>	
						</h:panelGrid>
					</s:layoutingTitlePane>
					
					
					<s:layoutingTitlePane id="mostrarSolicitudesPromotor" label=" Solicitudes del Promotor"
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >	
						<h:panelGrid id="filtroTres" columns="4" align="center">										
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 650; HEIGHT: 200px; ">
			                    <h:dataTable id="listado_2"
			                            styleClass="table-bordered table-striped"
			                            headerClass="dataTable_Header"
			                            footerClass="standardTable_Header"
			                            rowClasses="standardTable_Row1,standardTable_Row2" 
			                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"                            
			                            var="sol" 
			                            value="#{SolicitudesBean.solicitudesPromotorSeleccionables}"
			                            first="0"
			                            >
				                        <x:column defaultSorted="true">
				                            <f:facet name="header">
				                                <h:outputText value="Solicitud" styleClass="texto" />
				                            </f:facet>
				                            <h:outputText value="#{sol.numero}" styleClass="texto"/>
				                        </x:column>
				                   
				                        <x:column>
				                            <f:facet name="header">
				                                <h:outputText value="Promotor" styleClass="texto" />
				                            </f:facet>
				                            <h:outputText value="#{sol.promotor}" styleClass="texto"/>
				                        </x:column>
				
				                        <x:column>
				                            <f:facet name="header">
				                                <h:outputText value="Estado" styleClass="texto" />
				                            </f:facet>
				                            <h:outputText value="#{sol.solicitud.estados.descripcion}" styleClass="texto"/>
				                        </x:column>
				                        
				                        <x:column>
				                            <f:facet name="header">
				                                <h:outputText value="Impreso" styleClass="texto" />
				                            </f:facet>
				                            <h:outputText value="#{sol.solicitud.estaImpreso}" styleClass="texto"/>
				                        </x:column>
				                        
				                        <h:column>
				                            <f:facet name="header">
				                                <h:selectBooleanCheckbox  value="#{SolicitudesBean.selPromotorSolicitudes}" onclick="marcar2(this);"/>
				                            </f:facet>                            
				                            <h:selectBooleanCheckbox id="sel"  value="#{sol.seleccionado}"  />
										</h:column>
			                	</h:dataTable>
			                </x:div>	
			                
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
														
							<h:panelGrid columns="4">
								<h:panelGrid columns="2">
									<h:outputText value="Promotor: " styleClass="texto"/>
									<h:selectOneMenu value="#{SolicitudesBean.promotorReasignarSeleccionado}" 
		        					     			 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     			 style=" width : 200px;" onchange="submit();"
													 valueChangeListener="#{SolicitudesBean.cambiarPromotorReasignar}" >													 
											<f:selectItems value="#{SolicitudesBean.lstPromotores}" id="selectPromotoresReasignar"/>
									</h:selectOneMenu>	
								</h:panelGrid>
								<h:commandButton action="#{SolicitudesBean.reasignarSeleccionadas}" value="Reasignar Seleccionadas" style=" height : 24px;"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>								
								<h:commandButton action="#{SolicitudesBean.desasignarSeleccionadas}" value="Desasignar Seleccionadas" style=" height : 24px;"/>	

							</h:panelGrid>			                					
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>							
						</h:panelGrid>
					</s:layoutingTitlePane>
										
				</h:panelGrid>
           </h:panelGroup>
        </f:facet>

    </x:panelLayout>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{SolicitudesBean.inicializar}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
