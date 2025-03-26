<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
    <title><h:outputText value="#{VerificadoresBean.tituloLargo}" /></title>
    <script src="http://java.com/js/deployJava.js"></script>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
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

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');" onload="${VerificadoresBean.popupReport}">
	<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${VerificadoresBean.tituloCorto}
    <small>${VerificadoresBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
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
<!-- 			INCLUDE PARA LOS ERRORES 	 -->
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				<s:layoutingTitlePane id="filtroProveedores"
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >								      	

					<h:panelGrid id="filtroUno" columns="1" align="center" width="546">
								
						<h:panelGrid align="center">
						<f:verbatim>	
								<h:outputText value="Último Procesamiento: "
								style="padding-right:5px; font-size: 14px;color: black; align='center'"/>
								
								<h:outputText value="#{VerificadoresBean.ultimaFecha}" 
								style="font-size: 14px;color: blue; align='center'"/>
						</f:verbatim>
						</h:panelGrid>
						
						<f:verbatim>&nbsp;</f:verbatim>
						
						<h:panelGrid align="center">								 
								 <h:commandButton actionListener="#{VerificadoresBean.generarListadoVerificadores}" 
											 value="Generar Reporte" styleClass="btn btn-primary btn-flat" />
						</h:panelGrid>
												
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{VerificadoresBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>        
</body>
</html>
</f:view>
