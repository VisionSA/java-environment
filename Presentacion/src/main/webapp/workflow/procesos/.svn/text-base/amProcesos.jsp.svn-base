<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{ProcesoBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('amProcesoForm').submit();
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

<%@include file="/inc/includes.jsp" %>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amProcesoForm');" onload="if('${ProcesoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ProcesoBean.tituloCorto}
    <small>${ProcesoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
<h:form id="amProcesoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ProcesoBean.popup.mostrar}">
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
            
            	<h:panelGrid columns="1" align="center" id="PanelPricipalProceso">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{ProcesoBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{ProcesoBean.popup.nombreIcono}" />
					        <h:outputText value=" #{ProcesoBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{ProcesoBean.irANuevoProceso}" 
				        		 onclick="clickLink('nuevoProc');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nuevo proceso."/>

				        	<x:commandButton action="#{ProcesoBean.irAModificarProceso}" 
				        		 onclick="clickLink('modificarProc');dojo.widget.byId('viewDialog').hide();"
				         		 value="Continuar" styleClass="btn btn-primary btn-flat" title="Seguir modificando el proceso."/>
							
							<x:commandButton action="#{ProcesoBean.irAListarProceso}" 
								 onclick="clickLink('listarProc');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado de procesos."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoProc" action="#{ProcesoBean.irANuevoProceso}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarProc" action="#{ProcesoBean.irAModificarProceso}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarProc" action="#{ProcesoBean.irAListarProceso}" forceId="true" style="display: block;"/>
				
				<h:panelGrid columns="2" id="panelDatos">
             		<h:panelGrid id="panelPrincipalUno" columns="3" width="350">
		                <h:outputText id="outTituloProceso" value="Titulo: " styleClass="texto"/>
    		            <h:inputText id="Titulo" value="#{ProcesoBean.proceso.titulo}" disabled="#{ProcesoBean.edicion}"
	                			 size="100" maxlength="100" styleClass="bordeceldatext" 
	                			 style=" width : 200px;margin-bottom:4px" onfocus="encender(this);" onblur="apagar(this);"/>
						<f:verbatim>&nbsp;</f:verbatim>
						
		                <h:outputText id="outComentario" value="Comentario: " styleClass="texto"/>
    		            <h:inputText id="Comentario" value="#{ProcesoBean.proceso.comentario}" 
	                			 size="255" maxlength="255" styleClass="bordeceldatext" 
	                			 style=" width : 200px;margin-bottom:4px" onfocus="encender(this);" onblur="apagar(this);"/>
						<f:verbatim>&nbsp;</f:verbatim>						
						
		                <h:outputText id="outVersion" value="Versión: " styleClass="texto" style="margin-top:4px" />
    		            <h:outputText id="Version" value="#{ProcesoBean.proceso.version}" styleClass="texto"/>
						<f:verbatim>&nbsp;</f:verbatim>
						
		                <h:outputText id="outRevision" value="Revisión: " styleClass="texto"/>
    		            <h:outputText id="Revision" value="#{ProcesoBean.proceso.revision}" styleClass="texto" style="margin-top:4px" />
						<f:verbatim>&nbsp;</f:verbatim>
					</h:panelGrid>
					
					<h:panelGrid id="panelPrincipalDos" columns="4" width="390">

		                <h:outputLabel id="outRol" for="lstRoles" value="Rol: " styleClass="texto"/>
						<h:selectOneMenu id="lstRoles" valueChangeListener="#{ProcesoBean.cambioRol}" 
								value="#{ProcesoBean.rolSeleccionado}" style="width:180px;margin-left: 10px;margin-bottom:4px"
	       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
	       					 	onblur="apagar(this);" onchange="submit();"
	       					 	binding="#{ProcesoBean.rolesHtml}">
	        				<f:selectItems id="itemRoles" value="#{ProcesoBean.roles}"/>
	       				</h:selectOneMenu>

						<h:outputLabel id="outCkCU" for="ckCU" value="CU Requerido: " styleClass="texto" style="margin-left:10px"/>
						<h:selectBooleanCheckbox id="ckCU" value="#{ProcesoBean.usarCU}" onclick="javascript:submit();" style="margin-left:10px"/>

		                <h:outputLabel id="outSupervisor" for="lstSupervisores" value="Supervisor: " styleClass="texto"/>
						<h:selectOneMenu id="lstSupervisores" value="#{ProcesoBean.operadorSeleccionado}" 
	       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
	       					 	onblur="apagar(this);" style="width:180px;margin-left: 10px;margin-bottom:4px">
	        				<f:selectItems id="itemSuper" value="#{ProcesoBean.operadores}"/>
	       				</h:selectOneMenu>

						<c:if test="${ProcesoBean.usarCU}">
						<h:outputLabel id="outTipoClave" for="lstTiposCU" value="Tipo de CU: " styleClass="texto"/>
						<h:selectOneMenu id="lstTiposCU" value="#{ProcesoBean.tipoCUSeleccionado}" 
	       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
	       					 	onblur="apagar(this);" style="width:180px;margin-left: 10px;margin-bottom:4px">
	        				<f:selectItems id="itemTiposCU" value="#{ProcesoBean.tiposCU}"/>
	       				</h:selectOneMenu>
						</c:if>

						<c:if test="${!ProcesoBean.usarCU}">
							<h:outputLabel id="blank1" value="    " />
							<h:outputLabel id="blank2" value="    " />
						</c:if>

						<h:outputLabel id="outArea" for="lstArea" value="Area: " styleClass="texto" rendered="#{!ProcesoBean.edicion}"/>
						<h:selectOneMenu id="lstArea" value="#{ProcesoBean.areaSeleccionada}" style="margin-left: 10px"
	       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
	       					 	onblur="apagar(this);" rendered="#{!ProcesoBean.edicion}">
	        				<f:selectItems id="itemArea" value="#{ProcesoBean.areas}"/>
	       				</h:selectOneMenu>
						
						<h:outputLabel value="Activo" for="chkActivo" styleClass="texto" rendered="#{!ProcesoBean.edicion}" style="margin-left:10px"/>
						<x:selectBooleanCheckbox id="chkActivo" value="#{ProcesoBean.proceso.activo}" rendered="#{!ProcesoBean.edicion}" style="margin-left:10px" />
					</h:panelGrid>
				</h:panelGrid>
				
				<c:if test="${!ProcesoBean.alta}">	
				<h:panelGrid columns="3" id="panelAdjunto" align="center">
					<s:layoutingTitlePane id="listadoAtributos" label=" Lista de Atributos"
						    containerNodeClass="contentTitlePaneAuto" labelNodeClass="labelTitlePaneAuto" >
						<h:panelGrid columns="1" id="panelAtributos" width="300">
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: auto; HEIGHT: 100px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:dataTable id="atributosList" var="atributo" value="#{ProcesoBean.atributos}" style=" width : 295px;">
									<h:column >
											<h:outputText value="#{atributo.nombre}" style="font-size: 12px;color: blue"/>
								    </h:column>
								    <h:column>
										<x:commandLink action="#{ProcesoBean.editarAtributo}" id="editarTareaLink">
											<f:param id="idProcesoAtributo" name="idProcesoAtributo" value="#{atributo.idProcesoAtributo}"/>
											<x:graphicImage value="/img/editar.gif" title="Editar el atributo." border="0"/>
										</x:commandLink>
									</h:column>
								    <h:column>
										<x:commandLink action="#{ProcesoBean.eliminarAtributo}" id="eliminarTareaLink">
											<f:param id="idProcesoAtributo" name="idProcesoAtributo" value="#{atributo.idProcesoAtributo}"/>
											<x:graphicImage value="/img/borrar.gif" title="Eliminar el atributo." border="0" 
											onclick="return confirm('¿Confirma la eliminación del atributo?');"/>
										</x:commandLink>
									</h:column>
								</h:dataTable>
							</x:div>
						</h:panelGrid>
					</s:layoutingTitlePane>
					
				<h:panelGrid columns="3" width="" id="panelBotoneraUno">
					<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
					<x:commandButton id="buttonInsertarAtributos" value="Insertar Atributo"
	           			action="#{ProcesoBean.insertarAtributo}"  styleClass="btn btn-primary btn-flat"
	           			disabled="#{ProcesoBean.proceso.discontinuado}"/>
	           		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	           		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>

		            <x:commandButton id="buttonAnexarTareas" value="Anexar Tarea"
			            action="#{ProcesoBean.irAInsertarTarea}"  styleClass="btn btn-primary btn-flat" 
			            disabled="#{ProcesoBean.proceso.discontinuado}"/>
	           		<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				</h:panelGrid>
				
				<s:layoutingTitlePane id="listadoParametros" label=" Lista de Parametros" 
						    containerNodeClass="contentTitlePaneAuto" labelNodeClass="labelTitlePaneAuto" >
					<h:panelGrid columns="1" id="panelParametros" width="300">
						<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: auto; HEIGHT: 100px; border: 1px; margin-left: auto; margin-right: auto;">
							<x:dataTable id="parametrosList" var="parametro" value="#{ProcesoBean.parametros}">
								<h:column >
										<h:outputText value="#{parametro.nombre}" style="font-size: 12px;color: blue"/>
							    </h:column>	
							</x:dataTable>
						</x:div>
					</h:panelGrid>
				</s:layoutingTitlePane>
			</h:panelGrid>			
           		
           		           		
           		
				<f:verbatim>
					<br>
				</f:verbatim>				
				

				<%-- TAREAS PARA EL PROCESO --%>
				<s:layoutingTitlePane id="listadoTareas" label=" Lista de tareas" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >

						<h:dataTable value="#{ProcesoBean.tablaTareas}" id="tablaTareas"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column"							             
						             var="tareaTabla" style=" width : 900px;" align="center">

	                        <h:column>
	                            <x:commandLink id="agregarEditarAnexoTareaLink" action="#{ProcesoBean.editarTarea}" 
	                            			disabled="#{ProcesoBean.proceso.discontinuado}">
									<f:param id="idTareaProceso" name="idTareaProceso" value="#{tareaTabla.tareaProceso.idTareaProceso}"/>
									<x:graphicImage value="/img/editar.gif" title="Editar la asociacion de la tarea." border="0"/>
								</x:commandLink>
	                        </h:column>
						    
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Titulo" styleClass="texto" />
	                            </f:facet>
                                <h:outputText value="#{tareaTabla.tarea.titulo}" styleClass="texto" />
                                <h:outputText value="#{tareaTabla.ordenText}" style="font-size: 10px;color: orange" />
	                        </h:column>
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Comentario" styleClass="texto" />
	                            </f:facet>
                                <h:outputText value="#{tareaTabla.tarea.comentario}" styleClass="texto" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Duración" styleClass="texto"/>
	                            </f:facet>
                                <h:outputText value="#{tareaTabla.tarea.duracion}" styleClass="texto" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Rol Asignado" styleClass="texto" />
	                            </f:facet>
                                <h:outputText value="#{tareaTabla.rolAsignado}" styleClass="texto" />
	                        </h:column>

	                        <h:column>
								<x:commandLink action="#{ProcesoBean.administrarFlujosDeTarea}" id="adminFlujosLink">
									<f:param id="idTareaProceso" name="idTareaProceso" value="#{tareaTabla.tareaProceso.idTareaProceso}"/>
									<x:graphicImage value="/img/icon/redo_24.png" title="Administrar flujos de la tara." border="0"/>
								</x:commandLink>
							</h:column>
							
							<h:column>
								<x:commandLink id="agregarAtributoLink" action="#{ProcesoBean.agregarAtributosATarea}">
									<f:param id="idTareaProceso" name="idTareaProceso" value="#{tareaTabla.tareaProceso.idTareaProceso}"/>
									<x:graphicImage value="/img/cat_pad.gif" title="Agregar atributos." border="0"/>
								</x:commandLink>
							</h:column>
						    		                        
	                        <h:column>
								<x:commandLink action="#{ProcesoBean.eliminarTarea}" id="eliminarTareaLink"
								disabled="#{ProcesoBean.proceso.discontinuado}">
									<f:param id="idTareaTabla" name="idTareaTabla" value="#{tareaTabla.idTareaTabla}"/>
									<x:graphicImage value="/img/borrar.gif" title="Eliminar la tarea." border="0" 
									onclick="return confirm('¿Confirma la eliminación de la tarea?');"/>
								</x:commandLink>
							</h:column>	                        
						</h:dataTable>
						
				</s:layoutingTitlePane>
			
			</c:if>
				
			<f:verbatim><hr align="center" width="800"></f:verbatim>
			<h:panelGrid id="panelbtn" columns="1" width="750" align="center">
				<h:panelGroup>
					<x:commandButton id="buttonGrabar" value="Guardar" actionListener="#{ProcesoBean.grabarProcesoListener}" 
						styleClass="btn btn-primary btn-flat pull-right"  style="margin-left:10px;" disabled="#{ProcesoBean.proceso.discontinuado}"/>
					<x:commandButton id="buttonCancelar" value="Volver al listado " action="#{ProcesoBean.cancelar}" 
						styleClass="btn btn-primary btn-flat pull-right"  style="margin-left:10px;"/>
				</h:panelGroup>			
			</h:panelGrid>
				
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ProcesoBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
