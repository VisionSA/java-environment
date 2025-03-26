<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>
<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{EmpresaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amEmpresaForm').submit();
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
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amEmpresaForm');" onload="if('${EmpresaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${EmpresaBean.tituloCorto}
    <small>${EmpresaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>
    <secure:check/>
	<h:form id="amEmpresaForm">
	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup id="estadoScroll" rendered="#{!EmpresaBean.popup.mostrar}">
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
																
								<%-- INCLUDE PARA LOS ERRORES --%>
								<h:panelGroup id="errores">
									<jsp:include page="/inc/error.jsp" />
								</h:panelGroup>
								
								
								<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
								<s:modalDialog dialogId="viewDialog"
											dialogVar="viewDialog"
											styleClass="viewDialog"
											dialogTitle="#{EmpresaBean.tituloCorto}">
									<h:panelGrid columns="2" width="500">
										<x:graphicImage value="/img/#{EmpresaBean.popup.nombreIcono}" />
										<h:outputText value="#{EmpresaBean.popup.mensaje}" styleClass="texto"/>
									</h:panelGrid>
									<h:panelGrid columns="3" width="500">
										<x:commandButton action="#{EmpresaBean.irANuevaEmpresa}" 
											onclick="clickLink('nuevaEmpresa');dojo.widget.byId('viewDialog').hide();"
											value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nueva."/>
										<x:commandButton action="#{EmpresaBean.irAModificarEmpresa}" 
											onclick="clickLink('modificarEmpresa');dojo.widget.byId('viewDialog').hide();"
											value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando."/>
										<x:commandButton action="#{EmpresaBean.irAListarEmpresas}" 
											onclick="clickLink('listarEmpresas');dojo.widget.byId('viewDialog').hide();"
											id="cmdListar" value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado."/>
									</h:panelGrid>
								</s:modalDialog>
								
								
								<x:commandLink id="nuevaEmpresa" action="#{EmpresaBean.irANuevaEmpresa}" forceId="true" style="display: block;"/>
								<x:commandLink id="modificarEmpresa" action="#{EmpresaBean.irAModificarEmpresa}" forceId="true" style="display: block;"/>
								<x:commandLink id="listarEmpresas" action="#{EmpresaBean.irAListarEmpresas}" forceId="true" style="display: block;"/>
							    <h:panelGrid columns="6" id="panelEmpresa" align="center">
								    <h:outputText value="Cuit:" id="outputCuit"></h:outputText>
								    <h:inputText value="#{EmpresaBean.empresa.cuit}" id="inputCuit" size="11" maxlength="11" />
								    <h:outputText value="Razon Social:" id="outputRazonSocial"></h:outputText>
								    <h:inputText value="#{EmpresaBean.empresa.razonSocial}" id="inputRazonSocial"/>
								    <h:outputText value="¿Es Riesgosa?" id="outputRiesgo"/>
								    <h:selectBooleanCheckbox value="#{EmpresaBean.esRiesgosa}" id="selecRiesgo"> </h:selectBooleanCheckbox>
								</h:panelGrid>
								<h:panelGrid columns="1" width="700" id="prinEmpre" align="center">
			                   
								
								<s:fieldset legend="Rubro de la Empresa: " id="fieldRubroTamanio" rendered="false" >
								    <h:panelGrid columns="5">
								       	<h:outputText id="outputIndicacion2" value="Ingrese el tamaño de la empresa: " rendered="false"/>
								        <h:selectOneMenu id="listTamEmpresa" value="#{EmpresaBean.idTamanio}" styleClass="lista" rendered="false" 
										immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
										<f:selectItems value="#{EmpresaBean.listaTamanios}" />
										</h:selectOneMenu>
								    </h:panelGrid>        				
								</s:fieldset>
								
								
								
								
								
								<s:fieldset rendered="#{EmpresaBean.muestroLista}" id="tablaSucursales">
										<h:panelGrid columns="2" id="panelSucursales" align="center">
											<h:dataTable value="#{EmpresaBean.sucursales}"
												id="tablaDeSucursales" styleClass="standardTable" headerClass="dataTable_Header"
												footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
												columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
												var="sucur" style=" width : 600px;">
												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Descripción" styleClass="texto" />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.descripcion}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Calle" styleClass="texto" />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.domicilio.calleNombre}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Número" styleClass="texto"  />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.domicilio.calleNumero}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Localidad" styleClass="texto"  />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.domicilio.localidad.nombre}" />
												</h:column>
		
												<h:column>
													<x:commandLink action="#{EmpresaBean.editarSucursal}" id="editSucursal">
														<f:param id="idSucAEdit" name="idSucAEdit" value="#{sucur.idSucursalTabla}" />
														<x:graphicImage value="/img/editar.gif" title="Editar una sucursal." border="0" />
													</x:commandLink>
												</h:column>
												
												<h:column>
													<x:commandLink action="#{EmpresaBean.borrarSucursal}" id="eliminarSucursal">
														<f:param id="idSucAElim" name="idSucAElim" value="#{sucur.idSucursalTabla}" />
														<x:graphicImage value="/img/cat_act.gif" onclick="return confirm('¿Confirma la eliminacion de la sucursal?');"
															title="Eliminar una sucursal." border="0" />
													</x:commandLink>
												</h:column>
												
											</h:dataTable>
											
											
											<x:commandLink id="agregarSucursal" action="#{EmpresaBean.agregarSucursal}">
												<x:graphicImage value="/img/cat_pad.gif" title="Agregar una sucursal." border="0" />
											</x:commandLink>
		
		
									</h:panelGrid>
				                </s:fieldset>
				                <s:fieldset rendered="#{!EmpresaBean.muestroLista}" id="altaSucursales" legend="#{EmpresaBean.tituloCorto}">
				                        
				                        <h:panelGrid columns="4" id="panelSucursalDetalle">
										    <h:outputText value="Descripción:" id="outputDescrip"></h:outputText>
										    <h:inputText value="#{EmpresaBean.sucursalEmpresa.sucEmpresa.descripcion}" id="inputDescrip"/>
										    <h:outputText value="Autónomo:" id="outputAutonomo" rendered="false"></h:outputText>
										    <h:selectOneMenu id="listAutonomos" value="#{EmpresaBean.sucursalEmpresa.autonomoSeleccionado}" styleClass="lista" 
											immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px" rendered="false">
											<f:selectItems value="#{EmpresaBean.listaAutonomos}" />
											</h:selectOneMenu>
		        						</h:panelGrid>

				                        
				                        <s:fieldset legend="Domicilio" id="fieldDomicilio">
				                            <h:panelGrid columns="2" align="center" id="domic">
												<h:panelGrid columns="4" id="panelDomicilio" width="600" align="center">
														<h:outputText id="txtCalle" value="Calle:" styleClass="texto"/>
														<h:outputText id="outTxtCalle" value="#{EmpresaBean.sucursalEmpresa.sucEmpresa.domicilio.calleNombre}" />
														<h:outputText id="txtNumero" value="Número:" styleClass="texto"/>
														<h:outputText id="outTxtNumero" value="#{EmpresaBean.sucursalEmpresa.sucEmpresa.domicilio.calleNumero}" />
														<h:outputText id="txtBarrio" value="Barrio:" styleClass="texto"/>
														<h:outputText id="outTxtBarrio" value="#{EmpresaBean.sucursalEmpresa.sucEmpresa.domicilio.barrio.descripcion}" />
														<h:outputText id="txtLocalidad" value="Localidad:" styleClass="texto"/>
														<h:outputText id="outTxtLocalidad" value="#{EmpresaBean.sucursalEmpresa.sucEmpresa.domicilio.localidad.nombre}" />
												</h:panelGrid>
												<x:commandLink id="agregarDomicilioLink" action="#{EmpresaBean.agregarDomicilioPopup}" >
															<x:graphicImage value="/img/icon/home_24.gif" title="Agregar-Modificar domicilio." border="0" />
												</x:commandLink>
											</h:panelGrid>
										</s:fieldset>
				
				
				                       <s:fieldset legend="Teléfonos:" id="fieldTelefonos">
							    
							             <h:panelGrid columns="2" id="panelDeTelefonos" width="650" align="center">
									      <h:dataTable value="#{EmpresaBean.sucursalEmpresa.telefonos}"
										   id="tablaDeTelefonosSuc" styleClass="standardTable" headerClass="dataTable_Header"
										   footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										   columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										   var="telef" style=" width : 600px;">
										   <h:column>
											 <f:facet name="header">
												<h:outputText value="Número" styleClass="texto" />
											 </f:facet>
											 <h:outputText value="#{telef.sucTelefono.telefono.nroTlefono}"/>
										   </h:column>
									       <h:column>
										  	 <f:facet name="header">
												 <h:outputText value="¿Es celular?" styleClass="texto"  />
											 </f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.esCelular}"/>
									    	</h:column>
										 	<h:column>
												<f:facet name="header">
													<h:outputText value="¿Es Fax?" styleClass="texto"  />
												</f:facet>
												<h:outputText value="#{telef.sucTelefono.telefono.esFax}"/>
											</h:column>
											
											<h:column>
												<f:facet name="header">
													<h:outputText value="Tipo Telefono" styleClass="texto"  />
												</f:facet>
												<h:outputText value="#{telef.sucTelefono.telefono.tipo.descripcion}" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Horarios" styleClass="texto"  />
												</f:facet>
												<h:outputText value="#{telef.sucTelefono.telefono.horarios}" />
											</h:column>

											<h:column>
												<f:facet name="header">
													<h:outputText value="Descripcion" styleClass="texto" />
												</f:facet>
												<h:outputText value="#{telef.sucTelefono.telefono.descripcion}" />
											</h:column>
										
											<h:column>
												<x:commandLink action="#{EmpresaBean.editarTelefono}" id="editarTelefonoSucursal">
													<f:param id="idTelSucEdit" name="idTelSucEdit" value="#{telef.codigo}" />
													<x:graphicImage value="/img/editar.gif" title="Editar un telefono." border="0" />
												</x:commandLink>
											</h:column>
											<h:column>
												<x:commandLink action="#{EmpresaBean.eliminarTelefono}" id="eliminarTelefonoSucursal">
													<f:param id="idTelAElim" name="idTelAElim" value="#{telef.codigo}" />
													<x:graphicImage value="/img/cat_act.gif" title="Eliminar un teléfono." border="0" />
												</x:commandLink>
											</h:column>
									     </h:dataTable>
										<x:commandLink id="agregarTelefono" action="#{EmpresaBean.agregarTelefono}">
											<x:graphicImage value="/img/cat_pad.gif" title="Agregar un telefono." border="0" />
										</x:commandLink>
							      </h:panelGrid>
				               </s:fieldset>
							
				                <s:fieldset legend="Gestionar Email">
							
								<%-- GESTIONAR EMAILS --%>							             		             		
									<h:panelGrid columns="2" width="700" id="panelGestionarEmails">
										<h:dataTable value="#{EmpresaBean.sucursalEmpresa.email}" var="sucursalEmail" id="tablaEmail"
													 styleClass="standardTable"
						                             headerClass="dataTable_Header"
						                             footerClass="standardTable_Header"
						                             rowClasses="standardTable_Row1,standardTable_Row2"
						                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
													 style=" width : 700px;">
					                        <h:column>
					                            <f:facet name="header">
					                                <h:outputText value="Email" styleClass="texto" />
					                            </f:facet>
					                            <h:inputText value="#{sucursalEmail.sucEmail.email.email}" style=" width : 213px;" size="50" maxlength="50" 
					                            			 styleClass="bordeceldatext" onfocus="encender(this);" 
					                            			 onblur="apagar(this);"/>
					                        </h:column>								
						                        
					                        <h:column>
					                            <f:facet name="header">
					                                <h:outputText value="Descripción" styleClass="texto" />
					                            </f:facet>
					                            <h:inputText value="#{sucursalEmail.sucEmail.email.descripcion}" style=" width : 271px;" size="255" 
					                             			 maxlength="255" styleClass="bordeceldatext" 
					                             			 onfocus="encender(this);" onblur="apagar(this);"/>
					                        </h:column>
					                        
					                        <h:column>
												<x:commandLink action="#{EmpresaBean.eliminarEmail}" id="eliminarEmailLink">
													<f:param id="idEmailAElim" name="idEmailAElim" value="#{sucursalEmail.codigo}"/>							
													<x:graphicImage value="/img/cat_act.gif" title="Eliminar email." border="0"/>								
												</x:commandLink>
											</h:column>
										</h:dataTable>
										<h:panelGroup>
											<x:commandLink action="#{EmpresaBean.agregarEmail}" id="agregarEmailLink">
												<x:graphicImage value="/img/cat_pad.gif" title="Agregar email." border="0"/>								
											</x:commandLink>						
										</h:panelGroup>
									</h:panelGrid>	
							
		                     	</s:fieldset>
				
					   
					
					            <s:fieldset legend="Actividad">
											<h:panelGrid id="panelPrincipalActividad" columns="2" width="700" >
												
												<h:outputText value="Rubro:" styleClass="texto"/>
												<h:selectOneMenu id="lstRubroSuc" value="#{EmpresaBean.sucursalEmpresa.idRubroSeleccionado}"
													binding="#{EmpresaBean.rubro}" valueChangeListener="#{EmpresaBean.sucursalEmpresa.cambiarActividadesSuc}"
													styleClass="lista" immediate="true" onfocus="encender(this);"
													onblur="apagar(this);" style="width: 200" onchange="submit()">
													<f:selectItems id="itemsRubroSuc" value="#{EmpresaBean.sucursalEmpresa.rubroItem}"/>
												</h:selectOneMenu>
												<h:outputText  value="Actividad:" styleClass="texto"/>
							                    <h:selectOneMenu id="lstActi" value="#{EmpresaBean.sucursalEmpresa.idActividadSeleccionada}"
								                  binding="#{EmpresaBean.actividad}" styleClass="lista" immediate="true" onfocus="encender(this);"
								                  onblur="apagar(this);" style="width: 200">
								                 <f:selectItems id="itemsActividadSuc" value="#{EmpresaBean.sucursalEmpresa.actividadItem}"/>
							                    </h:selectOneMenu>
												
											</h:panelGrid>
							 	</s:fieldset>	
				
								 <f:verbatim><hr align="center" width="600"></f:verbatim>
					                            <h:panelGrid columns="7" width="560">
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						                	<x:commandButton id="buttonAceptarDomicilioPopup" value="Grabar Sucursal" 
						                					 action="#{EmpresaBean.grabarSucursal}" 
						                					 styleClass="btn btn-primary btn-flat pull-right" />
									<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
															 action="#{EmpresaBean.ocultarSucursal}" 
															 styleClass="btn btn-primary btn-flat pull-right" />
								</h:panelGrid>   
		                </s:fieldset>
                </h:panelGrid>
				<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid rendered="#{EmpresaBean.muestroLista}"  columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarDomicilioPopupDos" value="Grabar Empresa" 
	                					 action="#{EmpresaBean.grabarEmpresa}" 
	                					 styleClass="btn btn-primary btn-flat pull-right" 
	                					 actionListener="#{EmpresaBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopupDos" value="Cancelar" 
										 action="#{EmpresaBean.cancelar}" 
										 styleClass="btn btn-primary btn-flat pull-right" onclick="window.close();"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{EmpresaBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
