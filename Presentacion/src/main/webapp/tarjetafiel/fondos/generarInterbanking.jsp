<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="Tarjeta Fiel - Upload Interbanking"/></title>
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
<jsp:include page="/inc/includes.jsp" />
<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generarInterbanking');" onload="verFiltros();">

	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${InterbankingBean.tituloCorto}
    <small>${InterbankingBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div> 
<center>
	

	<h:form id="generarInterbanking">
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
						
						<h:panelGrid columns="1" id="uno" width="700" align="center">
						<s:layoutingTitlePane id="filtroInterbanking" label=" Generacion archivo upload Interbanking " containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
							<f:verbatim><br></f:verbatim>
							<h:panelGrid id="filtroUno" columns="3" width="600" align="center">
								<h:outputText id="outFecha" value="Fecha Solicitada:" styleClass="texto"/>
								<h:panelGroup id="GRdesdeEmision">
							 		<f:verbatim>
								            <div class="input-group date" style="width: 200px">
								                <div class="input-group-addon">
								                    <i class="fa fa-calendar"></i>
								                </div>
								                <input type="text" class="form-control pull-right" id="fDesde" >
								            </div>
									</f:verbatim>
								</h:panelGroup>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>

								<h:outputText value="Banco Propio:" styleClass="texto"/>
								<h:selectOneMenu id="lstBanco" value="#{InterbankingBean.idBancoSeleccionado}"
									disabled="#{InterbankingBean.listaCargada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{InterbankingBean.bancoItems}"/>
								</h:selectOneMenu>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>

								<h:outputText value="Total de Acred:   #{InterbankingBean.totalAcred}" styleClass="texto"/>
								<h:outputText value="Sumatoria:   $ #{InterbankingBean.sumatoria}" styleClass="texto" />
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;</f:verbatim>
								
							</h:panelGrid>
						</s:layoutingTitlePane>
						
						<f:verbatim><hr align="center" width="700"></f:verbatim>
						
						<x:commandButton id="btnBuscar" value="Buscar" styleClass="btn btn-primary btn-flat pull-right"
									action="#{InterbankingBean.listar}" title="Busca las transferencias pendientes" 
									disabled="#{InterbankingBean.listaCargada}"/>
									
						</h:panelGrid>

						<%--c:if test="${!empty InterbankingBean.chequeList}"`--%>

						<h:panelGrid columns="1" align="center" id="paelSecundario" width="850" rendered="#{InterbankingBean.listaCargada}">
							
							<x:commandButton id="btnGenerar" value="Generar Upload" styleClass="btn btn-primary btn-flat"
									action="#{InterbankingBean.generar}" title="Genera el archivo el upload de Iinterbanking" />
							<f:verbatim><br></f:verbatim>
							<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 800px; border: 1px; margin-left: auto; margin-right: auto;">
								<h:dataTable value="#{InterbankingBean.chequeList}"
									id="tablaAcred" styleClass="table-striped table-bordered" headerClass="dataTable_Header"
									footerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
									columnClasses="tda,tdA,tdB,tdA,tdB,tdB,tdB dinero"
									var="obj" style=" width : 850px;">
									<h:column>
									    <f:facet name="header">
									        <h:outputText value="" styleClass="texto"  />
									    </f:facet>
	                                    <x:selectBooleanCheckbox value="#{obj.seleccionado}" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Banco" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{obj.cheque.banco.descripcion}"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Cuenta" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{obj.cheque.cuenta}"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Destinatario" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{obj.cheque.beneficiario}"/>
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Fecha Emision" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{obj.cheque.fechaEmision}"/>
									</h:column>
									<h:column>
									    <f:facet name="header">
									        <h:outputText value="Fecha Pago" styleClass="texto"  />
									    </f:facet>
	                                          <h:outputText value="#{obj.cheque.fechaPago}" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Importe" styleClass="texto"  />
										</f:facet>
										<h:outputText value="#{obj.cheque.importe}"/>
									</h:column>
								</h:dataTable>
							</x:div>	
                    	</h:panelGrid>
                        <%--/c:if--%>
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>

		<h:inputText id="FechaDesde" value="#{InterbankingBean.fecha}" style="display: none;">
	        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
	    </h:inputText>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{InterbankingBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });


	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("generarInterbanking:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("generarInterbanking:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
    
    
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("generarInterbanking:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    

  });
</script>

</body>
</html>
</f:view>
