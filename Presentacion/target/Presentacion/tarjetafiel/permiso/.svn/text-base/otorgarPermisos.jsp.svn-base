<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   <title><h:outputText value="Tarjeta Fiel - Otorgar permisos"/></title>
	<s:script language="javascript">
		 function marcar(obj) {
		 	var len = document.getElementById('otorgarPermisos:listado_1').rows.length;
		    var val = obj.checked;
		     	
			for (var i = 0; i < len; i++){
				document.getElementById('otorgarPermisos:listado_1:'+i+':sel').checked = val;
			}
			
		}
    </s:script>     
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('otorgarPermisos');">

<h:form id="mainMenu" style="display: none">
    <jsp:include page="/inc/navigation_test.jsp" />
    <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${PermisoBean.tituloCorto}
    <small>${PermisoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
    <div class="box box-default">
        <div class="box-header"><h3></h3>
        </div>

<center>

<secure:check/>

<h:form id="otorgarPermisos" >
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >
        
        <f:facet name="body">
			<h:panelGroup id="body">
            
            <%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
            
	        <h:panelGrid columns="1" align="center" id="panelGUno">
              	<h:panelGrid id="grid11" columns="4" width="350" align="center">
              		<h:panelGrid columns="4" id="panelRol">
		        		
		        		<h:outputText value="Rol:" styleClass="texto"/>
						<h:selectOneMenu id="lstRoles" value="#{PermisoBean.rolSeleccionado}" styleClass="lista" immediate="true">
		        			<f:selectItems value="#{PermisoBean.roles}" id="selectOperadores"/>
		        		</h:selectOneMenu>
		        		
		        		<x:commandLink id="editarRolLink" action="#{PermisoBean.editarRol}">
								<x:graphicImage value="/img/editar.gif" title="Editar la descripción del rol." border="0"/>								
						</x:commandLink>
						
						<x:commandLink id="nuevoRolLink" action="#{PermisoBean.nuevoRol}">
								<x:graphicImage value="/img/cat_pad.gif" title="Nuevo Rol." border="0"/>								
						</x:commandLink>
		        		
					</h:panelGrid>
					
					<h:inputText id="razonSocialFiltro" value="#{PermisoBean.rol.descripcion}" styleClass="bordeceldatext" maxlength="45" size="30" 
			        	style="width: 200px" onfocus="encender(this);" onblur="apagar(this);" rendered="#{PermisoBean.verRol}"/>
			       
					<h:commandButton id="grabarRol" value="Grabar" action="#{PermisoBean.grabarRol}" styleClass="btn btn-primary btn-flat pull-right" 
						rendered="#{PermisoBean.verRol}"/>
					<h:commandButton id="cancelarRol" value="Cancelar" action="#{PermisoBean.cancelarRol}" 
                				styleClass="btn btn-primary btn-flat pull-right" rendered="#{PermisoBean.verRol}"/>
	               
					<h:panelGroup rendered="#{!PermisoBean.verRol}" id="panelPor">
					
		        		<h:outputText value="Por:" styleClass="texto"/>
						<h:selectOneMenu id="lstOpciones" value="#{PermisoBean.opcionSeleccionada}" styleClass="lista" immediate="true">
		        			<f:selectItems value="#{PermisoBean.objPor}" id="selectOpcion"/>	        			
		        		</h:selectOneMenu>
		        		
		        	</h:panelGroup>
		        	
		        	<x:commandButton id="btnBuscarRol" value="Buscar" action="#{PermisoBean.cargarMenu}" styleClass="btn btn-primary btn-flat pull-right" title="Busca el rol seleccionado"
	        			rendered="#{!PermisoBean.verRol}"/>        
	        									 		
	        	</h:panelGrid>
              	
              	<h:panelGrid id="grid2" columns="3" width="350" rendered="#{PermisoBean.mostrarMenu}" align="center">
              	
					<h:outputText value="Menu:" styleClass="texto"/>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
						
					<f:verbatim>&nbsp;</f:verbatim>
					<x:jscookMenu layout="hbr" theme="ThemeOffice2003" imageLocation="/css/ThemeOffice2003" immediate="true">
						<x:navigationMenuItems id="list_menu" value="#{PermisoBean.menuSegunRol}"/>
					</x:jscookMenu>
					<f:verbatim>&nbsp;</f:verbatim>					
					
				</h:panelGrid>
<%--sortable="false" preserveDataModel="false"--%>
				<c:if test="${!empty PermisoBean.menuItemSeleccionables}">
					
					<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 910; HEIGHT: 150px; border: 1px; margin-left: auto; margin-right: auto;" id="div">
					
                    <h:dataTable id="listado_1" align="center" styleClass="standardTable" headerClass="dataTable_Header" footerClass="standardTable_Header"
                    	rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                        var="obj_1" value="#{PermisoBean.menuItemSeleccionables}" style=" width : 578px; height : 52px;">

                        <x:column defaultSorted="true">
                            <f:facet name="header">
                                <h:outputText value="Id_O" styleClass="texto"  id="outId_o"/>
                            </f:facet>
                            <h:outputText value="#{obj_1.menuItem.idItem}" styleClass="texto" id="outIdItem"/>
                        </x:column>
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Label" styleClass="texto"  id="outLabe"/>
                            </f:facet>
                            <h:outputText value="#{obj_1.menuItem.label}" styleClass="texto" id="outMenuLabel"/>
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Icono" styleClass="texto"  id="icono"/>
                            </f:facet>
                            <h:outputText value="#{obj_1.menuItem.icon}" styleClass="texto" id="outIcono"/>
                        </x:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <%--h:commandLink action="#{PermisoBean.marcarTodos}">
                                	<h:outputText value="Todos" styleClass="texto" />
                                </h:commandLink--%>
                                <h:selectBooleanCheckbox  value="#{PermisoBean.selColumnas}" onclick="marcar(this);"/>
                            </f:facet>                            
                            <h:selectBooleanCheckbox id="sel"  value="#{obj_1.seleccionado}"  />
                        </h:column>        
                        
                        <h:column>
                             <x:commandLink id="comandoLin" value="Ver Página" action="#{PermisoBean.cargarMenuSoloPagina}" >
								<f:param id="idPaginaHidden" name="idPaginaHidden" value="#{obj_1.menuItem.pagina.idPagina}" />
							</x:commandLink>
                        </h:column>                  
                	</h:dataTable>
                	</x:div>
                	<%-- Link oculto para eliminar o editar --%>
							<x:commandLink action="#{PermisoBean.cargarMenuSoloPagina}" id="editarLaPaginaLink" style="display: none;"/>
                </c:if>                	
                	
				<c:if test="${!empty PermisoBean.paginasSeleccionables}">
				<h:panelGrid columns="2" align="center" id="gridTercero" rendered="#{PermisoBean.origen}">
				    <h:panelGroup id="p">		
						<h:outputText value="Pagina:" styleClass="texto"/>
						<x:inputText id="nombrePagina" value="#{PermisoBean.paginaBuscada}" 
									 size="20" maxlength="30" styleClass="bordeceldainferior" 
									 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
					</h:panelGroup>				 
		        	<x:commandButton id="buscarPagina" value="Buscar" action="#{PermisoBean.buscarPagina}" 
	        						 styleClass="btn btn-primary btn-flat pull-right" title="Buscar Pagina."/>					
				</h:panelGrid>				
				
				<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 910; HEIGHT: 150px; border: 1px; margin-left: auto; margin-right: auto;">
                    <x:dataTable id="listado_2" align="center"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"
                            sortable="false"
                            var="obj_2" 
                            value="#{PermisoBean.paginasSeleccionables}"
                            preserveDataModel="false"
                            first="1">

                        <x:column defaultSorted="true">
                            <f:facet name="header">
                                <h:outputText value="Id_lista2" styleClass="texto" />
                            </f:facet>
                            <h:outputText value="#{obj_2.pagina.idPagina}" styleClass="texto"/>
                        </x:column>
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Página" styleClass="texto" />
                            </f:facet>
                            <h:outputText value="#{obj_2.pagina.pagina}" styleClass="texto"/>
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:commandLink action="#{PermisoBean.marcarL}">
                                	<h:outputText value="Acceso" styleClass="texto" />
                                </h:commandLink>
                            </f:facet>
                            <h:panelGrid columns="1" align="center" id="griCuarto">
                            	<x:selectBooleanCheckbox value="#{obj_2.acceso}" id="L" forceId="true"/>
                            </h:panelGrid>
                        </x:column>                         
                        
                        <x:column>
                            <f:facet name="header">
                                <h:commandLink action="#{PermisoBean.marcarA}">
                                	<h:outputText value="Alta" styleClass="texto" />
                                </h:commandLink>
                            </f:facet>
                            <h:panelGrid columns="1" align="center" id="griQuinto">
                            	<x:selectBooleanCheckbox value="#{obj_2.alta}" id="A" forceId="true"/>
                            </h:panelGrid>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:commandLink action="#{PermisoBean.marcarB}">
                                	<h:outputText value="Baja" styleClass="texto" />
                                </h:commandLink>
                            </f:facet>
                            <h:panelGrid columns="1" align="center" id="griSexto">
                            	<x:selectBooleanCheckbox value="#{obj_2.baja}" id="B" forceId="true"/>
                            </h:panelGrid>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:commandLink action="#{PermisoBean.marcarM}">
                                	<h:outputText value="Modificar" styleClass="texto" />
                                </h:commandLink>
                            </f:facet>
                            <h:panelGrid columns="1" align="center" id="griSeptimo">
                            	<x:selectBooleanCheckbox value="#{obj_2.modificacion}" id="M" forceId="true"/>
                            </h:panelGrid>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:commandLink action="#{PermisoBean.marcarE}">
                                	<h:outputText value="Exportar" styleClass="texto" />
                                </h:commandLink>
                            </f:facet>
                            <h:panelGrid columns="1" align="center" id="panelOctavo">
                            	<x:selectBooleanCheckbox value="#{obj_2.exportacion}" id="E" forceId="true"/>
                            </h:panelGrid>
                        </x:column>
                	</x:dataTable>
                </x:div>            
                </c:if>
                	
				<c:if test="${!empty PermisoBean.menuItemSeleccionables || !empty PermisoBean.paginasSeleccionables}">
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="637" id="panelFinal">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<h:commandButton id="buttonGrabar" value="Grabar" action="#{PermisoBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
	                	<h:commandButton id="buttonCancelar" value="Cancelar" action="#{PermisoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right"/>
	                	
					</h:panelGrid>							                	
				</c:if>
				
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{PermisoBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
