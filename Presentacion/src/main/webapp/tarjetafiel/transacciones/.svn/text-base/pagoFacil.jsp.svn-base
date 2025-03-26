<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{PagoFacilBean.tituloLargo}"/></title>
	<!--  BEGIN Browser History required section -->
	<link rel="stylesheet" type="text/css" href="../../history/history.css" />
	<!--  END Browser History required section -->

	<script src="../../AC_OETags.js" language="javascript"></script>
	<script src="../../history/history.js" language="javascript"></script>
	
	<s:script language="javascript">
		
		
		var requiredMajorVersion = 9;
	 
		var requiredMinorVersion = 0;
	 
		var requiredRevision = 124;
		
			

		function recargar() {
			document.getElementById('pagoFacilForm').submit();
		}
	</s:script>
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('pagoFacilForm');" onload="if('${PagoFacilBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">
<script language="JavaScript" type="text/javascript">
	<!--
	// Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
	var hasProductInstall = DetectFlashVer(6, 0, 65);
	
	// Version check based upon the values defined in globals
	var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);
	
	if ( hasProductInstall && !hasRequestedVersion ) {
		// DO NOT MODIFY THE FOLLOWING FOUR LINES
		// Location visited after installation is complete if installation is required
		var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
		var MMredirectURL = window.location;
	    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
	    var MMdoctitle = document.title;
	
		AC_FL_RunContent(
			"src", "playerProductInstall",
			"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
			"width", "900",
			"height", "400",
			"align", "middle",
			"id", "ProcesaPagoFacil",
			"quality", "high",
			"bgcolor", "#869ca7",
			"name", "ProcesaPagoFacil",
			"allowScriptAccess","sameDomain",
			"type", "application/x-shockwave-flash",
			"pluginspage", "http://www.adobe.com/go/getflashplayer"
		);
	} else if (hasRequestedVersion) {
		// if we've detected an acceptable version
		// embed the Flash Content SWF when all tests are passed
		AC_FL_RunContent(
				"src", "ProcesaPagoFacil",
				"width", "900",
				"height", "400",
				"align", "middle",
				"id", "ProcesaPagoFacil",
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", "ProcesaPagoFacil",
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer"
		);
	  } else {  // flash is too old or we can't detect the plugin
	    var alternateContent = 'Alternate HTML content should be placed here. '
	  	+ 'This content requires the Adobe Flash Player. '
	   	+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
	    document.write(alternateContent);  // insert non-flash content
	  }
	// -->
	</script>
<center>
	<secure:check/>

	<h:form id="pagoFacilForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!PagoFacilBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			<f:facet name="header">
				<f:subview id="header">
					<jsp:include page="/inc/page_header.jsp" />
					<jsp:include page="/inc/navigation_test.jsp" />
				</f:subview>
			</f:facet>

			<f:facet name="body">
				<h:panelGroup id="body">
					<jsp:include page="/inc/title_header.jsp" >
						<jsp:param name="tituloLargo" value="${PagoFacilBean.tituloLargo}"/>
						<jsp:param name="tituloCorto" value="${PagoFacilBean.tituloCorto}"/>
					</jsp:include>
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					
					<h:panelGrid id="uno" columns="1" width="850" align="center">
						<f:verbatim>
							<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="ProcesaPagoFacil" width="900" height="400"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="../../ProcesaPagoFacil.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="../../ProcesaPagoFacil.swf" quality="high" bgcolor="#869ca7"
				width="900" height="400" name="ProcesaPagoFacil" align="middle"
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object>
  						</f:verbatim>
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
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{PagoFacilBean.cancelar}" styleClass="botones" />
					</h:panelGrid>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>
			<%@include file="/inc/page_footer.jsp" %>
		</x:panelLayout>
	</h:form>
</center>
</body>
</html>
</f:view>
