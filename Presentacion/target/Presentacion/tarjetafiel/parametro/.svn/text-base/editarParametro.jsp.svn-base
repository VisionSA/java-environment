<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Editar parametro"/></title>
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('editarParametros');">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>PARAMETROS
    <small>Editar parametro</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

<center>
<h:form id="editarParametros">
	<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >
        
        <f:facet name="body">
            <h:panelGroup id="body">
            	
					<h:panelGrid columns="1">
		                	<h:message style="font-size: 10px;color: red" for="Concepto"/>
					</h:panelGrid>        	        		
            	   <h:panelGrid id="gridNivel" columns="4" width="277">
            	  
            	    <h:outputText id="outNivel" value="Nivel: " styleClass="texto"/>
	                <h:outputText value="#{ParametroBean.id.nivel1}" style="font-style: italic" styleClass="texto"/>
	                <h:outputText value="#{ParametroBean.id.nivel2}" style="font-style: italic" styleClass="texto"/>
	                <h:outputText value="#{ParametroBean.id.nivel3}" style="font-style: italic" styleClass="texto"/>
            	   
            	  </h:panelGrid>
            	   
	              <h:panelGrid id="gridColumnas" columns="2" width="277">

	                <h:outputText id="outConcepto" value="Concepto: " styleClass="texto"/>
	                <h:inputText id="Concepto" value="#{ParametroBean.concetab}" 
	                			 required="true" styleClass="bordeceldatext" size="45" style="width: 250px;"/>

	                <h:outputText id="outColumna1" value="Columna1: " styleClass="texto"/>
	                <h:inputText id="inpColumna1" value="#{ParametroBean.colum1}" 
	                			 required="false" size="6" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna2" value="Columna2: " styleClass="texto"/>
	                <h:inputText id="inpColumna2" value="#{ParametroBean.colum2}" 
	                			 required="false" size="13" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna3" value="Columna3: " styleClass="texto"/>
	                <h:inputText id="inpColumna3" value="#{ParametroBean.colum3}" 
	                			 required="false" size="13" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna4" value="Columna4: " styleClass="texto"/>
	                <h:inputText id="inpColumna4" value="#{ParametroBean.colum4}" 
	                			 required="false" size="2" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna5" value="Columna5: " styleClass="texto"/>
	                <h:inputText id="inpColumna5" value="#{ParametroBean.colum5}" 
	                			 required="false" size="2" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna6" value="Columna6: " styleClass="texto"/>
	                <h:inputText id="inpColumna6" value="#{ParametroBean.colum6}" 
	                			 required="false" size="1" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna7" value="Columna7: " styleClass="texto"/>
	                <h:inputText id="inpColumna7" value="#{ParametroBean.colum7}" 
	                			 required="false" size="1" styleClass="bordeceldatext" style="width: 40px;"/>

	                <h:outputText id="outColumna8" value="Columna8: " styleClass="texto"/>
	                <h:inputText id="inpColumna8" value="#{ParametroBean.colum8}" 
	                			 required="false" size="1" styleClass="bordeceldatext" style="width: 40px;"/>
	                
	                <h:outputText id="outColumna9" value="Columna9: " styleClass="texto"/>
	                <h:inputText id="inpColumna9" value="#{ParametroBean.colum9}" 
	                			 required="false" size="1" styleClass="bordeceldatext" style="width: 40px;"/>	                

	                <h:outputText id="outColumna10" value="Columna10: " styleClass="texto"/>
	                <h:inputText id="inpColumna10" value="#{ParametroBean.colum10}" required="false" size="10" styldClass="bordeceldatext"/>

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
	                <h:commandButton id="buttonModificar" value="Modificar" action="#{ParametroBean.editar}" styleClass="botones"/>
					<h:commandButton id="buttonCancelar" value="Cancelar" action="#{ParametroBean.listar}" type="reset" styleClass="botones"/>					
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>
   
</body>
</html>
</f:view>
