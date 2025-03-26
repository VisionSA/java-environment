<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="Tarjeta Fiel - Calendario de Pagos"/></title>
    <s:script language="javascript">
    	function popupCalendar(pagina,id,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'+id+','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>   	
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('calendarioForm');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    PROVEEDORES
    <small>Calendario de Pago</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>

<secure:check/>
<h:form id="calendarioForm">

    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalProveedores">
				
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				
				<h:panelGrid columns="1">
					<x:div style="width: 210px; overflow: auto;" >
						<h:panelGrid columns="3" id="panelSchedule">
							<x:commandLink action="#{bindingScheduleHandler.retroceder}" id="retrocederCalendario">
								<x:graphicImage value="/img/cat_act.gif" title="Retrocede el calendario." border="0"/>								
							</x:commandLink>
							<h:outputText value="<- #{bindingScheduleHandler.mes} ->" styleClass="texto" style="FONT-WEIGHT: bold; font: bold;"/>
							<x:commandLink action="#{bindingScheduleHandler.avanzar}" id="avanzarCalendario">
								<x:graphicImage value="/img/cat_pad.gif" title="Avanza el calendario." border="0"/>								
							</x:commandLink>
						</h:panelGrid>
					</x:div>							
					
					<x:div style="width: 850px;height: 130px;">
						<x:schedule value="#{bindingScheduleHandler.model}" id="schedule1"
						    binding="#{bindingScheduleHandler.schedule}" 
							rendered="true" visibleEndHour="18" visibleStartHour="8"
							workingEndHour="17" workingStartHour="9" readonly="false"
							theme="default" tooltip="true" headerDateFormat="dd-MMM-yyyy"
							compactWeekRowHeight="200" compactMonthRowHeight="100"
							detailedRowHeight="22" submitOnClick="true"
							mouseListener="#{bindingScheduleHandler.scheduleClicked}"
							monthClass="table-borde"
							action="#{bindingScheduleHandler.scheduleAction}"/>
					</x:div>						
				</h:panelGrid>

				<h:panelGroup>
					<f:verbatim>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>												
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
						<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
					</f:verbatim>
				</h:panelGroup>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{bindingScheduleHandler.irACalendarioPagos}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>
