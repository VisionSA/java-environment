<%--@I4503--%>
<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{InfoSistemaBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amTipoDomicilioForm').submit();
		}
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amTipoDomicilioForm');" onload="if('${InfoSistemaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${InfoSistemaBean.tituloCorto}
    <small>${InfoSistemaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
	<secure:check/>

	<h:form id="amTipoDomicilioForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!InfoSistemaBean.popup.mostrar}">
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
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>						
						
						<h:panelGrid id="uno" columns="1" width="850" align="center">
							<s:layoutingTitlePane id="env" label="Variables de Entorno" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id="uno_cero" columns="1" >
									
									<x:dataTable value="#{InfoSistemaBean.vars}" id="varEntornos"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="var" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{var.clave}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{var.valor}" styleClass="texto" />  
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>
							</s:layoutingTitlePane>
						
						
							<s:layoutingTitlePane id="conex" label="Datos de Conexion" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id="uno_uno" columns="1" >
									<x:dataTable value="#{InfoSistemaBean.datosConexion}" id="datConexion"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="conexion" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{conexion.clave}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{conexion.valor}" styleClass="texto" />  
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>		
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="sisArchivos" label="Sistema de Archivos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id ="uno_dos" columns="1" width="500">
									<x:dataTable value="#{InfoSistemaBean.datosArchivos}" id="datArchivos"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="archivo" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{archivo.clave}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{archivo.valor}" styleClass="texto" />  
		    		                    </x:column>
									</x:dataTable>		
								</h:panelGrid>
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="configFlex" label="Configuracion de Proyectos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id ="uno_tres" columns="1" width="500">
									<x:dataTable value="#{InfoSistemaBean.configFlex}" id="confFlex"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="conf" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{conf.clave}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{conf.valor}" styleClass="texto" />  
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="version" label="Datos de Version" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id ="uno_cuatro" columns="1" width="500">
									<x:dataTable value="#{InfoSistemaBean.version}" id="tablaversion"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="ver" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{ver.clave}" styleClass="texto" escape="false"/>
		    		                    </x:column>
				                        <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{ver.valor}" styleClass="texto" escape="false"/>  
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="paramBase" label="Parametros definidos en la tabla de parametos en la base datos" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id ="uno_cinco" columns="1" width="500">
									<x:dataTable value="#{InfoSistemaBean.dbParametros}" id="tablaDBParametros"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="tablaDB" style=" width : 890px;">
				                        <x:column style="width : 220px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Nombre" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{tablaDB.descripcion}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column  >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Detalle y Valor" styleClass="texto" />
		                    		        </f:facet>
				                              <x:dataList id="dt1" value="#{tablaDB.detalleList}" 
											      var="item" first="0" 
											      dir="LTR" >
													    <x:outputText  value="#{item.detalleYValor}"/>
													    <f:verbatim>
													    	<br>
													    </f:verbatim>
											  </x:dataList>
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>
							</s:layoutingTitlePane>
							
							<s:layoutingTitlePane id="kettle" label="Datos Kettle" containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
								<h:panelGrid id ="uno_seis" columns="1" width="500">
									<x:dataTable value="#{InfoSistemaBean.kettle}" id="tablakettle"
												 styleClass="standardTable table-striped"
	                    		       			 headerClass="standardTable_Header"
	                           					 footerClass="standardTable_Header"
			                           			 rowClasses="standardTable_Row1,standardTable_Row2"
			                           			 sortable="true"
	        		                   			 columnClasses="standardTable_Column"							             
									             var="ket" style=" width : 890px;">
		    		                    <x:column style="width : 20px;">>
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Id" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{ket.idKettleConfig}" styleClass="texto" />
		    		                    </x:column>
				                        <x:column style="width : 200px;">
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Codigo" styleClass="texto" />
		                    		        </f:facet>
			                                <h:outputText value="#{ket.codigo}" styleClass="texto" />
		    		                    </x:column>
		    		                    <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Valor" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{ket.valor}" styleClass="texto" />  
		    		                    </x:column>
		    		                    <x:column >
		    		                        <f:facet name="header">
		            		                    <h:outputText value="Descripcion" styleClass="texto" />
		                    		        </f:facet>
				                            <h:outputText value="#{ket.descripcion}" styleClass="texto" />  
		    		                    </x:column>
									</x:dataTable>
								</h:panelGrid>
							</s:layoutingTitlePane>
							
						</h:panelGrid>
						<f:verbatim>
							<br>
						</f:verbatim>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{InfoSistemaBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
<%--@F4503--%>