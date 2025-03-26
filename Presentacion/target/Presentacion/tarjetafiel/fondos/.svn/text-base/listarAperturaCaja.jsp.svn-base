<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Listado de Movimientos"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('listadoCajaApertura');">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${MovimientoBean.tituloCorto}
    <small>${MovimientoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="listadoCajaApertura">
		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>
						
						<h:panelGrid columns="1" id="uno" width="850" align="center">
						<s:layoutingTitlePane id="filtroCaja" label=" Filtro Caja" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<h:panelGrid id="filtroUno" columns="3" width="300" align="center">
								<h:panelGroup>
									<h:outputText value="Id Caja:" styleClass="texto"/>
									<h:inputText id="idcajaFiltro" value="#{AperturaCajaBean.idCaja}"
										styleClass="bordeceldainferior" maxlength="10" size="10"
										style=" width : 72px;" onfocus="encender(this);" onblur="apagar(this);"
										onkeypress="return soloEnteros(this,event);"/>
								</h:panelGroup>

								
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="btnBuscar" value="Buscar" onclick="agregarCaja.show();"
									action="#{AperturaCajaBean.listarAperturaCaja}" title="Busca las cajas abiertas" styleClass="botones"/>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							</h:panelGrid>
						</s:layoutingTitlePane>
						</h:panelGrid>
						
						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850">
						 <c:if test="${!empty AperturaCajaBean.cajaList}">			
											<h:dataTable value="#{AperturaCajaBean.cajaList}" id="listaCaja"
														 styleClass="standardTable" 
							                             headerClass="standardTable_Header" 
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="cajaAper" style=" width : 700px;">
						                       							
					                            
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Id caja" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{cajaAper.cajaApertura.caja.idCaja}" />
						                        </h:column>
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Descripcion Caja" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{cajaAper.cajaApertura.caja.descripcion}" />
						                        </h:column>
						                        <h:column>
						                           <h:commandLink   value="ver detalles"   action="#{cajaAper.mostrarDetAperturaCaja}" 
						                           id="mostrarDetCajaAperturaLink">
						                                 <f:param name="idCaja" value="#{cajaAper.cajaApertura.caja.idCaja}"/> 
						                           </h:commandLink>
						                           
						                        </h:column>
						                     </h:dataTable>   
						               </c:if>
                     	  </h:panelGrid>
                     	  <s:fieldset legend="Datalles seleccion" rendered="#{AperturaCajaBean.mostrarDetalleApertura}">
                                 <h:panelGrid id="panelPrincipalUno" columns="2" width="600" align="center">
							<h:outputText value="Operador" styleClass="texto"/>
							<h:selectOneMenu id="lstOperador" value="#{AperturaCajaBean.idOperadorSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{AperturaCajaBean.operadorItems}"/>
							</h:selectOneMenu>	
							<h:outputText value="Saldo Anterior" styleClass="texto"/>
							<h:outputText id="nombreFiltro" value="#{AperturaCajaBean.cajaApertura.saldoInicial}"/>
							<h:outputText value="Saldo Anterior" styleClass="texto"/>
							<h:outputText id="saldoAnt" value="#{AperturaCajaBean.saldoInicial}"/>
						    
						</h:panelGrid>
						   <s:fieldset legend="Gestionar Medio de Pago">
							<h:panelGrid id="panel2" columns="1" style="height: 150" >
										<h:panelGrid columns="2" id="panelFormaPago" >
						                <h:outputText value="Formas de Pago Habilitadas" style="FONT-SIZE: large;" styleClass="texto"/>
			                        
										<f:verbatim>&nbsp;</f:verbatim>
											 <c:if test="${!empty AperturaCajaBean.tablaDeFormaDePago}">			
											<h:dataTable value="#{AperturaCajaBean.tablaDeFormaDePago}" id="tablaFormaPago"
														 styleClass="standardTable" 
							                             headerClass="standardTable_Header" 
							                             footerClass="standardTable_Header"
							                             rowClasses="standardTable_Row1,standardTable_Row2"
							                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
														 var="forma" style=" width : 700px;">
						                       							
					                            
						                        <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Forma Pago" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.formaPago.descripcion}" />
						                        </h:column>
						                        
						                         <h:column>
						                            <f:facet name="header">
						                                <h:outputText value="Monto" styleClass="texto" />
						                            </f:facet>
						                            <h:outputText value="#{forma.monto}" />
						                        </h:column>
						                        
						                        
						                   </h:dataTable>
					                        </c:if>
                    			          	<c:if test="${empty AperturaCajaBean.tablaDeFormaDePago}">
								         	<h:outputText value="No existen Formas de Pago." styleClass="texto" style="color:green"/>
								        	</c:if>
					            	</h:panelGrid>     
			                    </h:panelGrid>
					          </s:fieldset>		      
                         
                         </s:fieldset>
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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{AperturaCajaBean.irAListarAperturaCaja}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>
