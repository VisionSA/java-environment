<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel | Informes Clearing"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>
<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onload="${ClearingBean.popupReport}">
<h:form id="mainMenu" style="display: none">
	  <jsp:include page="/inc/navigation_test.jsp" />
	  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
	</h:form>

	<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    Transacción
	    <small>Reportes</small>
	  </h1>
	</section>

	<section class="content">

	<div class="box box-default">
	<div class="box-header">
		<h3 class="box-title">Informes Clearing</h3>
	</div>
<center>

<secure:check/>

<h:form id="generalForm">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
            	
			<h:panelGrid columns="1" align="center">
			<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<s:layoutingTitlePane id="filtroProveedores"
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >

					<h:panelGrid id="filtroUno" columns="1" align="center" width="500">

						
						<f:verbatim>&nbsp;</f:verbatim>
						<h:panelGrid id="panelTipoArchivo" columns="2" width="500"
								align="center">

								<h:outputText value="Seleccione la Empresa:" styleClass="texto" />
								<h:selectOneMenu id="lstTipoConc"  
									value="#{ClearingBean.reporteSeleccionado}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px"
									binding="#{ClearingBean.tipoAccion}">
									<f:selectItems id="itemTipo" value="#{ClearingBean.tipoBusquedaItems}" />
								</h:selectOneMenu>
												
						</h:panelGrid>
						<f:verbatim>&nbsp;</f:verbatim>
						
						<h:commandButton action="#{ClearingBean.generarInforme}" 
									value="Generar" styleClass="btn btn-primary btn-flat pull-right"/>
										
						
					</h:panelGrid>
					
				</s:layoutingTitlePane>
			        
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ClearingBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
