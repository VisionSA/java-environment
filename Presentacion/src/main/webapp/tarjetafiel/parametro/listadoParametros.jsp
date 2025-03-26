<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel | Listado de parametros"/></title>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('parametros');">

<h:form id="mainMenu" style="display: none">
    <jsp:include page="/inc/navigation_test.jsp" />
    <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>PARAMETROS
    <small>Listado de parametros</small>
  </h1>
</section>

<section class="content">
    <div class="box box-default">
        <div class="box-header"><h3></h3>
        </div>

<center>
<h:form id="parametros">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter">
        
        <f:facet name="body">
            <h:panelGroup id="body">
        	
				<c:if test="${!empty ParametroBean.parametros}">
                <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="standardTable_Header"
                            footerClass="standardTable_Header"
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
                            var="parametro"
                            value="#{ParametroBean.parametros}"
                            rows="25"
                            preserveDataModel="false" >
                                               
                        <h:column>
                            <f:facet name="header"><h:outputText value="N1" /></f:facet>
                            <h:outputText value="#{parametro.id.nivel1}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header"><h:outputText value="N2" /></f:facet>
                            <h:outputText value="#{parametro.id.nivel2}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="N3" /></f:facet>
                            <h:outputText value="#{parametro.id.nivel3}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="Concepto" /></f:facet>
                            <h:outputText value="#{parametro.concetab}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C1" /></f:facet>
                            <h:outputText value="#{parametro.colum1}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C2" /></f:facet>
                            <h:outputText value="#{parametro.colum2}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C3" /></f:facet>
                            <h:outputText value="#{parametro.colum3}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C4" /></f:facet>
                            <h:outputText value="#{parametro.colum4}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C5" /></f:facet>
                            <h:outputText value="#{parametro.colum5}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C6" /></f:facet>
                            <h:outputText value="#{parametro.colum6}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C7" /></f:facet>
                            <h:outputText value="#{parametro.colum7}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C8" /></f:facet>
                            <h:outputText value="#{parametro.colum8}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="C9" /></f:facet>
                            <h:outputText value="#{parametro.colum9}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header"><h:outputText value="Fecha Alta" /></f:facet>
                            <h:outputText value="#{parametro.colum10}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <x:commandLink action="#{ParametroBean.goEditar}">
                            	<f:param id="n1Parametro" name="n1Parametro" value="#{parametro.id.nivel1}" />
                            	<f:param id="n2Parametro" name="n2Parametro" value="#{parametro.id.nivel2}" />
                            	<f:param id="n3Parametro" name="n3Parametro" value="#{parametro.id.nivel3}" />
								<x:graphicImage value="/img/editar.gif" title="Editar el parametro" border="0"/>
                            </x:commandLink>
                        </h:column>                                                
                        
                        <h:column>
                            <f:facet name="header">
                            </f:facet>
                            <x:commandLink value="" action="#{ParametroBean.eliminar}" onclick="if (!confirm(' Desea dar de baja el parametro?')) return false">
                            	<f:param id="n1Parametro" name="n1Parametro" value="#{parametro.id.nivel1}" />
                            	<f:param id="n2Parametro" name="n2Parametro" value="#{parametro.id.nivel2}" />
                            	<f:param id="n3Parametro" name="n3Parametro" value="#{parametro.id.nivel3}" />
                            	<x:graphicImage value="/img/borrar.gif" title="Eliminar el parametro" border="0"/>
                            </x:commandLink>
                        </h:column>                         

                	</x:dataTable>
                
				<%-- Paginación --%>
        		<%@include file="/inc/paginator.jsp" %>
				<%-- Paginación --%>        		                
				</c:if>
				
				<c:if test="${empty ParametroBean.parametros}">
					<h:outputText value="No existen parametros." styleClass="texto"/>
				</c:if>
				
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp1_menu:A]\#{ParametroBean.listar}'") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
   
</body>
</html>
</f:view>
