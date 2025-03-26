<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
   	<title><h:outputText value="#{TipoTareaBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('amTipoTareaForm').submit();
    	}
    </s:script>
</head>

<%@include file="/inc/includes.jsp" %>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amTipoTareaForm');" onload="if('${TipoTareaBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${TipoTareaBean.tituloCorto}
    <small>${TipoTareaBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>


<center>
<h:form id="amTipoTareaForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!TareaBean.popup.mostrar}">
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
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalProceso">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				<f:verbatim>
				<input type="hidden" id="pCuenta" name="pCuenta" value="${param.cuenta}"/> 
				</f:verbatim>
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{TipoTareaBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{TipoTareaBean.popup.nombreIcono}" />
					        <h:outputText value=" #{TipoTareaBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{TipoTareaBean.irANuevoTipoTarea}" 
				        		 onclick="clickLink('nuevoTipoTarea');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="botones" title="Agregar nueva tarea."/>

				        	<x:commandButton action="#{TipoTareaBean.irAModificarTipoTarea}" 
				        		 onclick="clickLink('modificarTipoTarea');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="botones" title="Seguir modificando la tarea."/>
							
							<x:commandButton action="#{TipoTareaBean.irAListarTipoTarea}" 
								 onclick="clickLink('listarTipoTarea');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="botones" title="Ir al listado de tareas."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoTipoTarea" action="#{TipoTareaBean.irANuevoTipoTarea}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarTipoTarea" action="#{TipoTareaBean.irAModificarTipoTarea}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarTipoTarea" action="#{TipoTareaBean.irAListarTipoTarea}" forceId="true" style="display: block;"/>
							
             		<h:panelGrid id="panelPrincipalUno" columns="3" width="350">
		                <h:outputText id="outTitulo" value="Descripción: " styleClass="texto"/>
						<h:inputText id="inTitulo" value="#{TipoTareaBean.descripcion}" 
									 size="11" styleClass="bordeceldatext" 
									 style=" width : 190px;" onfocus="encender(this);" onblur="apagar(this);"/>
						
						<f:verbatim>&nbsp;</f:verbatim>
						
		                <h:outputText id="outImagen" value="Imagen: " styleClass="texto"/>
						<x:inputFileUpload id="impImagen"
		                	storage="file"
		                	styleClass="fileUploadInput"
	    	            	value="#{TipoTareaBean.archivoImagen}"
	        	        	onfocus="encender(this);" onblur="apagar(this);"> 
	            	    </x:inputFileUpload>
	            	    
					</h:panelGrid>
					
					
				<f:verbatim>
					<br>
				</f:verbatim>				
				
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>						
	                	<x:commandButton id="buttonGrabar" value="Guardar" action="#" actionListener="#{TipoTareaBean.grabarTipoTareaListener}" styleClass="botones"/>
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{TipoTareaBean.cancelar}" styleClass="botones" />
					</h:panelGrid>					
			</h:panelGrid>
           </h:panelGroup>
        </f:facet>

        <%@include file="/inc/page_footer.jsp" %>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{TipoTareaBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


</body>
</html>
</f:view>
