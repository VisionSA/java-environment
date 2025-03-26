<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Edición de Concepto"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />
    <s:script language="javascript">
		function recargar() {
			document.getElementById('renovarPlasticoPopupForm').submit();
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

<body onbeforeunload="ShowWait('renovarPlasticoPopupForm');">
     
      
<center>
	

	<h:form id="renovarPlasticoPopupForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!RenovarPlasticoBean.popup.mostrar}">
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
		                 	<h:outputText value="Historial de Plasticos" style="FONT-SIZE: large;" styleClass="texto"/>			
					     </h:panelGrid>
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

                    <h:panelGrid id="popParaIndi" width="400" columns="1" align="center" >
                 <%-- %>   
                    <s:fieldset id="fielDatosConcepto" legend="Concepto">
					<h:panelGrid id="panelPrincipalUno" columns="2" width="350" align="center">
						<h:outputText id="first" value="Descripcion:" styleClass="texto"/>
						<h:outputText id="second" value="#{RenovarPlasticoBean.concepto.descripcion}" styleClass="textoblue"/>
						<h:outputText id="third" value="Sucursal:" styleClass="texto"/>
						<h:outputText id="fourth" value="#{RenovarPlasticoBean.concepto.sucursal.label}" styleClass="textoblue"/>
					</h:panelGrid>
					<h:panelGrid id="altas" columns="4" align="center">
						<h:outputText id="fifth" value="Calcula Disponible:" styleClass="texto"/>
						<h:outputText id="sixth" value="#{RenovarPlasticoBean.concepto.calculaDisponible}" styleClass="textoblue"/>
						<h:outputText id="seventh" value="Target:" styleClass="texto"/>
						<h:outputText id="eighth" value="#{RenovarPlasticoBean.concepto.target}" styleClass="textoblue"/>
					</h:panelGrid>
					</s:fieldset>
					
					
										<c:if test="${RenovarPlasticoBean.verCampo2}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampo2}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo2}"/>
										</h:column>
										</c:if>
										<c:if test="${RenovarPlasticoBean.verCampo3}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampo3}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo3}"/>
										</h:column>
										</c:if>
										<c:if test="${RenovarPlasticoBean.verCampo4}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampo4}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo4}"/>
										</h:column>
										</c:if>
										<c:if test="${RenovarPlasticoBean.verCampo5}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampo5}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo5}"/>
										</h:column>
										</c:if>
										<c:if test="${RenovarPlasticoBean.verCampo6}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampo6}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.campo6}"/>
										</h:column>
										</c:if>
										<c:if test="${RenovarPlasticoBean.verCampoBoleanoDos}">
										<h:column>
											<f:facet name="header">
												<h:outputText value="#{RenovarPlasticoBean.tituloCampoBoleanoDos}" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:selectBooleanCheckbox value="#{var.campoBoleanoDos}"/>
										</h:column>
										</c:if>
										<h:column>
											<x:commandLink action="#{RenovarPlasticoBean.eliminarElemento}" id="ediIndividuoLink">
												<f:param id="idElementoElim" name="idElementoElim" value="#{var.campoIdentificatorio}"/>
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar #{RenovarPlasticoBean.descripcionElemento}." border="0" id="botonIndividuoEdiTar" />
											</x:commandLink>
										</h:column>
                    --%>
                    
		             	<c:if test="${!empty RenovarPlasticoBean.plasticos}">
							<h:panelGrid columns="2">
	                    		<h:outputText value="#{RenovarPlasticoBean.idCliente}" styleClass="texto"/>
								<h:outputText value="Hitorial Platico Nro: #{RenovarPlasticoBean.plasticoVista.label}" styleClass="texto"/>
								<x:div id="divPla" style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 475px; HEIGHT: 250px; border: 1px; margin-left: auto; margin-right: auto;">
		                        	<h:dataTable value="#{RenovarPlasticoBean.plasticos}" 
										id="tablaPlasticos" styleClass="standardTable" headerClass="dataTable_Header" 
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column,standardTable_Column,,standardTable_ColumnCentered,,standardTable_ColumnCentered"
										var="var" style=" width :350px;">
										<h:column>
											<f:facet name="header">
												<h:outputText value="Plasticos" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.plastico.label}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.plastico.estadoPlastico.descripcion}"/>
										</h:column>
										<h:column>
											<x:commandLink action="#{var.verPlastico}" id="ediIndividuoLink">
												<x:graphicImage value="/img/icon/OrderView.gif"  border="0" id="botonIndividuoEdiTar" />
											</x:commandLink>
										</h:column>
		                  			</h:dataTable>
								</x:div>
								<x:div id="divPlaHis" style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 475px; HEIGHT: 250px; border: 1px; margin-left: auto; margin-right: auto;">
									<h:dataTable value="#{RenovarPlasticoBean.plasticoHistorial}" 
										id="tablaPlasticosHistorial" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column,standardTable_Column,,standardTable_ColumnCentered,,standardTable_ColumnCentered"
										var="var" style=" width :350px;">
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.fechaEstado}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Lugar" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.plasticoLugar.descripcion}"/>
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Estado" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{var.plasticoEstado.descripcion}"/>
										</h:column>
		                  			</h:dataTable>
								</x:div>
							<h:panelGroup>
								<h:outputText value="El plastico se va a dar de baja por estar: #{RenovarPlasticoBean.motivoBaja.descripcion}" styleClass="texto"/>
								<x:commandButton id="buttonRenovar" value="Renovar" action="#{RenovarPlasticoBean.renovar}" styleClass="botones"/>
							</h:panelGroup>
							</h:panelGrid>
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
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{RenovarPlasticoBean.cancelar}" onclick="window.close();" styleClass="botones" />
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
