<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Listar Acreditaciones)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onload="${AcreditacionesBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${AcreditacionesBean.tituloCorto}
    <small>${AcreditacionesBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

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

			<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Reporte" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="1" align="center" width="546">
						<f:verbatim>&nbsp;</f:verbatim>	
						<h:panelGrid columns="7" align="center">
							<h:outputText value="Lote Desde:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 		<h:inputText id="loteDesde" value="#{AcreditacionesBean.loteDesde}" />
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:outputText value="Lote Hasta:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
					 		<h:inputText id="loteHasta" value="#{AcreditacionesBean.loteHasta}" />
					 	</h:panelGrid>
					 	<f:verbatim>&nbsp;</f:verbatim>
						<h:panelGrid id="filtrodos" columns="3" align="center">
								<h:outputText value="Banco Propio:" styleClass="texto"/>
								<f:verbatim>&nbsp;&nbsp;</f:verbatim>
								<h:selectOneMenu id="lstBanco" value="#{AcreditacionesBean.idBancoSeleccionado}"
									disabled="#{AcreditacionesBean.listaCargada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{AcreditacionesBean.bancoItems}"/>
								</h:selectOneMenu>										    
						</h:panelGrid>  
						<f:verbatim><hr align="center" width="600"></f:verbatim> 			
						<h:commandButton actionListener="#{AcreditacionesBean.listarAcreditacionesLotes}" 
										value="Generar Reporte" styleClass="btn btn-primary btn-flat pull-right"/>
					
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{AcreditacionesBean.inicializarLote}") + `</li>`;
}

</script>   

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>
