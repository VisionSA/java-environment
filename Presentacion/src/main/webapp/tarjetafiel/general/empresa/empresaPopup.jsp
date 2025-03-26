<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar empresa"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
     <s:script language="javascript">
		var idSeleccionado;
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
		function checkearSeleccionado(ochk){
				var cantFila=parseInt(document.getElementById('amEmpresaForm:tamanioLstDomicilio').value);
                var direccion = ochk.id.charAt(35);
				var direccion2;
				var aux = document.getElementById('amEmpresaForm:tamanioLstDomicilio').value;
				for(var i=0;i < aux;i++){
					if(i!=direccion){
	                	document.getElementById('amEmpresaForm:tablaDeDomiciliosSuc:' + i + ':idCentral').checked=false;
	            		idSeleccionado=ochk.id;
					}
				}			
		};
	
		function chequearSeleccionado(){
			if(document.getElementByID(idSeleccionado).value==false){
				document.getElementByID(idSeleccionado).value=true;
			}
		};
		
	</s:script>

</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('amEmpresaForm');" onLoad="chequearSeleccionado();">
<center>
<h:form id="amEmpresaForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Empresa" style="FONT-SIZE: large;" styleClass="texto"/>
		
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
											value="Nuevo" styleClass="botones" title="Agregar nueva."/>
										<x:commandButton action="#{EmpresaBean.irAModificarEmpresa}" 
											onclick="clickLink('modificarEmpresa');dojo.widget.byId('viewDialog').hide();"
											value="Modificar" styleClass="botones" title="Seguir modificando."/>
										<x:commandButton action="#{EmpresaBean.irAListarEmpresas}" 
											onclick="clickLink('listarEmpresas');dojo.widget.byId('viewDialog').hide();"
											id="cmdListar" value="Listar" styleClass="botones" title="Ir al listado."/>
									</h:panelGrid>
								</s:modalDialog>
								
								
								<x:commandLink id="nuevaEmpresa" action="#{EmpresaBean.irANuevaEmpresa}" forceId="true" style="display: block;"/>
								<x:commandLink id="modificarEmpresa" action="#{EmpresaBean.irAModificarEmpresa}" forceId="true" style="display: block;"/>
								<x:commandLink id="listarEmpresas" action="#{EmpresaBean.irAListarEmpresas}" forceId="true" style="display: block;"/>
							    <h:panelGrid columns="6" id="panelEmpresa" align="center">
								    <h:outputText value="Cuit:" id="outputCuit"></h:outputText>
								    <h:inputText value="#{EmpresaBean.empresa.cuit}" disabled="true" id="inputCuit"/>
								    <h:outputText value="Razon Social:" id="outputRazonSocial"></h:outputText>
								    <h:inputText value="#{EmpresaBean.empresa.razonSocial}" id="inputRazonSocial"/>
								    <h:outputText value="¿Es Riesgosa?" id="outputRiesgo"/>
								    <h:selectBooleanCheckbox value="#{EmpresaBean.esRiesgosa}" id="selecRiesgo"> </h:selectBooleanCheckbox>
								</h:panelGrid>
			                    <s:fieldset legend="Domicilio central: " id="fieldRubroTamanio" >
								    <h:panelGrid columns="4" id="gridRubroTamanio">
								        <h:outputText id="outputIndicacion2" value="Ingrese el tamaño de la empresa: " rendered="false"/>
								        <h:selectOneMenu id="listTamEmpresa" value="#{EmpresaBean.idTamanio}" styleClass="lista" rendered="false"
												immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 150px">
											<f:selectItems value="#{EmpresaBean.listaTamanios}" />
										</h:selectOneMenu>																		
								    </h:panelGrid> 
								   	<h:panelGrid columns="2">
										<h:outputText id="outputSucursalDomicilioCentral" value="Seleccione el Domicilio Central: " />
								       	<h:selectOneMenu id="listDomicilioCentral" value="#{EmpresaBean.idDomPrincipal}" styleClass="lista" 
											immediate="true" onfocus="encender(this);" onblur="apagar(this);" style="width: 411px" onchange="submit();">
											<f:selectItems id="selectDom" value="#{EmpresaBean.domPrincipalItems}"/>										
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
														<h:outputText id="outDescripcion" value="Descripción" styleClass="texto" style="font: bold;color: white;" />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.descripcion}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText id="outCalle" value="Calle" styleClass="texto" style="font: bold;color: white;" />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.domicilio.calleNombre}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText id="outNumero" value="Número" styleClass="texto" style="font: bold;color: white;" />
													</f:facet>
													<h:outputText value="#{sucur.sucEmpresa.domicilio.calleNumero}"/>
												</h:column>
												
												<h:column>
													<f:facet name="header">
														<h:outputText id="outLocalidad" value="Localidad" styleClass="texto" style="font: bold;color: white;" />
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

				                        
				                       
				
			
				<h:inputHidden id="tamanioLstDomicilio" value="#{EmpresaBean.sucursalEmpresa.tamanioLstDomicilio}"></h:inputHidden>
				<s:fieldset legend="Domicilios:" id="fieldDomicilios">
							    
							    <h:panelGrid columns="2" id="panelDeDomicilios" width="650" align="center">
									<h:dataTable value="#{EmpresaBean.sucursalEmpresa.lstDomicilios}"
										id="tablaDeDomiciliosSuc" styleClass="standardTable" headerClass="dataTable_Header"
										footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
										columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
										var="domicilio" style=" width : 600px;">
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Calle" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.domicilio.calleNombre}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.domicilio.calleNumero}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Barrio" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.domicilio.barrio.descripcion}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Localidad" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{domicilio.domicilio.localidad.nombre}"/>
										</h:column>	
														
										<h:column>
											<f:facet name="header">
												<h:outputText value="Central" styleClass="texto" style="font: bold;color: white;" />	
											</f:facet>
											<h:selectBooleanCheckbox id="idCentral"  onclick ="checkearSeleccionado(this)" value="#{domicilio.boolCentral}"
                        					immediate="true" /> 											
										</h:column>						
																			

										<h:column>
											<x:commandLink action="#{domicilio.editarLstDomicilio}" id="editarLstDomicilio">
												<f:param id="idDomicilioAEdit" name="idDomicilioAEdit" value="#{domicilio.codigo}" />
												<x:graphicImage value="/img/editar.gif" title="Editar un Domicilio." border="0" />
											</x:commandLink>
										</h:column>
										<h:column>
											<x:commandLink action="#{domicilio.eliminarLstDomicilio}" id="eliminarLstDomicilio">
												<f:param id="idDomicilioAElim" name="idDomicilioAElim" value="#{domicilio.codigo}" />
												<x:graphicImage value="/img/cat_act.gif" title="Eliminar un Domicilio." border="0" />
											</x:commandLink>
										</h:column>
									</h:dataTable>
									
									
									<x:commandLink id="agregarLstDomicilio" action="#{EmpresaBean.agregarLstDomicilio}">
										<x:graphicImage value="/img/cat_pad.gif" title="Agregar un Domicilio." border="0" />
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
												<h:outputText value="Número" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.nroTlefono}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="¿Es celular?" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.esCelular}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="¿Es Fax?" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.esFax}"/>
										</h:column>
										
										<h:column>
											<f:facet name="header">
												<h:outputText value="Tipo Telefono" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.tipo.descripcion}" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Horarios" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.horarios}" />
										</h:column>

										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripcion" styleClass="texto" style="font: bold;color: white;" />
											</f:facet>
											<h:outputText value="#{telef.sucTelefono.telefono.descripcion}" />
										</h:column>
										
										<h:column>
											<x:commandLink action="#{telef.editarTelefono}" id="editarTelefonoSucursal">
												<x:graphicImage value="/img/editar.gif" title="Editar un telefono." border="0" />
											</x:commandLink>
										</h:column>
										<h:column>
											<x:commandLink action="#{telef.eliminarTelefono}" id="eliminarTelefonoSucursal">
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
					                                <h:outputText value="Email" styleClass="texto" style="font: bold;color: white;"/>
					                            </f:facet>
					                            <h:inputText value="#{sucursalEmail.sucEmail.email.email}" style=" width : 213px;" size="50" maxlength="50" 
					                            			 styleClass="bordeceldatext" onfocus="encender(this);" 
					                            			 onblur="apagar(this);"/>
					                        </h:column>								
						                        
					                        <h:column>
					                            <f:facet name="header">
					                                <h:outputText value="Descripción" styleClass="texto" style="font: bold;color: white;"/>
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
	                					 styleClass="botones" />
				<x:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
										 action="#{EmpresaBean.ocultarSucursal}" 
										 styleClass="botones"/>
			</h:panelGrid>   

				
				
				
				                </s:fieldset>
				<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid rendered="#{EmpresaBean.muestroLista}"  columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<x:commandButton id="buttonAceptarDomicilioPopupDos" value="Grabar Empresa" 
	                					 action="#{EmpresaBean.grabarEmpresa}" 
	                					 styleClass="botones" 
	                					 actionListener="#{EmpresaBean.recargarYCerrarPopup}"/>
						<x:commandButton id="buttonVolverDomicilioPopupDos" value="Cancelar" 
										 action="#{EmpresaBean.cancelar}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>
		
		</h:panelGroup>
		</x:panelTabbedPane>
		
</h:form>
</center>
</body>
</html>
</f:view>