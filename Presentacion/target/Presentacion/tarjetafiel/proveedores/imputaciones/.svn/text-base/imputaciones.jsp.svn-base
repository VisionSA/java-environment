<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="Tarjeta Fiel - Gestionar Imputaciones"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
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

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');"
	  onload="if('${ImputacionBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

	<!-- Content Header (Page header) -->
	<section class="content-header">
	  <h1>
	    Proveedores
	    <small>Imputaciones</small>
	  </h1>
	</section>

	<section class="content">
	
	    <div class="box box-default">
	    <div class="box-header with-border">
	        <h3 class="box-title">Imputar</h3>
	    </div>
	    <br/>	

<center>

<secure:check/>

<h:form id="generalForm"> 						

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!ImputacionBean.popup.mostrar}">
		<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>
        	
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter">

        <f:facet name="body">
            <h:panelGroup id="body">

			<h:panelGrid columns="1" align="center" width="900">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="Gestionar Imputaciones">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{ImputacionBean.popup.nombreIcono}" />
					        <h:outputText value=" #{ImputacionBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{ImputacionBean.irANuevaImputacion}" 
				        		 onclick="clickLink('nuevaImp');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nuevas imutaciones."/>
				        		 
				        	<x:commandButton action="#{ImputacionBean.irAContinuarImputacion}" 
				        		 onclick="clickLink('contImp');dojo.widget.byId('viewDialog').hide();"
				        		 value="Continuar" styleClass="btn btn-primary btn-flat" title="Continuar imputando con este proveedor."/>

							<x:commandButton action="#{ImputacionBean.irAListarImputaciones}" 
								 onclick="clickLink('listarImp');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado de imputaciones."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevaImp" action="#{ImputacionBean.irANuevaImputacion}" forceId="true" style="display: block;"/>
			    <x:commandLink id="contImp" action="#{ImputacionBean.irAContinuarImputacion}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarImp" action="#{ImputacionBean.irAListarImputaciones}" forceId="true" style="display: block;"/>
					
				<c:if test="${ImputacionBean.validado!=true}">		
				<s:layoutingTitlePane id="altaComprobantes"
						      		containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >								
				<h:panelGroup id="errorCuitInvalido" style="width: 300px">
					<h:outputText id="cuitInvalido" value="#{ImputacionBean.cuitInvalido}" style="font-size: 10px;color: red"/>
				</h:panelGroup>	
				
				<f:verbatim><br></f:verbatim>				
            	<h:panelGrid id="panelPrincipalUno" columns="3" width="350" align="center">
	                <h:outputText id="outCuitIngreso" value="CUIT del Proveedor: " styleClass="texto"/>
					<x:inputText id="inCuit" forceId="true"  value="#{ImputacionBean.cuit}" 
									 size="11" maxlength="11" styleClass="bordeceldainferior" 
									 style="width: 100px" onfocus="encender(this);" onblur="apagar(this);" 
									 onkeypress="return soloEnteros(this,event);" />
								
					<x:commandLink id="buscarProveedorLink" action="#{ImputacionBean.buscarProvPopup}">
						<x:graphicImage value="/img/icon/srch_24.gif" title="Buscar Proveedor." border="0"/>								
					</x:commandLink>
																		
				</h:panelGrid>
				
				<f:verbatim><br></f:verbatim>
				<f:verbatim><hr align="center" width="700"></f:verbatim>
				
				<h:panelGrid id="panelbtn" columns="1" width="350" align="center">
					<c:choose>
						<c:when test="${lst:contains(requestScope.permisos,'alta')}">
							<h:commandButton value="Continuar" actionListener="#{ImputacionBean.validarCuit}" 
								styleClass="btn btn-primary btn-flat pull-right"/>
						</c:when>
					<c:otherwise>	
						<h:commandButton value="Continuar" onclick="alert('No posee los permisos necesarios.');" 
							styleClass="btn btn-primary btn-flat pull-right"/>																				       						 
						</c:otherwise>
					</c:choose>					
				</h:panelGrid>	
				
				</s:layoutingTitlePane>					
				</c:if>
					
				<%-- ////////// LLAMADA A LA PAGINA DE IMPUTACIONES ////////// --%>	
		        <%@include file="/tarjetafiel/proveedores/imputaciones/imputacionesInclude.jsp" %>					
				<%-- ///////////////////////////////////////////////////////// --%>			      
				
				<c:if test="${ImputacionBean.validado==true}">
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
						<c:if test="${!empty ImputacionBean.imputaciones}">
		               		<x:commandButton id="buttonGrabar" value="Guardar" action="#{ImputacionBean.grabarImputado}" styleClass="btn btn-primary btn-flat"/>
		               	</c:if>
						<x:commandButton id="buttnnCancelar" value="Cancelar" action="#{ImputacionBean.cancelarImputado}" styleClass="btn btn-primary btn-flat"/>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ImputacionBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp"%>



</body>
</html>
</f:view>
