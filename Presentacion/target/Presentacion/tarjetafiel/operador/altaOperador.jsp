<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="#{OperadorBean.tituloLargo}"/></title>
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaOperadores');" onload="if('${OperadorBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${OperadorBean.tituloCorto}
    <small>${OperadorBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

<secure:check/>

<h:form id="altaOperadores">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!OperadorBean.popup.mostrar}">
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
			                   dialogTitle="#{OperadorBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{OperadorBean.popup.nombreIcono}" />
					        <h:outputText value=" #{OperadorBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{OperadorBean.irANuevoOperador}" 
				        		 onclick="clickLink('nuevoOperador');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nuevo operador."/>

				        	<x:commandButton action="#{OperadorBean.irAModificarOperador}" 
				        		 onclick="clickLink('modificarOperador');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando el operador."/>
							
							<x:commandButton action="#{OperadorBean.irAListarOperador}" 
								 onclick="clickLink('listarOperador');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado de operadores."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoOperador" action="#{OperadorBean.irANuevoOperador}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarOperador" action="#{OperadorBean.irAModificarOperador}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarOperador" action="#{OperadorBean.irAListarOperador}" forceId="true" style="display: block;"/>
            	
            	
	        <h:panelGrid columns="1" align="center">
              	<h:panelGrid id="grid" columns="2" width="400">
              	
	               <h:outputText id="outUsername" value="Username: " styleClass="texto"/>
<%--@I4287--%>	                <h:inputText id="Username" value="#{OperadorBean.op.username}" size="30" maxlength="30" 
                			 styleClass="bordeceldatext" style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
<%--@F4287--%>
	                <h:outputText id="outNombre" value="Nombre: " styleClass="texto"/>
<%--@I4287--%>	                <h:inputText id="Nombre" value="#{OperadorBean.op.nombre}" 
	                			 styleClass="bordeceldatext" maxlength="45" size="30" style="width: 250px" 
	                			 onfocus="encender(this);" onblur="apagar(this);"/>
<%--@F4287--%>					
	                <h:outputText id="outApellido" value="Apellido: " styleClass="texto"/>
<%--@I4287--%>	                <h:inputText id="Apellido" value="#{OperadorBean.op.apellido}" 
	                			 styleClass="bordeceldatext" maxlength="45" size="30" style="width: 250px" 
	                			 onfocus="encender(this);" onblur="apagar(this);"/>
<%--@F4287--%>					
	                <h:outputText id="outEMail" value="Email: " styleClass="texto"/>
<%--@I4287--%>	                <h:inputText id="Email" value="#{OperadorBean.op.email}" 
	                			 styleClass="bordeceldatext" maxlength="45" size="30" style="width: 250px"
	                			 onfocus="encender(this);" onblur="apagar(this);" />
<%--@F4287--%>					

	                <h:outputText id="outRol" value="Rol:" styleClass="texto"/>
					<h:selectOneMenu id="Rol" value="#{OperadorBean.rolSeleccionado}" immediate="true"
       					 	styleClass="lista" onfocus="encender(this);" onblur="apagar(this);">
       					<f:selectItems value="#{OperadorBean.roles}"/>
       				</h:selectOneMenu>
       				
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>
					<h:outputText id="estado" value="Estado: " />
					<h:panelGroup>
<%--@4287--%>						<h:selectOneMenu id="Estados" value="#{OperadorBean.estado}" immediate="true"
       					 	styleClass="lista" onfocus="encender(this);" onblur="apagar(this);">
	       					<f:selectItems value="#{OperadorBean.listEstados}"/>
<%--@F4287--%>	       				</h:selectOneMenu>
					</h:panelGroup>
					<f:verbatim>&nbsp;</f:verbatim>
					<f:verbatim>&nbsp;</f:verbatim>					
					<h:outputText id="outClave" value="Clave: " styleClass="texto" rendered="#{OperadorBean.verClave}"/>
<%--@I4287--%>					<x:inputSecret id="Clave" value="#{OperadorBean.op.clave}" 
		               			 size="10" onfocus="encender(this);" onblur="apagar(this);"
		               			 styleClass="bordeceldatexto" maxlength="10" style="width: 90px"
		               			 rendered="#{OperadorBean.verClave}"/>
<%--@F4287--%>

					<h:outputText id="outRepClave" value="Confirmar Clave: " styleClass="texto" rendered="#{OperadorBean.verClave}"/>
		            <x:inputSecret id="RepClave" value="#{OperadorBean.confirmarClave}" 
		               		 size="10" onfocus="encender(this);" onblur="apagar(this);"
		               		 styleClass="bordeceldatexto" maxlength="10" style="width: 90px" 
		               		 rendered="#{OperadorBean.verClave}"/>
					<h:commandButton id="buttonClave" value="Cambiar Clave" action="#{OperadorBean.cambiarClave}" 
	                	styleClass="btn btn-primary btn-flat pull-right" rendered="#{!OperadorBean.verClave}"/>
				</h:panelGrid>					
				
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="637">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
	                <h:commandButton id="buttonGrabar" value="Grabar" action="#{OperadorBean.grabar}" styleClass="btn btn-primary btn-flat pull-right"/>
					<h:commandButton id="buttonSalir" value="Salir" action="#{OperadorBean.salir}" styleClass="btn btn-primary btn-flat pull-right"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{OperadorBean.inicializar}") + `</li>`;
}

</script>   
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
