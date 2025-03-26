<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Documentos Adjuntos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/jscript/common.js"></script>
    <s:script language="javascript">
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
<%@include file="/inc/head.inc"%>

<body onbeforeunload="ShowWait('documentoAdjuntoForm');">
<center>
<h:form id="documentoAdjuntoForm" enctype="multipart/form-data">
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="importacion" rendered="#{DocumentoAdjuntoBean.panelAdjuntar}">
		<h:outputText value="#{DocumentoAdjuntoBean.titulo}" style="FONT-SIZE: large;" styleClass="texto"/>
		
		
						<h:panelGrid columns="4" id="panelInternoOnce" width="650" align="center">
						
						
				    		<h:outputText value="Descripción: " id="outDesc" styleClass="texto"/>
							<h:inputText id="inDescrip" value="#{DocumentoAdjuntoBean.docAdjunto.descripcion}" maxlength="200" 
								styleClass="bordeceldatext" style="width: 150px" onfocus="encender(this);" 
								onblur="apagar(this);"/>
							<h:outputText value="Tipo Documentos: " rendered="#{DocumentoAdjuntoBean.verTiposDoc}" id="outTDoc" styleClass="texto"/>
							<h:selectOneMenu id="lstTDocs" rendered="#{DocumentoAdjuntoBean.verTiposDoc}" value="#{DocumentoAdjuntoBean.idTipoDocSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{DocumentoAdjuntoBean.listTipoDigital}" id="selectItemLstDocs"/>
							</h:selectOneMenu>
							
							<h:outputText value="Archivo: " id="outArch" styleClass="texto"/>
							<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" 
							maxlength="1000" required="true" value="#{DocumentoAdjuntoBean.uploadedFile}">
							</x:inputFileUpload>
										
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:commandButton value="Cancelar" onclick="window.close();" 
								styleClass="botones" id="btonDesabilitarTDocLink"/>
							<h:commandButton value="Adjuntar" action="#{DocumentoAdjuntoBean.saveFile}" 
								styleClass="botones" id="btonAdjuntarTDocLink"/>
						</h:panelGrid>
						
			</h:panelGroup>
			<h:panelGroup id="grup" rendered="#{!DocumentoAdjuntoBean.panelAdjuntar}">
						
						<h:panelGrid columns="1" width="650" align="center" id="panelListDocAdj">
						
						     <c:if test="${empty DocumentoAdjuntoBean.listTipoDocumentos}">
									<h:outputText value="No existen documentos adjuntos." styleClass="texto" style="color:green"/>
						     </c:if>
						     <c:if test="${!empty DocumentoAdjuntoBean.listTipoDocumentos}">
						     <h:dataTable value="#{DocumentoAdjuntoBean.listTipoDocumentos}"
						      id="tablaDocAdjuntos" styleClass="standardTable" headerClass="dataTable_Header"
								footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
						      var="obj" style=" width : 570px;">
						     	    <h:column>
										<f:facet name="header">
											<h:outputText value="Tipo Documento" styleClass="texto" style="font: bold;color: white;" />
										</f:facet>
										<h:outputText value="#{obj.tipoDocumento}" style=" width : 150px;" styleClass="texto"/>
									</h:column>
									
									<h:column>
										<f:facet name="header">
											<h:outputText value="Archivo" styleClass="texto" style="font: bold;color: white;"/>
										</f:facet>
										<h:commandLink value="#{obj.nombreArchivo}" action="#{obj.abrirArchivo}"/>
										
									</h:column>
						     
						            <h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto" style="font: bold;color: white;"/>
										</f:facet>
											<h:outputText value="#{obj.descripcion}" styleClass="texto" style="width: 150px" />
									</h:column>
										
									<h:column>	
									    <x:commandButton  action="#{obj.borrarArchivo}" image="/img/borrar.gif" id="botonImagenocho"/>
									</h:column>	
							
						     
						     </h:dataTable>
						</c:if>
						
						</h:panelGrid>
               
	    
		 <h:panelGrid id="botonera" columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<h:commandButton id="adjuntarArchi" value="Agregar Archivo" action="#{DocumentoAdjuntoBean.habilitarCarga}" styleClass="botones"/>
				<h:commandButton id="buttonCancelar" value="Volver" action="#{DocumentoAdjuntoBean.cancelar}" styleClass="botones" onclick="window.close();"/>
			</h:panelGrid>
		
	</h:panelGroup>
	</x:panelTabbedPane>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>