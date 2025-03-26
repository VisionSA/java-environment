<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Edición de Concepto"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
    <s:script language="javascript">
		function recargar() {
			document.getElementById('amConceptoPopupForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
	</s:script>

</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amConceptoPopupForm');">
     
      
<center>
	

	<h:form id="amConceptoPopupForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ConceptosPopupBean.popup.mostrar}">
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
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal" >
		                 <h:panelGrid id="titu" align="center">
		                 	<h:outputText value="Concepto" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

                    <h:panelGrid id="popParaIndi" width="400" columns="1" align="center" rendered="#{!ConceptosPopupBean.paraAgregar}">
                    
                    <s:fieldset id="fielDatosConcepto" legend="Concepto">
					<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
						<h:outputText id="first" value="Descripcion:" styleClass="texto"/>
						<h:outputText id="second" value="#{ConceptosPopupBean.concepto.descripcion}" styleClass="textoblue"/>
						<h:outputText id="third" value="Sucursal:" styleClass="texto"/>
						<h:outputText id="fourth" value="#{ConceptosPopupBean.concepto.sucursal.label}" styleClass="textoblue"/>
					</h:panelGrid>
					<h:panelGrid id="altas" columns="4" align="center">
						<h:outputText id="fifth" value="Calcula Disponible:" styleClass="texto"/>
						<h:outputText id="sixth" value="#{ConceptosPopupBean.concepto.calculaDisponible}" styleClass="textoblue"/>
						<h:outputText id="seventh" value="Target:" styleClass="texto"/>
						<h:outputText id="eighth" value="#{ConceptosPopupBean.concepto.target}" styleClass="textoblue"/>
					</h:panelGrid>
					</s:fieldset>
                    
                    
                    	<h:outputText value="#{ConceptosPopupBean.titulo}" style="FONT-SIZE: large;" styleClass="texto"/>
			           <%--  
			                         Aca termino filtros
			                        <s:layoutingTitlePane id="filtroCliente" label=" Filtro Cliente" containerNodeClass="contentTitlePane" rendered="#{ConceptosPopupBean.verFiltroClientes}" labelNodeClass="labelTitlePane" >
										<h:panelGrid id="filtroUno" columns="4">
											<h:panelGroup id="pnaGrupal"> 
											<h:panelGrid columns="4" id="patronesDeBusqueda" align="center">
											    <h:outputText value="Cuit:" styleClass="texto"/>
												<h:inputText id="cuitFiltro" value="#{ConceptosPopupBean.clienteFiltro.individuo.cuil}"
												styleClass="bordeceldainferior" maxlength="11" size="13"
												style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"/>
												<h:outputText value="Apellido:" styleClass="texto"/>
												<h:inputText id="apellIndividuoFiltro" value="#{ConceptosPopupBean.clienteFiltro.individuo.apellido}"
												styleClass="bordeceldainferior"
												style="width: 150px" onfocus="encender(this);" onblur="apagar(this);"/>
											</h:panelGrid>
			                                <h:panelGrid columns="6">
			                                	<h:outputText value="Estado Cliente:" styleClass="texto"/>
												<h:selectOneMenu id="lstDeEstadosClienteFiltro" value="#{ConceptosPopupBean.clienteFiltro.estadoCliente}"
													styleClass="lista" immediate="true" onfocus="encender(this);"
													onblur="apagar(this);" >
													<f:selectItems value="#{ConceptosPopupBean.estadoClienteFiltroItems}"/>
												</h:selectOneMenu>
												<h:outputText value="Estado Cobranza:" styleClass="texto"/>
												<h:selectOneMenu id="lstDeEstadosCobreanzaFiltro" value="#{ConceptosPopupBean.clienteFiltro.estadoCobranza}"
													styleClass="lista" immediate="true" onfocus="encender(this);"
													onblur="apagar(this);" >
													<f:selectItems value="#{ConceptosPopupBean.estadoCobranzaFiltroItems}"/>
												</h:selectOneMenu>
												<h:outputText value="Estado:" styleClass="texto"/>
												<h:selectOneMenu id="lstDeEstadoshabiliadoConsumo" value="#{ConceptosPopupBean.clienteFiltro.habiliadoConsumo}"
													styleClass="lista" immediate="true" onfocus="encender(this);"
													onblur="apagar(this);" >
													<f:selectItems value="#{ConceptosPopupBean.estadoConsumoFiltroItems}"/>
												</h:selectOneMenu>
			                                </h:panelGrid>
			                                </h:panelGroup>
			                                <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<x:commandButton id="btnBuscar" 
											value="Buscar"
											action="#{ConceptosPopupBean.listarElementos}" 
											title="Busca el cliente Seleccionado"
											image="/img/icon/srch_24.gif"/>
			
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			
										</h:panelGrid>
									</s:layoutingTitlePane>  --%>
			                        
			            
			            
			            
			            
			            
			            
			             <c:if test="${!empty ConceptosPopupBean.listaElementos}">
			                       
			                        
			                        
			                        <h:dataTable value="#{ConceptosPopupBean.listaElementos}"
										id="tablaDetalles" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column,standardTable_Column,,standardTable_ColumnCentered,,standardTable_ColumnCentered"
										var="var" style=" width :700px;">
										
										<c:if test="${ConceptosPopupBean.verCampo1}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo1}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo1}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo2}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo2}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo2}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo3}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo3}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo3}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo4}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo4}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo4}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo5}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo5}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo5}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo6}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo6}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo6}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampoBoleanoDos}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampoBoleanoDos}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.campoBoleanoDos}"/>
										</h:column>
										</c:if>
										<h:column>
											<x:commandLink action="#{ConceptosPopupBean.eliminarElemento}" id="ediIndividuoLink">
												<f:param id="idElementoElim" name="idElementoElim" value="#{var.campoIdentificatorio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar #{ConceptosPopupBean.descripcionElemento}." border="0" id="botonIndividuoEdiTar" />
											</x:commandLink>
										</h:column>
										<%-- 
										<h:column>
											<f:facet name="header">
												<h:outputText value="Autorizado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.paraAutorizado}" rendered="#{var.permitoAutorizado}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Responsable" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.paraResponsable}" rendered="#{var.permitoResponsable}"/>
										</h:column>
										--%>
                    				</h:dataTable>
                    				            <%-- Aqui el paginador --%>
						                        <h:panelGrid id="botoneraPaginadorElementos" width="150" columns="7" align="center">
						                        	<h:commandLink id="botonPrimeraPaginaElementos" action="#{ConceptosPopupBean.primerRegistro}" rendered="#{ConceptosPopupBean.paginador.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage id="elPaginadorImage" value="/img/icon/skipb_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonPaginaAnteriorLote" action="#{ConceptosPopupBean.anteriorRegistro}" rendered="#{ConceptosPopupBean.paginador.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage id="elPaginadorImage2" value="/img/icon/rewnd_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:outputText value="Página " />
						                        	<h:selectOneMenu  id="lstDeEjerciciosPorSucursal" value="#{ConceptosPopupBean.paginador.idPaginaSeleccionada}" binding="#{ConceptosPopupBean.paginador.pagSeleccionada}"
			       					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ConceptosPopupBean.cambiarPagina}"
			       					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       					 						 <f:selectItems value="#{ConceptosPopupBean.paginador.comboDePaginas}" id="selectPagDeElem" />
			       									</h:selectOneMenu>	
			       									<h:outputText value="#{ConceptosPopupBean.paginador.estado}" />
						                        	<h:commandLink id="botonPaginaSiguienteLote" action="#{ConceptosPopupBean.siguienteRegistro}" rendered="#{ConceptosPopupBean.paginador.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage id="elPaginadorImage3" value="/img/icon/fastf_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonUltimaPaginaLote" action="#{ConceptosPopupBean.ultimoRegistro}" rendered="#{ConceptosPopupBean.paginador.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage id="elPaginadorImage4" value="/img/icon/skipf_16.gif" border="0" />
						                        	</h:commandLink>
						                        </h:panelGrid>
                    				</c:if>
                    				<c:if test="${empty ConceptosPopupBean.listaElementos}">
									<h:outputText value="#{ConceptosPopupBean.mensaje}" styleClass="texto" style="color:green"/>
									</c:if>
                    
                    
                    					
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<f:verbatim><hr align="center" width="500"></f:verbatim>
					
					<h:panelGrid columns="10" width="727" id="panelBotonera" >
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonAltIndiNuevo" value="Grabar" action="#{ConceptosPopupBean.grabarCambiosElementos}" styleClass="botones" rendered="#{ConceptosPopupBean.verBotonGrabar}"/>
						<x:commandButton id="buttonGrabar" value="Agregar" action="#{ConceptosPopupBean.agregarElemento}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Volver" action="#{ConceptosPopupBean.volver}" onclick="window.close();" styleClass="botones" />
					</h:panelGrid>
					
					</h:panelGrid>
					
					
					
					
					
					
					
					
					
					
					
					
					
					<h:panelGrid id="popParaIndiDos" width="400" columns="1" align="center" rendered="#{ConceptosPopupBean.paraAgregar}">
                    
                    
                    	<h:outputText id="otx1" value="#{ConceptosPopupBean.tituloAgregar}" style="FONT-SIZE: large;" styleClass="texto"/>
			             
			             <c:if test="${!empty ConceptosPopupBean.listaElementosDisponibles}">
			                        <h:dataTable value="#{ConceptosPopupBean.listaElementosDisponibles}"
										id="tablaDetallesDos" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column,standardTable_Column,,standardTable_ColumnCentered,,standardTable_ColumnCentered"
										var="var" style=" width :700px;">
										
										<c:if test="${ConceptosPopupBean.verCampo1}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo1}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo1}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo2}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo2}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo2}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo3}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo3}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo3}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo4}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo4}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo4}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo5}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo5}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo5}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampo6}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{ConceptosPopupBean.tituloCampo6}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo6}"/>
										</h:column>
										</c:if>
										<c:if test="${ConceptosPopupBean.verCampoBoleano}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="Agregar..." styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.campoBoleano}"/>
										</h:column>
										</c:if>
										<%--
										 <h:column>
											<x:commandLink action="#{ConceptosPopupBean.eliminarElemento}" id="ediIndividuoLink">
												<f:param id="idIndiEdi" name="idIndiEdi" value="#{var.campoIdentificatorio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar #{ConceptosPopupBean.descripcionElemento}." border="0" id="botonIndividuoEdiTar" />
											</x:commandLink>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Autorizado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.paraAutorizado}" rendered="#{var.permitoAutorizado}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Responsable" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.paraResponsable}" rendered="#{var.permitoResponsable}"/>
										</h:column>
										--%>
                    				</h:dataTable>
                    				            <%-- Aqui el paginador --%>
						                        <h:panelGrid id="botoneraPaginadorElementosDos" width="150" columns="7" align="center">
						                        	<h:commandLink id="botonPrimeraPaginaElementosBis" action="#{ConceptosPopupBean.primerRegistroBis}" rendered="#{ConceptosPopupBean.paginadorBis.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage id="paginadorBisImage" value="/img/icon/skipb_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonPaginaAnteriorLoteDos" action="#{ConceptosPopupBean.anteriorRegistroBis}" rendered="#{ConceptosPopupBean.paginadorBis.hayAnterior}" styleClass="botones">
						                        		<x:graphicImage id="paginadorBisImage1" value="/img/icon/rewnd_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:outputText value="Página " id="pag"/>
						                        	<h:selectOneMenu  id="lstDeEjerciciosPorSucursalDos" value="#{ConceptosPopupBean.paginadorBis.idPaginaSeleccionada}" binding="#{ConceptosPopupBean.paginadorBis.pagSeleccionada}"
			       					  						styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ConceptosPopupBean.cambiarPaginaBis}"
			       					 						 onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       					 						 <f:selectItems value="#{ConceptosPopupBean.paginadorBis.comboDePaginas}" id="selectPagDeElemBis" />
			       									</h:selectOneMenu>	
			       									<h:outputText id="otxpagnador" value="#{ConceptosPopupBean.paginadorBis.estado}" />
						                        	<h:commandLink id="botonPaginaSiguienteLoteBis" action="#{ConceptosPopupBean.siguienteRegistroBis}" rendered="#{ConceptosPopupBean.paginadorBis.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage id="paginadorBisImage2" value="/img/icon/fastf_16.gif" border="0" />
						                        	</h:commandLink>
						                        	<h:commandLink id="botonUltimaPaginaLoteBis" action="#{ConceptosPopupBean.ultimoRegistroBis}" rendered="#{ConceptosPopupBean.paginadorBis.haySiguiente}" styleClass="botones">
						                        		<x:graphicImage id="paginadorBisImage3" value="/img/icon/skipf_16.gif" border="0" />
						                        	</h:commandLink>
						                        </h:panelGrid>
                    				</c:if>
                    				<c:if test="${empty ConceptosPopupBean.listaElementosDisponibles}">
									<h:outputText id="otxMensaDisponibles" value="#{ConceptosPopupBean.mensajeElementosDisponibles}" styleClass="texto" style="color:green"/>
									</c:if>
                    
                    
                    					
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					
					<h:panelGrid columns="10" width="727" id="panelBotoneraBis">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonGrabarBis" value="Agregar" action="#{ConceptosPopupBean.aceptarAgregados}" styleClass="botones"/>
						<x:commandButton id="buttonCancelarBis" value="Volver" action="#{ConceptosPopupBean.cancelarAgregados}" styleClass="botones" />
					</h:panelGrid>
					
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
