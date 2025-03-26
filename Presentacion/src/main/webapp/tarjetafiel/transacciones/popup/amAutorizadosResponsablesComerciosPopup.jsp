<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Agregar Autorizados y Responsables"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
    <s:script language="javascript">
		function recargar() {
			document.getElementById('amAutoResponForm').submit();
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

<body onbeforeunload="ShowWait('amAutoResponForm');">
     
      
<center>
	

	<h:form id="amAutoResponForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!CodComercioBean.popup.mostrar}">
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
		                 	<h:outputText value="INDIVIDUOS" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

                    <h:panelGrid id="popParaIndi" width="700" columns="1" align="center">
                    	<h:outputText value="Individuos Relacionados" style="FONT-SIZE: large;" styleClass="texto"/>
			             <c:if test="${!empty CodComercioBean.listaPosiblesIndividuos}">
			                        <h:dataTable value="#{CodComercioBean.listaPosiblesIndividuos}"
										id="tablaDetallesResponsables" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column,standardTable_Column,,standardTable_ColumnCentered,,standardTable_ColumnCentered"
										var="var" style=" width :700px;">
										
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Apellido y Nombre" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.nombre}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Documento" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.documento}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Cargo" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.cargo}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Teléfono" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.telefono}"/>
										</h:column>
										
										<h:column>
											<x:commandLink action="#{CodComercioBean.editarIndi}" id="ediIndividuoLink">
												<f:param id="idIndiEdi" name="idIndiEdi" value="#{var.indice}"/>
												<x:graphicImage value="/img/editar.gif" title="Editar Individuo." border="0" id="botonIndividuoEdiTar" />
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
										
                    				</h:dataTable>
                    				</c:if>
                    				<c:if test="${empty CodComercioBean.listaPosiblesIndividuos}">
									<h:outputText value="No existen individuos relacionados a la sucursal." styleClass="texto" style="color:green"/>
									</c:if>
                    
                    
                    </h:panelGrid>					
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					
					<h:panelGrid columns="6" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:outputText id="outCuitBusqueda"  value="CUIT:"/>
						<h:inputText id="cuitBusqueda"  value="#{CodComercioBean.cuitIndividuoAAgregar}"/>
						<x:commandButton id="buttonAltIndiNuevo" value="Agregar Individuo al Comercio" action="#{CodComercioBean.darAltaNuevoIndividuo}" styleClass="botones"/>
						<x:commandButton id="buttonGrabar" value="Guardar" action="#{CodComercioBean.grabarDesdePopup}"  styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{CodComercioBean.cancelarDesdePopup}"  styleClass="botones" />
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
