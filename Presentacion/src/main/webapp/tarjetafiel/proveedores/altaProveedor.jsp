
<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{ProveedorBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaProveedoresForm').submit();
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaProveedoresForm');" onload="if('${ProveedorBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}" >


<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ProveedorBean.tituloCorto}
    <small>${ProveedorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>

<secure:check/>

<h:form id="altaProveedoresForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ProveedorBean.popup.mostrar}">
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

            	<h:panelGrid columns="1" align="center" id="PanelPricipalProveedores" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog" id="viewDialog"
							   dialogVar="viewDialog" 
							   styleClass="viewDialog"
			                   dialogTitle="#{ProveedorBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{ProveedorBean.popup.nombreIcono}" />
					        <h:outputText value=" #{ProveedorBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{ProveedorBean.irANuevoProveedor}" 
				        		 onclick="clickLink('nuevoProv');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="botones" title="Agregar nuevo proveedor."/>

				        	<x:commandButton action="#{ProveedorBean.irAModificarProveedor}" 
				        		 onclick="clickLink('modificarProv');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="botones" title="Seguir modificando el proveedor."
				         		 rendered="#{ProvedorBean.verModificar}"/>
							
							<x:commandButton action="#{ProveedorBean.irAListarProveedor}" 
								 onclick="clickLink('listarProv');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="botones" title="Ir al listado de proveedores."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoProv" action="#{ProveedorBean.irANuevoProveedor}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarProv" action="#{ProveedorBean.irAModificarProveedor}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarProv" action="#{ProveedorBean.irAListarProveedor}" forceId="true" style="display: block;"/>
				
				
				<c:if test="${ProveedorBean.validado!=true}">
				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<s:layoutingTitlePane id="altaProveedores" label=" Alta Proveedores" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >												
             		<h:panelGrid id="panelPrincipalUno" columns="3" width="350" align="center">
		                <h:outputText id="outCuit" value="CUIT del Proveedor: " styleClass="texto"/>
						<h:panelGroup id="panelCuit">
							<h:inputText id="inCuit" value="#{ProveedorBean.cuit}" 
										 size="11" maxlength="11" styleClass="bordeceldainferior" 
										 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
										 onkeypress="return soloEnteros(this,event);"/>
						</h:panelGroup>
						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'alta')}">
									<h:commandButton value="Continuar" actionListener="#{ProveedorBean.validarCuit}"
											 styleClass="botones" />
								</c:when>
								<c:otherwise>
									<h:commandButton value="Continuar" onclick="alert('No posee los permisos necesarios.');"
											 styleClass="botones" />
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>						
					</h:panelGrid>
					<h:panelGroup id="errorCuitInvalido" style="width: 300px">
						<h:outputText id="cuitInvalido" value="#{ProveedorBean.cuitInvalido}" style="font-size: 10px;color: red"/>
					</h:panelGroup>
				</s:layoutingTitlePane>
			    </h:panelGrid>

				</c:if>
					<c:if test="${ProveedorBean.validado==true}">
					<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
					<f:verbatim>&nbsp;</f:verbatim>	
             		<h:panelGrid columns="2" id="panelDatos" align="center" width="600">
	             		<h:panelGrid id="panelPrincipalUnoDos" columns="3" width="350" align="center">
			                <h:outputText id="outCodigo" value="Código: " styleClass="texto"/>
			                <h:outputText id="outCodigoValor" value="#{ProveedorBean.idProveedor}" styleClass="texto"/>
							<f:verbatim>&nbsp;</f:verbatim>
												
			                <h:outputText id="outCuitDos" value="Cuit: " styleClass="texto"/>
							<h:panelGroup id="panelCuitDos">
								<h:outputText id="IdentDos" value="#{ProveedorBean.cuitIdentificador}" styleClass="texto"/>
			                	<h:outputText id="separador_1Dos" value="-" styleClass="texto"/>
								<h:outputText id="DniDos" value="#{ProveedorBean.cuitDni}" styleClass="texto" />
								<h:outputText id="separador_2Dos" value="-" styleClass="texto"/>
								<h:outputText id="VerifDos" value="#{ProveedorBean.cuitVerificador}" styleClass="texto" />
							</h:panelGroup>
							<f:verbatim>&nbsp;</f:verbatim>					
							
			                <h:outputText id="outRazonSocial" value="Razon Social: " styleClass="texto"/>
	    		            <h:inputText id="RazonSocial" value="#{ProveedorBean.razonSocial}" 
		                			 size="100" maxlength="100" styleClass="bordeceldatext" 
		                			 style=" width : 150px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>
													
			                <h:outputText id="outNombreFantasia" value="Nombre Fantasia: " styleClass="texto"/>
	    		            <h:inputText id="NombreFantasia" value="#{ProveedorBean.nombreFantasia}" 
		                			 size="100" maxlength="100" styleClass="bordeceldatext" 
		                			 style=" width : 150px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>
							
			                <h:outputText id="outAlias" value="Alias: " styleClass="texto"/>
	    		            <h:inputText id="Alias" value="#{ProveedorBean.alias}" 
		                			 size="50" maxlength="50" styleClass="bordeceldatext" 
		                			 style=" width : 150px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>						
						</h:panelGrid>

	             		<h:panelGrid id="panelPrincipalUnoTres" columns="3" width="350">
			                <h:outputText id="outSucursal" value="Sucursal: " styleClass="texto"/>
							<h:selectOneMenu id="lstSucursales" value="#{ProveedorBean.sucursalSeleccionda}" 
		        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
		        					         onblur="apagar(this);">
		       					<f:selectItems value="#{ProveedorBean.listaDeSucursales}"/>
		       				</h:selectOneMenu>
							<f:verbatim>&nbsp;</f:verbatim>
	
			                <h:outputText id="outGrupo" value="Grupo: " styleClass="texto"/>
							<h:selectOneMenu id="lstGrupos" value="#{ProveedorBean.grupoSeleccionado}" 
		       					 	styleClass="lista" immediate="true" onfocus="encender(this);"
		       					 	onblur="apagar(this);">
		        				<f:selectItems value="#{ProveedorBean.listaDeGrupos}"/>
		       				</h:selectOneMenu>			                
							<f:verbatim>&nbsp;</f:verbatim>							
							
			                <h:outputText id="outLimiteCredito" value="Limite Credito: " styleClass="texto"/>
		   		            <h:inputText id="LimiteCredito" value="#{ProveedorBean.limiteCredito}" 
		               			 size="13" maxlength="13" styleClass="bordeceldainferior" style="width: 80px"
		               			 onfocus="encender(this);" onblur="apagar(this);" 
		               			 onkeypress="return soloDecimales(this,event);"/>
							<f:verbatim>&nbsp;</f:verbatim>
							
			                <h:outputText id="outMoneda" value="Moneda: " styleClass="texto"/>
			                <h:outputText id="outPesos" value="Pesos" styleClass="texto" style="font: bold;"/>			                
							<f:verbatim>&nbsp;</f:verbatim>			                
							</h:panelGrid>	
             		</h:panelGrid>
             		<f:verbatim>&nbsp;</f:verbatim>	
             		</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>	
             		

             	<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR EMAILS --%>							             		
				<s:layoutingTitlePane id="gestionarEmails" label=" Gestionar Emails" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >             		
					<h:panelGrid columns="2" width="900" id="panelGestionarEmails">
						<h:dataTable value="#{ProveedorBean.tablaDeEmails}" var="proveedorEmail" id="tablaEmail"
									 styleClass="standardTable"
		                             headerClass="dataTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 style=" width : 860px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Email" styleClass="texto" />
	                            </f:facet>
	                            <h:inputText value="#{proveedorEmail.email.email}" style=" width : 213px;" size="50" maxlength="50" 
	                            			 styleClass="bordeceldatext" onfocus="encender(this);" 
	                            			 onblur="apagar(this);"/>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Descripción" styleClass="texto" />
	                            </f:facet>
	                            <h:inputText value="#{proveedorEmail.email.descripcion}" style=" width : 271px;" size="255" 
	                             			 maxlength="255" styleClass="bordeceldatext" 
	                             			 onfocus="encender(this);" onblur="apagar(this);"/>
	                        </h:column>
	                        
	                        <h:column>
								<x:commandLink action="#{ProveedorBean.eliminarEmail}" id="eliminarEmailLink">
									<f:param id="idEmail" name="idEmail" value="#{proveedorEmail.email.idEmail}"/>							
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar email." border="0"/>								
								</x:commandLink>
							</h:column>
						</h:dataTable>
						<h:panelGroup>
							<x:commandLink action="#{ProveedorBean.agregarEmail}" id="agregarEmailLink">
								<x:graphicImage value="/img/cat_pad.gif" title="Agregar email." border="0"/>								
							</x:commandLink>						
						</h:panelGroup>
					</h:panelGrid>	
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>	


				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR DOMICILIOS --%>
				<s:layoutingTitlePane id="gestionarDomicilios" label=" Gestionar Domicilios" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >             								
						<h:panelGrid columns="2" id="panelDomicilio" width="900">
						<h:dataTable value="#{ProveedorBean.tablaDeDomicilios}" id="tablaDomicilio"
									 styleClass="standardTable"
                                     headerClass="dataTable_Header"
                                     footerClass="standardTable_Header" 
                                     rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
						             var="proveedorDomicilio" style=" width : 860px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Tipo Dirección" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedorDomicilio.domicilio.tipoDomicilio.descripcion}" style=" width : 192px;"/>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Calle" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedorDomicilio.domicilio.calleNombre}" style=" width : 192px;"/>
	                        </h:column>								
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Número" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedorDomicilio.domicilio.calleNumero}" style=" width : 40px;"/>
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Barrio" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedorDomicilio.domicilio.barrio.descripcion}" style=" width : 40px;"/>
	                        </h:column>	                        

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Localidad" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedorDomicilio.domicilio.localidad.nombre}" style=" width : 40px;"/>
	                        </h:column>

	                        <h:column>
								<x:commandLink action="#{ProveedorBean.editarDomicilio}" id="editarDomicilioLink">
									<f:param id="idDomiEdi" name="idDomiEdi" value="#{proveedorDomicilio.domicilio.idDomicilio}"/>
									<x:graphicImage value="/img/editar.gif" title="Editar el domicilio." border="0"/>								
								</x:commandLink>
							</h:column>
	                        
	                        <h:column>
								<x:commandLink action="#{ProveedorBean.eliminarDomicilio}" id="eliminarDomicilioLink">
									<f:param id="idDomicilio" name="idDomicilio" value="#{proveedorDomicilio.domicilio.idDomicilio}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar un domicilio." border="0"/>								
								</x:commandLink>
							</h:column>	                        	                        
						</h:dataTable>
							<x:commandLink id="agregarDomicilioLink" action="#{ProveedorBean.agregarDomicilioPopup}" >
								<x:graphicImage value="/img/icon/home_24.gif" title="Agregar domicilios." border="0"/>								
							</x:commandLink>						
					</h:panelGrid>
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>


				<f:verbatim>
					<br>
				</f:verbatim>	

				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR TELEFONOS --%>
				<s:layoutingTitlePane id="gestionarTelefonos" label=" Gestionar Teléfonos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >
					<h:panelGrid columns="2" id="panelTelefono" width="900" align="center">
							<h:panelGroup>
								<h:dataTable value="#{ProveedorBean.listaTelefonos}"
									id="tablaTelefono" styleClass="standardTable" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
									var="proveedorTelefono" style=" width : 860px;">
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Número" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.codArea}"/>
										<h:outputText value=" - "/>
										<h:outputText value="#{proveedorTelefono.telefono.nroTlefono}"/>
										<h:outputText value=" int. "/>
										<h:outputText value="#{proveedorTelefono.telefono.nroInterno}"/>
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Celular" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.esCelular}"/>
									</h:column>
									
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fax" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.esFax}"/>
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Tipo Telefono" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.tipo.descripcion}" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Horarios" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.horarios}" />
									</h:column>
										
									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{proveedorTelefono.telefono.descripcion}" />
									</h:column>
										
									<h:column>
										<x:commandLink action="#{ProveedorBean.editarTelefono}" id="editarTelefonoLink">
											<f:param id="idTelEdi" name="idTelEdi" value="#{proveedorTelefono.telefono.idTelefono}" />
											<x:graphicImage value="/img/editar.gif" title="Editar el Teléfono." border="0" />
										</x:commandLink>
									</h:column>

									<h:column>
										<x:commandLink action="#{ProveedorBean.eliminarTelefono}" id="eliminarTelefonoLink">
											<f:param id="idTelefono" name="idTelefono" value="#{proveedorTelefono.telefono.idTelefono}"/>
											<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Teléfono." border="0" />
										</x:commandLink>
									</h:column>
										
								</h:dataTable>
							</h:panelGroup>
							
							<h:panelGroup>
								<x:commandLink id="agregarTelefonoLink" action="#{ProveedorBean.agregarTelefono}">
										<x:graphicImage value="/img/cat_pad.gif" title="Agregar Teléfono." border="0" />
							  	</x:commandLink>
							</h:panelGroup>

					</h:panelGrid>
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>

						
				<f:verbatim>
					<br>
				</f:verbatim>	


				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR RELACION CON PROVEEDORES Y CLIENTES --%>							
				<s:layoutingTitlePane id="gestionarRelacionCPC" label=" Gestionar Relaciones" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >
					<h:panelGrid columns="2" id="panelRelacionProveedores" width="900">
		                <h:outputText id="outRelacionProveedor" value="Proveedores Relacionados: " styleClass="texto"/>
						<f:verbatim>&nbsp;</f:verbatim>
													
						<x:dataTable value="#{ProveedorBean.tablaDeRelacionConProveedores}" id="tablaRelacionProveedores"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header" 
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
						             var="proveedor" style=" width : 860px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cuit" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedor.cuit}" />
	                        </h:column>								

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Razon Social" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{proveedor.razonSocial}" />
	                        </h:column>
	                        
	                        <h:column>
								<x:commandLink action="#{ProveedorBean.eliminarRelacionConProveedor}" id="eliminarRCPLink">
									<f:param id="idProveedor" name="idProveedor" value="#{proveedor.idProveedor}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar una relación." border="0"/>								
								</x:commandLink>
							</h:column>	                        	                        			                        
						</x:dataTable>
						<x:commandLink id="agregarRelacionProveedorLink" action="#{ProveedorBean.agregarRelProvPopup}">
							<x:graphicImage value="/img/icon/move_24.gif" 
											title="Agrega relaciones cnn proveedores." border="0"/>
						</x:commandLink>
					</h:panelGrid>	
					
					<h:panelGrid columns="2" id="panelRelacionClientes" width="900">
		                <h:outputText id="outRelacionCliente" value="Clientes Relacionados: " styleClass="texto"/>
						<f:verbatim>&nbsp;</f:verbatim>
													
						<h:dataTable value="#{ProveedorBean.tablaDeRelacionConClientes}" id="tablaRelacionClientes"
									 styleClass="standardTable"
                           			 headerClass="standardTable_Header"
                           			 footerClass="standardTable_Header"
                           			 rowClasses="standardTable_Row1,standardTable_Row2"
                           			 columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 var="cliente" style=" width : 860px;">

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cuil" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{cliente.cuil}" />
	                        </h:column>								

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Nombre 1" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{cliente.nombre1}" />
	                        </h:column>										                        
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Nombre 2" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{cliente.nombre2}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Apellido" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{cliente.apellido}" />
	                        </h:column>
	                        
	                        <h:column>
								<x:commandLink action="#{ProveedorBean.eliminarRelacionConCliente}" id="eliminarRCCLink">
									<f:param id="idCliente" name="idCliente" value="#{cliente.idCliente}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar un cliente." border="0"/>
								</x:commandLink>
							</h:column>
						</h:dataTable>
							<x:commandLink id="agregarRelacionClienteLink" action="#{ProveedorBean.agregarRelClientePopup}">
								<x:graphicImage value="/img/icon/businessman_find 24.png" 
												title="Agrega relaciones con clientes." border="0"/>
							</x:commandLink>
					</h:panelGrid>
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>	


				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR IMPUESTOS --%>							
				<s:layoutingTitlePane id="gestionarImpuestos" label=" Gestionar Impuestos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >
					<h:panelGrid columns="2" id="panelImpuestos" width="900">
						<h:dataTable value="#{ProveedorBean.tablaDeImpuestos}" id="tablaImpuestosNacionales"
									 styleClass="standardTable"
		                             headerClass="standardTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 var="imp" style=" width : 800px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Tipo" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{imp.tipoImpuesto.descripcion}" />
	                        </h:column>

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Categoria" styleClass="texto" />
	                            </f:facet>
									<h:selectOneMenu id="lstCategorias" value="#{imp.categoriaSeleccionada}" 
				       					             styleClass="lista" onfocus="encender(this);" valueChangeListener="#{imp.cambio}"
				       					             onblur="apagar(this);" immediate="true" onchange="submit();">
				       					<f:selectItems value="#{imp.categorias}" id="selectCategorias"/>
				       				</h:selectOneMenu>		
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Jurisdicción" styleClass="texto" />
	                            </f:facet>
	                            <h:selectOneMenu id="lstJurisActi" value="#{imp.jurisSeleccionada}" 
			       					             styleClass="lista" onfocus="encender(this);" immediate="true" onchange="submit();"
			       					             onblur="apagar(this);" style=" width : 180px;" valueChangeListener="#{imp.cambio}">
			       					<f:selectItems value="#{imp.jurisdicciones}" id="selectjuris"/>
			       				</h:selectOneMenu>	
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Actividad" styleClass="texto" />
	                            </f:facet>
	                            <x:commandButton id="btnBuscarActividades" value="" action="#{imp.buscarActividades}" 
												title="Buscar Actividades" image="/img/icon/reload_page.png"/>
								<f:verbatim>&nbsp;</f:verbatim>
	                            <h:selectOneMenu id="lstActi" value="#{imp.jurisActividadSeleccionada}" 
			       					             styleClass="lista" onfocus="encender(this);" disabled="#{imp.boolActi}"
			       					             onblur="apagar(this);" style=" width : 180px;">
			       					<f:selectItems value="#{imp.jurisActividades}" id="selectActivi"/>
			       				</h:selectOneMenu>	
	                        </h:column>				                        		                        
						</h:dataTable>
						<f:verbatim>&nbsp;</f:verbatim>
					</h:panelGrid>
					
					<h:panelGrid columns="3" width="900">
						<h:outputText id="outJurisdiccion" value="Jurisdicción: " styleClass="texto"/>
							<h:selectOneMenu id="lstJurisdiccion" value="#{ProveedorBean.jurisdiccionSeleccionada}" 
		       					 	styleClass="lista" immediate="true" onfocus="encender(this);" onblur="apagar(this);">
		        				<f:selectItems value="#{ProveedorBean.listaDeJurisdicciones}"/>
		       				</h:selectOneMenu>			                
						<h:outputText value="La Jurisdicción debe coincidir con la de Ingresos Brutos" styleClass="texto" style="FONT-STYLE: italic; font: italic;"/>
						<h:outputText value="Nro Inscripción DGR" styleClass="texto"/>
						<h:inputText value="#{ProveedorBean.inscripcionDgr}" 
									 styleClass="bordeceldainferior" style=" width : 110px;" 
									 onfocus="encender(this);" onblur="apagar(this);" 
									 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="(Ej: 9011711275)" styleClass="texto" style="FONT-STYLE: italic; font: italic;"/>
					</h:panelGrid>
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>		


				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR FORMAS DE PAGO --%>							
				<s:layoutingTitlePane id="gestionarFP" label=" Gestionar Formas de pago" widgetId="gestionarFP"
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >
				   <h:panelGrid columns="1"  id="panelGestionarFP">
					<h:panelGrid columns="2" id="panelFormaPago" width="900">
	                <h:outputText id="outFormaPago" value="Forma de Pago: " styleClass="texto"/>
					<f:verbatim>&nbsp;</f:verbatim>
												
						<h:dataTable value="#{ProveedorBean.tablaDeFormaDePago}" id="tablaFormaPago"
									 styleClass="standardTable"
		                             headerClass="standardTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 var="forma" style=" width : 860px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Nro Cuenta Fondos" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.nroCuentaFondos}" />
	                        </h:column>								

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Forma Pago" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.formaPago.descripcion}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Orden Cheque" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.ordenCheque}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Banco" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.banco.descripcion}" />
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Cod. Cuenta Dep." styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.codCtaDeposito}" />
	                        </h:column>

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="CBU" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{forma.formaPago.cbu}" />
	                        </h:column>
	                        
	                        <h:column>
								<x:commandLink action="#{forma.eliminar}" id="eliminarFPLink">
									<f:param id="idProvFormaPago" name="idProvFormaPago" value="#{forma.indice}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar la forma de pago." border="0"/>
								</x:commandLink>
							</h:column>
						</h:dataTable>

						<h:panelGroup>
						<x:commandLink id="agregarFormaLink" action="#{ProveedorBean.irAgregarFormaDePago}">
							<x:graphicImage value="/img/cat_pad.gif" title="Cargar la forma de pago." border="0"/>
						</x:commandLink>
						</h:panelGroup>
					</h:panelGrid>

					<h:panelGrid columns="1" id="panelGestionarDiaE">
	                <h:outputText id="outDiaEmision" value="Días de emisión de pago: " styleClass="texto"/>
					<h:panelGrid id="panelPrincipalTres" columns="1">
						<h:panelGroup id="panelChkFechaFactura">
							<x:selectBooleanCheckbox id="chkFechaFactura" value="#{ProveedorBean.fechaFactura}" forceId="true" onclick="validarCheckDias(this);">
							</x:selectBooleanCheckbox>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>								
			                <h:panelGroup id="panelDiaCalendario">
				                <h:outputText id="outDiaCalendario" value="Día de semana: " styleClass="texto"/>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>					                
								<h:selectOneMenu id="lstImpDiaCalendario" value="#{ProveedorBean.diaDeLaSemana}" 
		        					 styleClass="lista" immediate="true" style=" width : 40px;"
		        					 onfocus="encender(this);" onblur="apagar(this);">
		        					<f:selectItems value="#{ProveedorBean.listaDiasDeLaSemana}" id="selectDiasSemana"/>
		        				</h:selectOneMenu>
								<f:verbatim>&nbsp;</f:verbatim>		        				
								<h:selectOneMenu id="lstNombreDia" value="#{ProveedorBean.nombreDia}" 
		    	    					 styleClass="lista" immediate="true" style=" width : 100px;"
		    	    					 onfocus="encender(this);" onblur="apagar(this);">
									<f:selectItems value="#{ProveedorBean.listaDeDiasSemanaNombre}" id="selectDiasSemanaNombre"/>
		        				</h:selectOneMenu>
			        		</h:panelGroup>
						</h:panelGroup>

						<h:panelGroup id="panelChkDiaCC">
							<x:selectBooleanCheckbox id="chkDiaCC" value="#{ProveedorBean.diaCC}" forceId="true" onclick="validarCheckDias(this);">
							</x:selectBooleanCheckbox>
							<f:verbatim>&nbsp;</f:verbatim>
							<f:verbatim>&nbsp;</f:verbatim>								
				            <h:panelGroup id="panelDiaCC">		        				        				
								<h:outputText id="outDiaCC" value="Dia calendario:  " styleClass="texto"/>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>									
								<h:selectOneMenu id="lstDiasCC" value="#{ProveedorBean.diaDelMes}" 
			        					 styleClass="lista" immediate="true" style=" width : 40px;"
			        					 onfocus="encender(this);" onblur="apagar(this);">
									<f:selectItems value="#{ProveedorBean.listaDeDiasMes}" id="selectDiasMes"/>
			        			</h:selectOneMenu>		
		        			</h:panelGroup>
	        			</h:panelGroup>
	        			</h:panelGrid>
		            </h:panelGrid>
 				   </h:panelGrid>									      
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>
				
				<f:verbatim>
					<br>
				</f:verbatim>

				<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1">
				<%-- GESTIONAR CP Y DIAS DE EMISION --%>
				<s:layoutingTitlePane id="gestionarCPDE" label=" Gestionar condiciones de compra" 
								      containerNodeClass="contentTitlePane" labelNodeClass="box-header text-light-blue" >									
					<h:panelGrid columns="2" id="panelComposicion" width="320">
		                <h:outputText id="outCondicion" value="Composición de Pago: " styleClass="texto"/>
						<f:verbatim>&nbsp;</f:verbatim>
						
						<h:panelGrid columns="3" align="center" id="panelSelectCC">
							<h:panelGroup>
								<h:outputText value="Cuenta corriente" styleClass="texto" />
								<x:selectBooleanCheckbox id="chkCuentaC" value="#{ProveedorBean.cuentaC}" forceId="true" 
										onclick="validarCheckFF(this);" onchange="recargar();"/>
							</h:panelGroup>
							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							
							<h:panelGroup>
								<h:outputText value="Fecha de factura" styleClass="texto"/>
								<x:selectBooleanCheckbox id="chkFechaF" value="#{ProveedorBean.fechaF}" forceId="true" 
										onclick="validarCheckFF(this);" onchange="recargar();"/>
							</h:panelGroup>							
						</h:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>												
													
						<h:dataTable value="#{ProveedorBean.tablaDeComposicionDePago}" id="tablaComposicion"
									 styleClass="standardTable"
		                             headerClass="standardTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
									 var="composicion" style=" width : 300px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Días" styleClass="texto" />
	                            </f:facet>
	                            <h:inputText value="#{composicion.dias}" style=" width : 45px;" 
	                            			 styleClass="bordeceldainferior" onfocus="encender(this);" 
	                            			 onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
	                            			 disabled="#{ProveedorBean.fechaF}"/>
	                        </h:column>								

	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Porcentaje" styleClass="texto" />
	                            </f:facet>
	                            <h:inputText value="#{composicion.porcentajeMonto}" style=" width : 45px;" 
	                            			 styleClass="bordeceldainferior" onfocus="encender(this);" 
	                            			 onblur="apagar(this);" onkeypress="return soloEnteros(this,event);"
	                            			 disabled="#{ProveedorBean.fechaF}"/>
	                        </h:column>										                        
	                        
	                        <h:column>
								<x:commandLink action="#{ProveedorBean.eliminarComposicionDePago}" id="eliminarComposicionLink" 
									disabled="#{ProveedorBean.fechaF}">
									<f:param id="idCompPago" name="idCompPago" value="#{composicion.idTipoVencimiento}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar la composición de pago." border="0"/>								
								</x:commandLink>
							</h:column>	                        	                        
						</h:dataTable>
						<h:panelGroup>
							<x:commandLink action="#{ProveedorBean.agregarComposicionDePago}" id="agregarComposicionLink"
								disabled="#{ProveedorBean.fechaF}">
								<x:graphicImage value="/img/cat_pad.gif" 
												title="Agrega composición de pago." border="0"/>
							</x:commandLink>
						</h:panelGroup>
					</h:panelGrid>	
				</s:layoutingTitlePane>
				<f:verbatim>&nbsp;</f:verbatim>	
				</h:panelGrid>	
				

				<f:verbatim>
					<br>
				</f:verbatim>				
				
					<f:verbatim><hr align="center" width="900"></f:verbatim>
					<h:panelGrid columns="10" width="900" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>				
						<h:panelGroup>
							<c:choose>
								<c:when test="${lst:contains(requestScope.permisos,'alta')}">
									<h:commandButton id="buttonGrabar" value="Guardar" actionListener="#{ProveedorBean.grabarProveedorListener}" onclick="" styleClass="btn btn-primary btn-flat" />											 
								</c:when>
								<c:when test="${lst:contains(requestScope.permisos,'modificar')}">
									<h:commandButton id="buttonGrabar" value="Guardar" actionListener="#{ProveedorBean.grabarProveedorListener}" onclick="" styleClass="btn btn-primary btn-flat" />											 
								</c:when>								
								<c:otherwise>
									<h:commandButton value="Guardar" onclick="alert('No posee los permisos necesarios.');"
											 styleClass="btn btn-primary btn-flat" />
								</c:otherwise>
							</c:choose>					
						</h:panelGroup>		                	
						<x:commandButton id="buttonCancelar" value="Cancelar" 
										action="#{ProveedorBean.cancelarProveedor}" styleClass="btn btn-primary btn-flat"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ProveedorBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
