<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel | Transaccionador"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	};
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'ven1','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){
				popupWindow.opener = self;
				popupWindow.close();
			}
		};
		function correr(params) {
			alert(params.length);
			for (i=0; i < params.length; i++){
				popup(params[i],500,300);
			}
		};
		
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onload="correr(${TransaccionadorBean.popupReport})">
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
        <h3 class="box-title">Transaccionador</h3>
    </div>
    <br/>

<center>

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
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				<h:panelGrid width="800">

				<h:outputText value="Para Transacciones Posnet" />
					<x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_ColumnRight"
                            sortable="false"
                            var="obj" 
                            value="#{TransaccionadorBean.tokenList}"
                            preserveDataModel="false">

                        <x:column defaultSorted="true">
                            <f:facet name="header">
                                <h:outputText id="outId" value="Id" />
                            </f:facet>
                            <h:outputText id="Id" value="#{obj.token.idToken}" />
                        </x:column>
                   
                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outCantidadCuotas" value="Cantidad Cuotas" />
                            </f:facet>
                            <h:outputText id="CantidadCuotas" value="#{obj.parse.cantidadCuotas}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outImporte" value="Importe" />
                            </f:facet>
                            <h:outputText id="Importe" value="#{obj.parse.importe}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outImporteSinDescuento" value="Importe Sin Descuento" />
                            </f:facet>
                            <h:outputText id="ImporteSinDescuento" value="#{obj.parse.importeSinDescuento}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outCodigoAutorizacion" value="Codigo Autorizacion" />
                            </f:facet>
                            <h:outputText id="CodigoAutorizacion" value="#{obj.parse.codigoAutorizacion}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outNumeroComercio" value="Numero Comercio" />
                            </f:facet>
                            <h:outputText id="NumeroComercio" value="#{obj.parse.numeroComercio}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outNumeroTarjeta" value="Numero Tarjeta" />
                            </f:facet>
                            <h:outputText id="NumeroTarjeta" value="#{obj.parse.numeroTarjeta}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outPrivate" value="Private" />
                            </f:facet>
                            <h:outputText id="Private" value="#{obj.parse.private}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outTipoCuota" value="Tipo Cuota" />
                            </f:facet>
                            <h:outputText id="TipoCuota" value="#{obj.parse.tipoCuota}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outTipoPlanCuotas" value="Tipo Plan Cuotas" />
                            </f:facet>
                            <h:outputText id="TipoPlanCuotas" value="#{obj.parse.tipoPlanCuotas}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outUtilizaPin" value="Utiliza Pin" />
                            </f:facet>
                            <h:outputText id="UtilizaPin" value="#{obj.parse.utilizaPin}" />
                        </x:column>
						
						<x:column>
                            <f:facet name="header">
                                <h:outputText id="outPin" value="Pin" />
                            </f:facet>
                            <h:outputText id="Pin" value="#{obj.parse.pin}" />
                        </x:column>
						
                        <x:column>
                            <f:facet name="header">
                                <h:outputText id="outRazon" value="Origen" />
                            </f:facet>
                            <h:outputText id="Origen" value="#{obj.parse.origen}" />
                        </x:column>
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{obj.seleccionado}" />
                        </h:column>                         
                	</x:dataTable>
				</h:panelGrid>
				
				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Transacciones" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="2" align="center">
						<h:outputText value="Cantidad de tokens:" styleClass="texto"/>
						<h:inputText id="operador_desde" value="#{TransaccionadorBean.cantToken}" 
			               			 styleClass="bordeceldainferior" maxlength="11" size="11" 
			               			 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
			               			 onkeypress="return soloEnteros(this,event);"/>
 						
						<h:outputText value="Tiempo (en ms):" styleClass="texto"/>
						<h:inputText id="operador_hasta" value="#{TransaccionadorBean.tienpo}" 
				        			styleClass="bordeceldainferior" maxlength="11" size="11" 
				        			style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
				        			onkeypress="return soloEnteros(this,event);"/>


						<f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;</f:verbatim>
						<h:panelGroup>
							<h:commandButton id="pruebaPosnet" action="#{TransaccionadorBean.generarTransac}" 
											 value="Iniciar Prueba Posnet" styleClass="btn btn-primary btn-flat pull-right"/>
							 </h:panelGroup>
					</h:panelGrid>
					
				</s:layoutingTitlePane>
			
			<h:commandButton action="#{TransaccionadorBean.verResult}" 
				 value="Ver Resultados" styleClass="btn btn-primary btn-flat pull-right"/>

			<f:verbatim>&nbsp;</f:verbatim>

			<h:inputTextarea id="inputTextoUno" value="#{TransaccionadorBean.resultados}" style="width : 1000px; height : 300px;" 
					rendered="#{TransaccionadorBean.boolResult}"/> 
			
			        
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{TransaccionadorBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>   
</body>
</html>
</f:view>
