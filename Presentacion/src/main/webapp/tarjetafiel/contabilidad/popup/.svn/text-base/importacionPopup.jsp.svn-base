<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Importar Asientos"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/jscript/common.js"></script>
</head>
<%@include file="/inc/head.inc"%>

<body onbeforeunload="ShowWait('importarAsientosForm');" onload="ocultarBarra();">
    <script language="javascript">
        var muestro = false;
		var bool = true;
		function mostrarBarra() {
		    document.getElementById('importarAsientosForm:barrita').style.display = '';
			return null;
		}
		function ocultarBarra() {
		    document.getElementById('importarAsientosForm:barrita').style.display = 'none';
		}
		
		function marcar() {
         	if (document.getElementById('importarAsientosForm:listado:boolTodos').checked) {
                for (i=0; document.getElementById('importarAsientosForm:listado:' + i + ':seleccionado')!=null;i++) {
                     document.getElementById('importarAsientosForm:listado:' + i + ':seleccionado').checked = true;
                }
                return false;
            } else {
            	for (i=0; document.getElementById('importarAsientosForm:listado:' + i + ':seleccionado')!=null;i++) {   
                     document.getElementById('importarAsientosForm:listado:' + i + ':seleccionado').checked = false;
                }
                return false;
            }
         }
		
	</script>

<center>
<h:form id="importarAsientosForm" >
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="importacion">
		<h:outputText value="#{ImportacionAsientosBean.titulo}" style="FONT-SIZE: large;" styleClass="texto"/>
		
	    
		
		
		<h:panelGroup id="listadoPanel">
			<f:verbatim>&nbsp;</f:verbatim>
			<f:verbatim>&nbsp;</f:verbatim>

			
				    <h:panelGrid id="panelDeTitulos">
                    <h:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            var="obj" 
                            value="#{ImportacionAsientosBean.renglones}">
                   
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Asiento" />
                            </f:facet>
                            <h:outputText value="#{obj.importable.numeroAsiento}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Concepto" />
                            </f:facet>
                            <h:outputText value="#{obj.importable.concepto}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha Contable" />
                            </f:facet>
                            <h:outputText value="#{obj.importable.fechaCarga}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                        
										<h:selectBooleanCheckbox value="#{ImportacionAsientosBean.todos}" id="boolTodos" onclick="marcar()"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" id="seleccionado"/> 
                        </h:column>                         
                	</h:dataTable>

                     <h:panelGrid id="botoneraPaginador" columns="7" align="center" rendered="#{!ImportacionAsientosBean.hayAsientos}">
						<h:commandLink id="botonPrimeraPagina" action="#{ImportacionAsientosBean.primerRegistroAsiento}" rendered="#{ImportacionAsientosBean.paginador.hayAnterior}" styleClass="botones">
						     <x:graphicImage value="/img/icon/skipb_16.gif" border="0"/>
						</h:commandLink>
						<h:commandLink id="botonPaginaAnterior" action="#{ImportacionAsientosBean.anteriorRegistroAsiento}" rendered="#{ImportacionAsientosBean.paginador.hayAnterior}" styleClass="botones">
						     <x:graphicImage value="/img/icon/rewnd_16.gif" border="0"/>
						</h:commandLink>
						<h:outputText value="Página " />
						<h:selectOneMenu  id="lstDeEjerciciosPorsucu" value="#{ImportacionAsientosBean.paginador.idPaginaSeleccionada}" binding="#{ImportacionAsientosBean.paginador.pagSeleccionada}"
			       			styleClass="lista" onfocus="encender(this);" immediate="true" valueChangeListener="#{ImportacionAsientosBean.cambiarPagina}"
			       			onblur="apagar(this);" style=" width : 50px;" onchange="submit();">
			       		<f:selectItems value="#{ImportacionAsientosBean.paginador.comboDePaginas}" id="selectEjerDeSucumA" />
			       	</h:selectOneMenu>
			        <h:outputText value="#{ImportacionAsientosBean.paginador.estado}" />
					<h:commandLink id="botonPaginaSiguiente" action="#{ImportacionAsientosBean.siguienteRegistroAsiento}" rendered="#{ImportacionAsientosBean.paginador.haySiguiente}" styleClass="botones">
					<x:graphicImage value="/img/icon/fastf_16.gif" border="0" />
					</h:commandLink>
					<h:commandLink id="botonUltimaPagina" action="#{ImportacionAsientosBean.ultimoRegistroAsiento}" rendered="#{ImportacionAsientosBean.paginador.haySiguiente}" styleClass="botones">
						<x:graphicImage value="/img/icon/skipf_16.gif" border="0" />
					</h:commandLink>
				 </h:panelGrid>        		


		</h:panelGrid>
				
				
			   
			
			<h:panelGrid id="barrita" align="center" style="display:hidden">
				<f:verbatim>
					<script type="text/javascript">
		 				 var bar=createBar(150,15,"white",1,"black","#333366",85,7,3,"");
					</script>
				</f:verbatim>
			</h:panelGrid>
			
			<h:panelGrid id="botonera" columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
               	<x:commandButton id="buttonAceptar" value="Importar" 
               					 action="#{ImportacionAsientosBean.importar}" onclick="mostrarBarra();"
               					 styleClass="botones"/>
				<x:commandButton id="buttonCancelar" value="Cancelar" 
				                 action="#{ImportacionAsientosBean.cancelar}" 
				                 styleClass="botones" onclick="window.close();"/>
			</h:panelGrid>
			
			
		</h:panelGroup>
	</h:panelGroup>
	</x:panelTabbedPane>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>