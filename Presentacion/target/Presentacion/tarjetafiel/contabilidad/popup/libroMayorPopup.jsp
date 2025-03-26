<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{LibroMayorBean.tituloLargo}"/></title>
	
</head>

<%@include file="/inc/head.inc" %>





<body >

<center>
	

	<h:form id="asientosForm">


		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="700px">
					

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					
					<x:commandLink id="aceptarOK" forceId="true" style="display: block;"/>
					    <s:fieldset legend="Libro Mayor" id="primerField">
					    <h:panelGrid id="imp" columns="1" align="center">
					        <h:panelGrid id="panelBusUno" columns="2" align="center" rendered="false">   
					            <h:outputText value="Sucursal:" styleClass="texto" rendered="false"/>
			                	<h:outputText value="#{LibroMayorBean.sucursalVista}" styleClass="textoblue" rendered="false"/>
			                </h:panelGrid>
			                <h:panelGrid id="panelBusDos" columns="6" align="center">
			                    <h:outputText value="Ejercicio:" styleClass="texto"/>
			                	<h:outputText value="#{LibroMayorBean.ejercicioVisto}" styleClass="textoblue"/>
			                    <h:outputText value="Desde:" styleClass="texto"/>
								<h:outputText value="#{LibroMayorBean.fechaInicioVista}" styleClass="textoblue"/>		            
							    <h:outputText value="Hasta:" styleClass="texto"/>
								<h:outputText value="#{LibroMayorBean.fechaCierreVista}" styleClass="textoblue"/>
			                </h:panelGrid>
			                <h:panelGrid id="secun" columns="2" align="center">
			                     <h:outputText value="Cuenta a Buscar: " styleClass="texto"/>
								 <h:outputText value="#{LibroMayorBean.cuentaVista}"  styleClass="textoblue" />
			                </h:panelGrid>
			                 <h:panelGrid id="fecha" columns="6" align="center">
				                <h:outputText value="Lotes desde:" styleClass="texto"/>
								<x:inputCalendar id="fechaInicioBusqueda" monthYearRowClass="yearMonthHeader" 
								weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
	                			currentDayCellClass="currentDayCell" value="#{LibroMayorBean.fechaInicioBusqueda}" renderAsPopup="true"
			              		styleClass="bordeceldainferior" style="width: 90px"
			                	popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
			                	helpText="DD/MM/YYYY" 
			                	forceId="true"/>
	                               <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<h:outputText value="Hasta:" styleClass="texto"/>
								<x:inputCalendar id="fechaCierreBusqueda" monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
	                			currentDayCellClass="currentDayCell" value="#{LibroMayorBean.fechaCierreBusqueda}" renderAsPopup="true"
			              		styleClass="bordeceldainferior" style="width: 90px"
			                	popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
			                	helpText="DD/MM/YYYY" 
			                	forceId="true"/>
			                	<h:commandButton action="#{LibroMayorBean.buscarAsientos}" value="Buscar" styleClass="botones"/>
			              </h:panelGrid>  
			             </h:panelGrid>
								
					     </s:fieldset>
					     
					     <c:if test="${empty LibroMayorBean.renglones}">
					        <h:outputText value="No existe ningún asiento de la cuenta especificada en el rango de fechas introducido." styleClass="bordeceldainferior" style="border:none; background:none;color:green;"/>
					     </c:if>
					     
					     <c:if test="${!empty LibroMayorBean.renglones}">
					     
					     <h:panelGrid id="saldoAnterior" columns="2" align="right" >
			                  	<h:outputText id="salAntCaption" value="Saldo Anterior:" styleClass="texto" style="color:green;"/>
			                  	<h:outputText id="salAnt" value="#{LibroMayorBean.saldoAnterior}" style="border:none; background:none;" styleClass="bordeceldainferior">
			                  		<f:converter converterId="moneyConverter"/>
			                  	</h:outputText>
					     </h:panelGrid>    
					     <h:panelGrid id="panelDeTabla" columns="1" align="center">
					                            <h:dataTable id="listado" styleClass="standardTable"
						                            headerClass="dataTable_Header"
						                            footerClass="standardTable_Header"
						                            rowClasses="standardTable_Row1,standardTable_Row2"
						                            columnClasses="standardTable_ColumnRight,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnRight,standardTable_ColumnRight,standardTable_ColumnRight"
						                            var="obj"
						                            value="#{LibroMayorBean.renglones}">
						                             
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Número de Asiento" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.asiento}" />
						                        </x:column>
						
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Fecha Contab" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.fechaContab}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Leyenda" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.leyenda}" />
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Debe" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.debe}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Haber" />
						                            </f:facet>
						                            <h:outputText value="#{obj.renglonLibroMayor.haber}">
						                            	<f:converter converterId="moneyConverter"/>
						                            </h:outputText>
						                        </x:column>
						                        
						                        <x:column>
						                            <f:facet name="header">
						                                <h:outputText value="Saldo"/>
                                                    </f:facet>
						                              <h:outputText value="#{obj.renglonLibroMayor.saldoAcumulado}" styleClass="tdB">
                                                           <f:converter converterId="moneyConverter"/>
                                                         <%--<f:convertNumber pattern="$###,###.##"/>--%>
                                                      </h:outputText>
						                        </x:column>
							                 </h:dataTable>
					     </h:panelGrid>
					     
					     <h:panelGrid id="totalesYSaldo" columns="6" align="center">
					            <h:outputText value="Total Debe: " styleClass="texto"/>
					            <h:outputText value="#{LibroMayorBean.debe}" styleClass="textoblue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <h:outputText value="Total Haber: " styleClass="texto"/>
					            <h:outputText value="#{LibroMayorBean.haber}" styleClass="textoblue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					            <h:outputText value="Saldo: " styleClass="texto"/>
					            <h:outputText value="#{LibroMayorBean.saldo}" styleClass="textoblue">
					            	<f:converter converterId="moneyConverter"/>
					            </h:outputText>
					     </h:panelGrid>
					     </c:if>
					     
					      <h:panelGrid id="boto" columns="10">
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
					             <h:commandButton action="#{LibroMayorBean.cancelar}" value="Volver" id="salir" styleClass="botones" />
					      </h:panelGrid>
					
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
		</x:panelLayout>
	</h:form>
</center>
</body>
</html>
</f:view>
