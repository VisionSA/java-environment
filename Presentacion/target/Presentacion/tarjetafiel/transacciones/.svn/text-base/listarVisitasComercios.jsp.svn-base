<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel | Listas"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onload="${VisitarComerciosBean.popupReport}">
<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Transacción
    <small>Listas</small>
  </h1>
</section>

<section class="content">

	<div class="box box-default">
	<div class="box-header with-border">
		<h3 class="box-title">Listar Comercios a Visitar</h3>
	</div>
	<br/>

<center>
<secure:check/>

<h:form id="saldoProveedoresForm">
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
					<h:panelGrid id="filtroUno" columns="7" align="center" width="546">
					   <h:panelGroup>
							<h:outputText value="Mes:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 		<h:inputText id="idMes" style="width: 60px" value="#{VisitarComerciosBean.mes}" />
                       		<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="Asesor Comercial:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstBanco" value="#{VisitarComerciosBean.promotorSeleccionado}"
									disabled="#{VisitarComerciosBean.listaCargada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{VisitarComerciosBean.lstPromotores}"/>
								</h:selectOneMenu>							   
                        </h:panelGroup> 
					</h:panelGrid>  
						
					<f:verbatim><br></f:verbatim>
					
					<h:commandButton actionListener="#{VisitarComerciosBean.listarVisitasComercios}" 
											 value="Generar Reporte" styleClass="btn btn-primary btn-flat" />

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{VisitarComerciosBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>
