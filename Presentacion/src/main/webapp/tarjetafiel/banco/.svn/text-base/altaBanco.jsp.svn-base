
<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="#{BancoBean.tituloLargo}"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaBanco');">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${BancoBean.tituloCorto}
    <small>${BancoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>
<center>

<secure:check/>

<h:form id="altaBanco">    
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

               
        <f:facet name="body">
            <h:panelGroup id="body">
            	            	            
        		<h:panelGrid columns="1" align="center" width="900px">            	
					<%-- INCLUDE PARA LOS ERRORES --%> 	
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>
				     		
				     		
	              	<h:panelGrid id="grid" columns="2" width="277" align="center">
	                	<h:outputText id="outCodigo" value="Código: " styleClass="texto"/>
	                	<h:inputText id="Codigo" value="#{BancoBean.banco.codigo}" 
	                			  size="10" styleClass="bordeceldainferior" style="width: 70px"/>

	                	<h:outputText id="outDescripcion" value="Descripción: " styleClass="texto"/>
	                	<h:inputText id="Descripcion" value="#{BancoBean.banco.descripcion}" size="45"
	                			  style=" width : 250px;" styleClass="bordeceldatext"/>	                	
	              	</h:panelGrid>
	              
					<f:verbatim><hr align="center" width="300"></f:verbatim>
					<h:panelGrid columns="10" width="637">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>												
						<h:commandButton id="buttonGrabar" value="Grabar" action="#{BancoBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
						<h:commandButton id="buttonBorrar" value="Cancelar" action="#{BancoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right"/>
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

for(var i = 1; i < mainMenu__idJsp1_menu.length-1; i++) {
    var obj = mainMenu__idJsp1_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{BancoBean.inicializar}") + `</li>`;
}

</script>    

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
